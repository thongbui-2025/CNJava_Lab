package Lab2_Exercise;

import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class Main 
{
	
    public static void main( String[] args )
    {
    	while(true) {
    		System.out.println("--------------Menu------------");
    		System.out.println("1. Read all products");
    		System.out.println("2. Read detail of a product by id");
    		System.out.println("3. Add a new product");
    		System.out.println("4. Update a product");
    		System.out.println("5. Deleta a product by id");
    		System.out.println("6. Exit");
    		
    		ProductDAO pdao = new ProductDAO();
    		
    		Scanner sc = new Scanner(System.in);
    		System.out.print("Your choice: ");
    		int key = sc.nextInt();
    		
        	switch (key) {
    		case 1:
    			List<Product> listProducts = pdao.readAll();
    			for(Product p : listProducts) {
    				System.out.println(p.toString());
    			}
    			break;
    		case 2:
    			Scanner sc2 = new Scanner(System.in);
    			System.out.println("Nhap ma san pham: ");
    			int id = sc2.nextInt();
    			System.out.println(pdao.read(id).toString()); ;
    			break;
    		case 3:
    			Scanner sc3 = new Scanner(System.in);
    			System.out.println("Nhap ten san pham: ");
    			String name = sc3.nextLine();
    			System.out.println("Nhap gia san pham: ");
    			int price = sc3.nextInt();
    			int rows = pdao.add(new Product(0, name, price));
    			if(rows==1) {
    				System.out.println("Them thanh cong");
    			}
    			break;
    		case 4:
    			
    			break;
    		case 5:
    			Scanner sc5 = new Scanner(System.in);
    			System.out.println("Nhap ma san pham: ");
    			int id5 = sc5.nextInt();
    			if(pdao.delete(id5)) {
    				System.out.println("Xoa thanh cong");
    			}
    			else {
    				System.out.println("Da co loi xay ra");
    			}
    		default:
    			break;
    		}
	
    	}
    }
}
