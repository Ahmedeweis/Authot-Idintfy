package com.example.authorWebsite.Services;


import com.example.authorWebsite.Models.Role;
import com.example.authorWebsite.Models.User;
import com.example.authorWebsite.Repositories.UserRepository;
import com.example.authorWebsite.Services.Interfaces.RoleService;
import com.example.authorWebsite.Services.Interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleService roleService;


    @Override
    public boolean addUser(User user) {

        if(findUserByEmail(user.getEmail())!=null)return false;
        userRepository.save(user);
        return  true;
    }

    @Override
    public boolean deleteUser(int userId) {
        User user= findUserById(userId);
        if(user==null)return  false;
        userRepository.delete(user);
        return  true;
    }

    @Override
    public boolean updateUser(User user) {
        User currentUser=findUserById(user.getId());
        if(currentUser==null)return false;

        if(!currentUser.getEmail().equals(user.getEmail())){
            if(findUserByEmail(user.getEmail())!=null)return false;
        }
        currentUser.setAge(user.getAge());
        currentUser.setName(user.getName());
        currentUser.setEmail(user.getEmail());
        currentUser.setPassword(user.getPassword());
        userRepository.save(currentUser);
        return  true;
    }

    @Override
    public User findUserById(int id) {

        Optional<User>user=userRepository.findById(id);
        if(user.isPresent())
             return user.get() ;
        return  null;
    }

    @Override
    public List<User> getAllUsers() {
        return  userRepository.findAll();
    }

    @Override
    public User findUserByEmail(String email) {

        Optional<List<User>> user=userRepository.findByEmail(email);
        if(user.isPresent()&&user.get().size()>0){
           return user.get().get(0);
        }
        return null;
    }

    @Override
    public boolean addRoleToUser(int userId, int roleId) {
        User user =findUserById(userId);
        Role role=roleService.findRoleById(roleId);
        if(user==null||role==null)return  false;
        user.getRoles().add(role);
        userRepository.save(user);
        return  true;
    }

}
