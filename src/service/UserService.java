package service;

import dao.UserDao;
import entity.User;

public class UserService {
    private static UserDao dao = new UserDao();

    public static User getUserById(String id){
        return dao.getUserById(id);
    }

    public static User getUserByName(String name){
        return dao.getUserByName(name);
    }

    public static boolean addUser(User user){
        return dao.addUser(user);
    }

    public static boolean deleteUser(User user){
        return dao.deleteUser(user);
    }

    public static boolean deleteUserById(String id){
        return dao.deleteUserById(id);
    }

    public static boolean deleteUserByName(String name){
        return dao.deleteUserByName(name);
    }
}

