package com.example.ltm.Model.BO;

import com.example.ltm.Model.Bean.User;
import com.example.ltm.Model.DAO.UserDAO;

import java.util.List;

public class UserBO {
    private UserDAO userDAO = new UserDAO();
    public User findUserByName(String username) {
        return UserDAO.getUser(username);
    }
    public boolean checkUS(String username){
        return userDAO.checkUS(username);
    }

    public List<User> getAllUser(){
        return userDAO.getAllUser();
    }

}
