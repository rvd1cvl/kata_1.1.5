package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }

    public Transaction transaction = null;
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();


    @Override
    public void createUsersTable() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String create_sql = "CREATE TABLE IF NOT EXISTS users " +
                    "(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(100) NOT NULL, " +
                    "lastName VARCHAR(100) NOT NULL, " +
                    "age SMALLINT NOT NULL)";
            session.createSQLQuery(create_sql).executeUpdate();
            transaction.commit();
            transaction = null;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                transaction = null;
            }
            e.printStackTrace();
        }

    }

    @Override
    public void dropUsersTable() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
            transaction.commit();
            transaction = null;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                transaction = null;
            }
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName ,age);
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            transaction = null;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                transaction = null;
            }
            e.printStackTrace();
        }
    }


    @Override
    public void removeUserById(long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(session.get(User.class, id));
            transaction.commit();
            transaction = null;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                transaction = null;
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            users = session.createSQLQuery("SELECT * FROM users").getResultList();
            transaction.commit();
            transaction = null;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                transaction = null;
            }
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createQuery("delete User").executeUpdate();
            transaction.commit();
            transaction = null;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                transaction = null;
            }
            e.printStackTrace();
        }
    }
}