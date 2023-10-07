package com.example.authorWebsite.Services;

import com.example.authorWebsite.Models.Role;
import com.example.authorWebsite.Repositories.RoleRepository;
import com.example.authorWebsite.Services.Interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class RoleServiceImp implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public boolean addRole(Role role) {

        if(findRoleByName(role.getName())!=null)return false;
        roleRepository.save(role);
        return true;
    }

    @Override
    public boolean updateRole(Role role) {

        Role currentRole=findRoleById(role.getId());
        if(currentRole==null)return false;
        if(!currentRole.getName().equals(role.getName())){
            if(findRoleByName(role.getName())!=null)return false;
        }
        currentRole.setName(role.getName());
        roleRepository.save(currentRole);
        return  true;
    }

    @Override
    public boolean deleteRole(int roleId) {

        Role role=findRoleById(roleId);
        if(role==null)return  false;
        roleRepository.delete(role);
        return  true;
    }

    @Override
    public Role findRoleById(int id) {
        Optional<Role> role=roleRepository.findById(id);
        if(role.isPresent())return  role.get();
        return  null;
    }

    @Override
    public Role findRoleByName(String name) {

        Optional<List<Role>>role =roleRepository.findByName(name);
        if(role.isPresent()&&role.get().size()>0){
            return role.get().get(0);
        }
        return  null;
    }

    @Override
    public List<Role> getAllRoles() {
        return  roleRepository.findAll();
    }

}
