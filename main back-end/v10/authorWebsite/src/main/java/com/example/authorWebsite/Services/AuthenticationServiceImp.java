package com.example.authorWebsite.Services;

import com.example.authorWebsite.Models.Requests.LoginModel;
import com.example.authorWebsite.Models.User;
import com.example.authorWebsite.Repositories.UserRepository;
import com.example.authorWebsite.Services.Interfaces.AuthenticationService;
import com.example.authorWebsite.Services.Interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Service
public class AuthenticationServiceImp implements AuthenticationService {

    @Autowired
    UserRepository userRepository;


    @Override
    public User login(LoginModel loginModel, HttpSession httpSession) {
        Optional<List<User>> user =userRepository.login(loginModel.getEmail(),loginModel.getPassword());
        if(!user.isPresent()||user.get().size()==0)return null;
        httpSession.setAttribute("userName",user.get().get(0).getName());
        httpSession.setAttribute("userId",user.get().get(0).getId());
        return  user.get().get(0);
    }

    @Override
    public void logout(HttpSession httpSession) {
        httpSession.invalidate();
    }
}
