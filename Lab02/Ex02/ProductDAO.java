package Lab2_Exercise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements Repository<Product, Integer> {

	public static Connection getConnection() {
		String url = "jdbc:mysql://localhost/productmanagement?useSSL=false";
        Connection conn = null;
        try {
			conn = DriverManager.getConnection(url,"root","");
			String sql = "Create table if not exists product(id int not null AUTO_INCREMENT, name nvarchar(30), price int, primary key(id))";
			Statement stm = (Statement) conn.createStatement();
			stm.execute(sql);
        } catch (SQLException e) {
			//
			System.out.println("Ket noi that bai: "+e.getMessage());
			e.printStackTrace();
		}
        return conn;
	}
	public static void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			//
			e.printStackTrace();
		}
	}
	@Override
	public Integer add(Product item) {
		//
		Connection conn = getConnection();
		String sql = "Insert into Product(name, price) values(?,?)";
		PreparedStatement ptm;
		try {
			ptm = (PreparedStatement) conn.prepareStatement(sql);
			ptm.setString(1,item.getName());
			ptm.setInt(2,item.getPrice());
			int rows = ptm.executeUpdate();
			return rows;
		} catch (SQLException e) {
			//
			e.printStackTrace();
		}
		closeConnection(conn);
		return 0;
	}

	@Override
	public List<Product> readAll() {
		//
		Connection conn = getConnection();
		String sql = "select * from product";
		try {
			Statement stm = (Statement) conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			List<Product> productList  = new ArrayList<Product>();
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				int price = rs.getInt(3);
				productList.add(new Product(id, name, price));
			}
			return productList;
		} catch (SQLException e1) {
			//
			e1.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			//
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Product read(Integer id) {
		//
		Connection conn = getConnection();
		String sql = "select * from product where id = ?";
		try {
			PreparedStatement ptm = (PreparedStatement) conn.prepareStatement(sql);
			ptm.setInt(1, id);
			ResultSet rs =  ptm.executeQuery();
			rs.next();
			int idpro = rs.getInt(1);
			String name = rs.getString(2);
			int price = rs.getInt(3);
			return new Product(idpro,name,price);
		} catch (SQLException e1) {
			//
			e1.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			//
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean update(Product item) {
		//
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		//
		//
		Connection conn = getConnection();
		String sql = "delete from product where id = ?";
		try {
			PreparedStatement ptm = (PreparedStatement) conn.prepareStatement(sql);
			ptm.setInt(1, id);
			return ptm.execute();
		} catch (SQLException e1) {
			//
			e1.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			//
			e.printStackTrace();
		}
		return false;
	}


	
}
