package edu.tdtu.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import edu.tdtu.beans.Account;
import edu.tdtu.beans.Product;

import java.util.Properties;


public class HibernateUtils {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) {
            Configuration conf = new Configuration();
            Properties props = new Properties();

            props.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
            props.put(Environment.URL, "jdbc:mysql://localhost:3306/productmanagement");
            props.put(Environment.USER, "root");
            props.put(Environment.PASS, "");
            props.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");

            conf.addAnnotatedClass(Product.class);
            conf.addAnnotatedClass(Account.class);
            conf.setProperties(props);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(conf.getProperties())
                    .build();

            sessionFactory = conf.buildSessionFactory();
        }

        return sessionFactory;
    }
}
