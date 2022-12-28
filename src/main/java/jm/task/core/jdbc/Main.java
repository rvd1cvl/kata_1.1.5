package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Aboba1", "lastName1", (byte) 18);
        userService.saveUser("Aboba2", "lastName2", (byte) 18);
        userService.saveUser("Aboba3", "lastName3", (byte) 18);
        userService.saveUser("Aboba4", "lastName4", (byte) 18);

        userService.removeUserById(1);
        userService.getAllUsers();
        userService.dropUsersTable();
    }
}
