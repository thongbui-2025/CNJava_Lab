package edu.tdtu.daos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import edu.tdtu.beans.Product;
import edu.tdtu.utils.HibernateUtils;

import java.util.List;

public class ProductDAO {
    public static void add(Product product) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(product);
        session.getTransaction().commit();
        session.close();
    }

    public static Product get(int id) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();

        Product product = session.get(Product.class, id);

        session.close();
        return product;
    }

    public static List<Product> getAll() {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();

        String hql = "FROM Product";
        Query query = session.createQuery(hql);
        List<Product> lst = query.list();

        session.close();
        return lst;
    }

    public static void update(Product product) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.update(product);
        session.getTransaction().commit();
        session.close();
    }

    public static void delete(int id) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Product product = get(id);
        session.beginTransaction();
        session.delete(product);
        session.getTransaction().commit();
        session.close();
    }
}
