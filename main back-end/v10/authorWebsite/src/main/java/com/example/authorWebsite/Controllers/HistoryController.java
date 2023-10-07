package com.example.authorWebsite.Controllers;


import com.example.authorWebsite.Models.History;
import com.example.authorWebsite.Services.Interfaces.HistoryService;
import com.example.authorWebsite.Services.Interfaces.SendResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("history")
public class HistoryController {


    @Autowired
    HistoryService historyService;
    @Autowired
    private HttpSession httpSession;

    @Autowired
    SendResponseEntity sendResponseEntity;
    @GetMapping("viewHistory")
    public ResponseEntity viewHistory(@RequestParam int currentUserId){

        List<History> histories=historyService.viewHistory(currentUserId);

        historyService.addToHistory("viewHistory",currentUserId);
        return  sendResponseEntity.SendResponseEntityWithBody(
                "success","history retrieved successfully",
                HttpStatus.OK,
                histories);
    }

    @DeleteMapping("deleteHistory")
    public  ResponseEntity deleteHistory(@RequestParam int historyId){

        boolean status =historyService.deleteFromHistory(historyId);
        if(!status){
            return  sendResponseEntity.SendResponseEntity(
                    "fail","history not found",
                    HttpStatus.NOT_FOUND);
        }
        return  sendResponseEntity.SendResponseEntity("success",
                "history deleted successfully",HttpStatus.OK);
    }

}
