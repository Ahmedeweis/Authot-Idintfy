package com.example.authorWebsite.Controllers;


import com.example.authorWebsite.Models.History;
import com.example.authorWebsite.Models.Role;
import com.example.authorWebsite.Services.Interfaces.HistoryService;
import com.example.authorWebsite.Services.Interfaces.RoleService;
import com.example.authorWebsite.Services.Interfaces.SendResponseEntity;
import com.example.authorWebsite.Services.Interfaces.SendResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;

@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    RoleService roleService;
    @Autowired
    private HttpSession httpSession;

    @Autowired
    HistoryService historyService;
    @Autowired
    SendResponseEntity sendResponseEntity;
    @PostMapping("addRole")
    public ResponseEntity addRole(@RequestBody Role role,@RequestParam int currentUserId){

        boolean state= roleService.addRole(role);
        historyService.addToHistory("addRole",currentUserId);
        if(state==false){

            return sendResponseEntity.SendResponseEntity("fail",
                    "role is already exists",HttpStatus.BAD_REQUEST);
        }

        return sendResponseEntity.SendResponseEntity(
                "success","role created successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("deleteRole")
    public  ResponseEntity deleteRole(@RequestParam int roleId,@RequestParam int currentUserId){

        boolean state =roleService.deleteRole(roleId);
        historyService.addToHistory("deleteRole",currentUserId);
        if(state==false){
            return  sendResponseEntity.SendResponseEntity("fail","role not found",
                    HttpStatus.NOT_FOUND);
        }
        return  sendResponseEntity.SendResponseEntity("success","role deleted successfully",
                HttpStatus.OK);
    }

    @PutMapping("updateRole")
    public  ResponseEntity updateRole(@RequestBody Role role,@RequestParam int currentUserId){
        boolean state = roleService.updateRole(role);
        historyService.addToHistory("updateRole",currentUserId);

        if(!state){
            return  sendResponseEntity.SendResponseEntity("fail",
                    "the name of role is already exists",HttpStatus.BAD_REQUEST);
        }
        return  sendResponseEntity.SendResponseEntity("success","role updated successfully"
                ,HttpStatus.OK);
    }

    @GetMapping("getAllRoles")
    public  ResponseEntity getAllRoles(@RequestParam int currentUserId){
        historyService.addToHistory("getAllRoles",currentUserId);
        return  sendResponseEntity.SendResponseEntityWithBody(
                "success","roles retrieved successfully",
                HttpStatus.OK,roleService.getAllRoles()
        );
    }

}
