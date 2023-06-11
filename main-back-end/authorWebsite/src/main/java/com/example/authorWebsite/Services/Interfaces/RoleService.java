package com.example.authorWebsite.Services.Interfaces;

import com.example.authorWebsite.Models.Role;

import java.util.List;

public interface RoleService {

    public boolean addRole(Role role);
    public  boolean updateRole(Role role);
    public boolean deleteRole(int roleId);
    public Role findRoleById(int id);
    public Role findRoleByName(String name);
    public List<Role> getAllRoles();
}
