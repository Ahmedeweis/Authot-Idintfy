package com.example.authorWebsite.Controllers;


import com.example.authorWebsite.Models.History;
import com.example.authorWebsite.Models.Responses.SendResponse;
import com.example.authorWebsite.Models.Responses.SendResponseWithObject;
import com.example.authorWebsite.Models.User;
import com.example.authorWebsite.Services.Interfaces.HistoryService;
import com.example.authorWebsite.Services.Interfaces.SendResponseEntity;
import com.example.authorWebsite.Services.Interfaces.SendResponseService;
import com.example.authorWebsite.Services.Interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    private HttpSession httpSession;

    @Autowired
    HistoryService historyService;

    @Autowired
    SendResponseService sendResponseService;

    @Autowired
    SendResponseEntity sendResponseEntity;
    @GetMapping("viewProfile")
    public ResponseEntity viewProfile(@RequestParam int id,@RequestParam int currentUserId){

        User user =userService.findUserById(id);
        historyService.addToHistory("viewProfile",currentUserId);
        if(user==null){

            return new  ResponseEntity<SendResponse>(sendResponseService.CreateResponse("fail","user not found")
                    ,HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<SendResponseWithObject<User>>(
                sendResponseService.CreateResponseWithBody("success","user retreived successfully",user),
                HttpStatus.OK);

    }
    @PostMapping("addUser")
    public ResponseEntity addUser(@RequestBody User user){

        boolean state = userService.addUser(user);

        if(state==false){
            return new ResponseEntity<SendResponse>(
                    sendResponseService.CreateResponse("fail","Email used before"),HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<SendResponse>(sendResponseService.CreateResponse(
                "success","User Created successfully"
        ),HttpStatus.CREATED) ;
    }

    @DeleteMapping("deleteUser")
    public ResponseEntity deleteUser(@RequestParam int userId,@RequestParam int currentUserId){

        boolean state=userService.deleteUser(userId);
        historyService.addToHistory("deleteUser",currentUserId);

        if(state==false){
            return  new ResponseEntity<SendResponse>(sendResponseService.CreateResponse(
                    "fail","user not found"
            ),HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<SendResponse>(sendResponseService.CreateResponse(
                "success","user deleted successfully"
        ),HttpStatus.OK);
    }

    @PutMapping("updateUser")
    public ResponseEntity updateUser(@RequestBody User user,@RequestParam int currentUserId){
        boolean state=userService.updateUser(user);
        historyService.addToHistory("updateUser",currentUserId);
        if(state==false){
            return  new ResponseEntity<SendResponse>(sendResponseService.CreateResponse(
                    "fail","user not found or email is not unique"),HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity(sendResponseService.CreateResponse("success",
                "user updated successfully"),HttpStatus.OK);
    }

    @GetMapping("getAllUsers")
    public ResponseEntity getAllUsers(@RequestParam int currentUserId){
        historyService.addToHistory("getAllUsers",currentUserId);
        List<User> users=userService.getAllUsers();
        return  new ResponseEntity(sendResponseService.CreateResponseWithBody(
                "success","users retrieved successfully",users
        ),HttpStatus.OK);
    }

    @GetMapping("addRoleToUser")
    public  ResponseEntity addRoleToUser(@RequestParam  int userId, @RequestParam int roleId,
                                         @RequestParam int currentUserId){

        historyService.addToHistory("addRoleToUser",currentUserId);
        boolean state=userService.addRoleToUser(userId,roleId);
        if(!state){
            return  sendResponseEntity.SendResponseEntity("fail",
                    "user or role is not found",HttpStatus.NOT_FOUND);
        }
        return  sendResponseEntity.SendResponseEntity("success","role added successfully",
                HttpStatus.OK);

    }



}
