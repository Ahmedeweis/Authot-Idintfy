package com.example.authorWebsite.Services;


import com.example.authorWebsite.Models.Article;
import com.example.authorWebsite.Models.Author;
import com.example.authorWebsite.Repositories.ArticleRepository;
import com.example.authorWebsite.Services.Interfaces.ArticleService;
import com.example.authorWebsite.Services.Interfaces.AuthorService;
import com.opencsv.exceptions.CsvValidationException;
import io.swagger.annotations.Authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.io.FileReader;
import java.io.IOException;

import java.util.List;
import java.util.Optional;

import com.opencsv.CSVReader;



@Service
public class ArticleServiceImp implements ArticleService {


    @Autowired
    AuthorService authorService;
    @Autowired
    ArticleRepository articleRepository;
    @Override
    public boolean addArticle(Article article,int currentUserId) {
        Author currentAuthor=authorService.findAuthor(currentUserId);
        if(currentAuthor==null) return false;
        currentAuthor.setNumberOfArticles(currentAuthor.getNumberOfArticles()+1);
        authorService.updateAuthor(currentAuthor);
        articleRepository.save(article);
        return  true;
    }


    @Override
    public boolean updateArticle(Article article) {
        Article currentArticle=viewArticle(article.getId());

        if(currentArticle==null)return false;

        currentArticle.setAuthor(article.getAuthor());
        currentArticle.setContent(article.getContent());
        articleRepository.save(currentArticle);
        return  true;
    }

    @Override
    public boolean deleteArticle(int articleId) {
        Article currentArticle=viewArticle(articleId);

        if(currentArticle==null)return false;

        articleRepository.delete(currentArticle);
        return true;

    }

    @Override
    public List<Article> viewArticlesForAuthor(int authorId) {

        Optional<List<Article>> articles=articleRepository.findArticlesForAuthor(authorId);
        if(articles.isPresent()&&articles.get().size()>0)
          return   articles.get();
        return null;
    }

    @Override
    public Article viewArticle(int articleId) {

        Optional<Article>article=articleRepository.findById(articleId);
        if(article.isPresent())return article.get();
        return  null;

    }

    @Override
    public void loadSheet() throws IOException, CsvValidationException {
        String filePath = "E:\\mahmoud\\projects\\gp\\trainArticles.csv";
        CSVReader reader = new CSVReader(new FileReader(filePath));
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            String column1 = nextLine[0];
            String column2 = nextLine[1];
            System.out.println(column1 + ", " + column2);
        }

    }
}
