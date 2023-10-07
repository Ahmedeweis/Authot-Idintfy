package com.example.authorWebsite.Services.Interfaces;

import com.example.authorWebsite.Models.User;
import org.springframework.stereotype.Component;

import java.util.List;



public  interface UserService {

    public boolean addUser(User user);
    public  boolean deleteUser(int userId);
    public  boolean updateUser(User user);
    public  User findUserById(int id);
    public List<User> getAllUsers();
    public User findUserByEmail(String email);
    public boolean addRoleToUser(int userId , int roleId);

}
