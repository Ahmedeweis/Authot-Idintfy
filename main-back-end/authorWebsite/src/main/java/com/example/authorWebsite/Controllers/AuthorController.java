package com.example.authorWebsite.Controllers;


import com.example.authorWebsite.Models.Author;
import com.example.authorWebsite.Services.Interfaces.AuthorService;
import com.example.authorWebsite.Services.Interfaces.HistoryService;
import com.example.authorWebsite.Services.Interfaces.SendResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("author")
public class AuthorController {

    @Autowired
    AuthorService authorService;
    @Autowired
    HistoryService historyService;

    @Autowired
    SendResponseEntity sendResponseEntity;


    @PostMapping("addAuthor")
    public ResponseEntity addAuthor(@RequestBody Author author,@RequestParam int currentUserId){

        historyService.addToHistory("addAuthor",currentUserId);
        authorService.addAuthor(author);
        return  sendResponseEntity.SendResponseEntity("success","authr created successfully",
                HttpStatus.CREATED);
    }

    @PutMapping("updateAuthor")
    public ResponseEntity updateAuthor(@RequestBody Author author,@RequestParam int currentUserId){

        historyService.addToHistory("updateAuthor",currentUserId);
        boolean state = authorService.updateAuthor(author);

        if(!state){
            return sendResponseEntity.SendResponseEntity("fail",
                    "author not found",HttpStatus.NOT_FOUND);
        }
        return  sendResponseEntity.SendResponseEntity("success","Author updated successfully",
                HttpStatus.OK);
    }

    @DeleteMapping("deleteAuthor")
    public ResponseEntity deleteAuthor(@RequestParam int authorId,@RequestParam int currentUserId){

        historyService.addToHistory("deleteAuthor",currentUserId);
        boolean state = authorService.deleteAuthor(authorId);

        if(!state){
            return sendResponseEntity.SendResponseEntity("fail",
                    "author not found",HttpStatus.NOT_FOUND);
        }
        return  sendResponseEntity.SendResponseEntity("success","Author deleted successfully",
                HttpStatus.OK);
    }

    @GetMapping("viewAllAuthors")
    public ResponseEntity viewAllAuthors(@RequestParam int currentUserId){

        historyService.addToHistory("viewAllAuthors",currentUserId);
        return sendResponseEntity.SendResponseEntityWithBody("success",
                "authors retrieved successfully",HttpStatus.OK,authorService.viewAllAuthors());
    }

    @GetMapping("identifyAuthor")
    public ResponseEntity identifyAuthor(@RequestParam String article) throws IOException {

        return authorService.identifyAuthor(article);

    }

    @GetMapping("readPdf")
    public ResponseEntity readPdf(@RequestParam String filePath) throws IOException {

        return sendResponseEntity.SendResponseEntityWithBody("success",
                "authors retrieved successfully",HttpStatus.OK,authorService.readPdf(filePath));

    }



}
