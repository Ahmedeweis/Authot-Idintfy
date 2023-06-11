package com.example.authorWebsite.Services.Interfaces;

import com.example.authorWebsite.Models.Requests.LoginModel;
import com.example.authorWebsite.Models.User;

import javax.servlet.http.HttpSession;

public interface AuthenticationService {
    public User login(LoginModel loginModel, HttpSession httpSession);
    public  void logout(HttpSession httpSession);
}
