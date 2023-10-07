package com.example.authorWebsite.Services.Interfaces;


import com.example.authorWebsite.Models.Article;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface ArticleService {

    public boolean addArticle(Article article);
    public  boolean updateArticle(Article article);
    public  boolean deleteArticle(int articleId);
    public List<Article> viewArticlesForAuthor(int authorId);
    public  Article viewArticle(int articleId);

    public  void loadSheet() throws IOException, CsvValidationException;

}
