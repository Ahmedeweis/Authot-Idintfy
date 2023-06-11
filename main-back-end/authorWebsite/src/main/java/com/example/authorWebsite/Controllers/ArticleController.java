package com.example.authorWebsite.Controllers;


import com.example.authorWebsite.Models.Article;
import com.example.authorWebsite.Models.Author;
import com.example.authorWebsite.Services.Interfaces.ArticleService;
import com.example.authorWebsite.Services.Interfaces.HistoryService;
import com.example.authorWebsite.Services.Interfaces.SendResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("article")
public class ArticleController {



    @Autowired
    ArticleService articleService;
    @Autowired
    HistoryService historyService;

    @Autowired
    SendResponseEntity sendResponseEntity;


    @PostMapping("addArticle")
    public ResponseEntity addArticle(@RequestBody Article article,@RequestParam int currentUserId){

        historyService.addToHistory("addArticle",currentUserId);
        boolean state = articleService.addArticle(article,currentUserId);
        if(state) {
            return sendResponseEntity.SendResponseEntity("success", "article created successfully",
                    HttpStatus.CREATED);
        }
        else {
            return sendResponseEntity.SendResponseEntity("failed","Author not found",
                    HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("updateArticle")
    public ResponseEntity updateArticle(@RequestBody Article article,@RequestParam int currentUserId){

        historyService.addToHistory("updateArticle",currentUserId);
        boolean state = articleService.updateArticle(article);

        if(!state){
            return sendResponseEntity.SendResponseEntity("fail",
                    "article not found",HttpStatus.NOT_FOUND);
        }
        return  sendResponseEntity.SendResponseEntity("success","article updated successfully",
                HttpStatus.OK);
    }

    @DeleteMapping("deleteArticle")
    public ResponseEntity deleteArticle(@RequestParam int articleId,@RequestParam int currentUserId){

        historyService.addToHistory("deleteArticle",currentUserId);
        boolean state = articleService.deleteArticle(articleId);

        if(!state){
            return sendResponseEntity.SendResponseEntity("fail",
                    "article not found",HttpStatus.NOT_FOUND);
        }
        return  sendResponseEntity.SendResponseEntity("success","Article deleted successfully",
                HttpStatus.OK);
    }

    @GetMapping("viewAllArticlesForAuthor")
    public ResponseEntity viewAllArticlesForAuthor(@RequestParam int authorId,@RequestParam int currentUserId){

        historyService.addToHistory("viewAllArticleForAuthor",currentUserId);
        return sendResponseEntity.SendResponseEntityWithBody("success",
                "articles retrieved successfully",HttpStatus.OK,
                articleService.viewArticlesForAuthor(authorId));
    }

    @GetMapping("viewArticle")
    public ResponseEntity viewArticle(@RequestParam int articleId,@RequestParam int currentUserId){

        historyService.addToHistory("viewArticle",currentUserId);
        return sendResponseEntity.SendResponseEntityWithBody("success",
                "article retrieved successfully",HttpStatus.OK,
                articleService.viewArticle(articleId));
    }

}
