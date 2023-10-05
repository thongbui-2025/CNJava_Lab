package Lab2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Program 
{
    public static void main( String[] args )
    {
        String url = "jdbc:mysql://localhost/lab2_testjdbc?useSSL=false";
        Connection conn = null;
        try {
			conn = DriverManager.getConnection(url,"root","");
			System.out.println("Ket noi thanh cong");
			
        } catch (SQLException e) {
			//
			System.out.println("Ket noi that bai: "+e.getMessage());
			e.printStackTrace();
		}
			String sql = "Create table if not exists student(id int not null AUTO_INCREMENT, name nvarchar(30), age int, primary key(id))";
			Statement stm;
			try {
				stm = (Statement) conn.createStatement();
				stm.execute(sql);
			} catch (SQLException e) {
				//
				e.printStackTrace();
			}
		
			String sql2 = "insert into student(name,age) values(?,?)";
			PreparedStatement ptm;
			try {
				ptm = (PreparedStatement) conn.prepareStatement(sql2);
				ptm.setString(1, "NVH");
				ptm.setInt(2, 18);
				int rows = ptm.executeUpdate();
				if(rows == 1) {
					System.out.println("Them thanh cong");
				}
				else {
					System.out.println("Da co loi xay ra");
				}
			} catch (SQLException e) {
				//
				e.printStackTrace();
			}
			String sql3 = "select * from student";
			try {
				stm = (Statement) conn.createStatement();
				ResultSet rs = stm.executeQuery(sql3);
				List<Student> studentList  = new ArrayList<Student>();
				while(rs.next()) {
					int id = rs.getInt(1);
					String name = rs.getString(2);
					int age = rs.getInt(3);
					studentList.add(new Student(id, name, age));
				}
				for(Student st : studentList) {
					System.out.println(st.toString());
				}
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
    }
}
