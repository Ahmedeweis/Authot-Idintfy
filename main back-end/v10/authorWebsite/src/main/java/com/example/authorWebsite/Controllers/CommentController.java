package com.example.authorWebsite.Controllers;


import com.example.authorWebsite.Models.Author;
import com.example.authorWebsite.Models.Comment;
import com.example.authorWebsite.Services.Interfaces.CommentService;
import com.example.authorWebsite.Services.Interfaces.HistoryService;
import com.example.authorWebsite.Services.Interfaces.SendResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("comment")
public class CommentController {


    @Autowired
    CommentService commentService;
    @Autowired
    HistoryService historyService;

    @Autowired
    SendResponseEntity sendResponseEntity;


    @PostMapping("addComment")
    public ResponseEntity addComment(@RequestBody Comment comment,@RequestParam int currentUserId){

        historyService.addToHistory("addComment",currentUserId);
        commentService.addComment(comment);
        return  sendResponseEntity.SendResponseEntity("success","comment created successfully",
                HttpStatus.CREATED);
    }


    @DeleteMapping("deleteComment")
    public ResponseEntity deleteComment(@RequestParam int commentId,@RequestParam int currentUserId){

        historyService.addToHistory("deleteComment",currentUserId);
        boolean state = commentService.deleteComment(commentId);

        if(!state){
            return sendResponseEntity.SendResponseEntity("fail",
                    "comment not found",HttpStatus.NOT_FOUND);
        }
        return  sendResponseEntity.SendResponseEntity("success","Comment deleted successfully",
                HttpStatus.OK);
    }




}
