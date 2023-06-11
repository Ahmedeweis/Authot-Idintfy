package com.example.authorWebsite.Controllers;


import com.example.authorWebsite.Models.History;
import com.example.authorWebsite.Models.Requests.LoginModel;
import com.example.authorWebsite.Models.User;
import com.example.authorWebsite.Services.Interfaces.*;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("auth")
public class AuthenticationController {


    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    ArticleService articleService;
    @Autowired
    SendResponseEntity sendResponseEntity;

    @Autowired
    HistoryService historyService;

    @Autowired
    private HttpSession httpSession;

    @PostMapping("login")
    public ResponseEntity login(@RequestBody LoginModel loginModel) throws CsvValidationException, IOException {

//        articleService.loadSheet();
       User user= authenticationService.login(loginModel,httpSession);
       if(user==null){
           return  sendResponseEntity.SendResponseEntity("fail","invalid credentials"
                   , HttpStatus.NOT_FOUND);
       }



       return  sendResponseEntity.SendResponseEntityWithBody("success","login successfully"
       ,HttpStatus.OK,user);
    }


    @GetMapping("logout")
    public ResponseEntity logout(@RequestParam int currentUserId){
        historyService.addToHistory("logout",currentUserId);
        authenticationService.logout(httpSession);
        return new ResponseEntity(HttpStatus.OK);
    }

}
