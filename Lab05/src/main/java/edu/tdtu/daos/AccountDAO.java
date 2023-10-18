package edu.tdtu.daos;

import org.hibernate.Session;
import org.hibernate.query.Query;

import edu.tdtu.beans.Account;
import edu.tdtu.utils.HibernateUtils;

public class AccountDAO {
    public static Account get(String username) {
        Session session = HibernateUtils.getSessionFactory()
                .openSession();
        String hql = "FROM Account WHERE username = :username";
        Query<Account> query = session.createQuery(hql);
        query.setParameter("username", username);
        Account account = query.uniqueResult();

        session.close();
        return account;
    }

    public static boolean isMatchPassword(String username, String password) {
        Account account = get(username);

        return account.getPassword().equals(password);
    }

    public static void add(Account acc) {
        Session session = HibernateUtils.getSessionFactory()
                .openSession();

        session.beginTransaction();

        session.save(acc);

        session.getTransaction().commit();
        session.close();
    }
}
