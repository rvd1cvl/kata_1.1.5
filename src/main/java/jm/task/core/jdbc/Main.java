package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.HibernateUtil;
import org.hibernate.Session;

import javax.transaction.Transaction;
import java.sql.SQLException;

import static jm.task.core.jdbc.dao.UserDaoJDBCImpl.connection;

public class Main {
    public static void main(String[] args) {

        HibernateUtil.getSessionFactory();
        UserDao userDao = new UserDaoHibernateImpl();

        userDao.createUsersTable();

        userDao.saveUser("Aboba1", "lastName1", (byte) 18);
        userDao.saveUser("Aboba2", "lastName2", (byte) 18);
        userDao.saveUser("Aboba3", "lastName3", (byte) 18);
        userDao.saveUser("Aboba4", "lastName4", (byte) 18);

        userDao.removeUserById(2);
        userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
