package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    private static final UserService userService = new UserServiceImpl();
    public static void main(String[] args) {
        userService.createUsersTable();

        userService.saveUser("aboba", "testLastName1", (byte) 18);
        userService.saveUser("aboba", "testLastName2", (byte) 18);
        userService.saveUser("aboba", "testLastName3", (byte) 18);
        userService.saveUser("aboba", "testLastName4", (byte) 18);

        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
