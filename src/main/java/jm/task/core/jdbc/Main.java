package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userDaoJDBC = new UserServiceImpl();
        UserService userDaoHibernate = new UserServiceImpl();

//        userDaoJDBC.createUsersTable();
//        userDaoJDBC.saveUser("Name1", "LastName1", (byte) 20);
//        userDaoJDBC.saveUser("Name2", "LastName2", (byte) 25);
//        userDaoJDBC.saveUser("Name3", "LastName3", (byte) 31);
//        userDaoJDBC.saveUser("Name4", "LastName4", (byte) 38);
//         List<User> users = userDao.getAllUsers();
//        for (User user : users) {
//            System.out.println(user);
//        }
//        userDaoJDBC.cleanUsersTable();
//        userDaoJDBC.dropUsersTable();

        userDaoHibernate.createUsersTable();

        userDaoHibernate.saveUser("Name1", "LastName1", (byte) 20);
        userDaoHibernate.saveUser("Name2", "LastName2", (byte) 25);
        userDaoHibernate.saveUser("Name3", "LastName3", (byte) 31);
        userDaoHibernate.saveUser("Name4", "LastName4", (byte) 38);

        List<User> users = userDaoHibernate.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
        userDaoHibernate.cleanUsersTable();
        userDaoHibernate.dropUsersTable();

    }
}
