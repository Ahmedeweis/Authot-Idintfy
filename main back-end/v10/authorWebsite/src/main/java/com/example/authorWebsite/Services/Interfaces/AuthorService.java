package com.example.authorWebsite.Services.Interfaces;


import com.example.authorWebsite.Models.Author;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface AuthorService {

    public boolean addAuthor(Author author);
    public boolean updateAuthor(Author author);
    public  boolean deleteAuthor(int authorId);
    public Author findAuthor(int authoId);
    public List<Author> viewAllAuthors();
    public ResponseEntity<String> identifyAuthor(String article) throws IOException;
    public Author findAuthorByName(String name);

    public String readPdf(String filePath) throws IOException;
}
