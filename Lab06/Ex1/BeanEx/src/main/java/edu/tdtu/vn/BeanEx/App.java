package edu.tdtu.vn.BeanEx;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App 
{
    public static void main( String[] args )
    {
    	ApplicationContext context = new ClassPathXmlApplicationContext("Appconfig.xml"); 
    	Product p1 = (Product) context.getBean("product1");
    	System.out.println(p1.toString()); 
    	Product p2 = (Product) context.getBean("product2");
    	System.out.println(p2.toString()); 
    	Product p3 = (Product) context.getBean("product3");
    	System.out.println(p3.toString()); 
    }
}
