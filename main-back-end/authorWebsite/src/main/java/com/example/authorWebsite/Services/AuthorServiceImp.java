package com.example.authorWebsite.Services;

import com.example.authorWebsite.Models.Author;
import com.example.authorWebsite.Repositories.AuthorRepository;
import com.example.authorWebsite.Services.Interfaces.AuthorService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;


@Service
public class AuthorServiceImp implements AuthorService {

    @Autowired
    AuthorRepository authorRepository;
    @Override
    public boolean addAuthor(Author author) {
        authorRepository.save(author);
        return true;
    }

    @Override
    public boolean updateAuthor(Author author) {

        Author currentAuthor=findAuthor(author.getId());
        if(currentAuthor==null)return  false;

        currentAuthor.setName(author.getName());
        currentAuthor.setNumberOfArticles(author.getNumberOfArticles());
        authorRepository.save(currentAuthor);
        return true;
    }

    @Override
    public boolean deleteAuthor(int authorId) {
        Author currentAuthor=findAuthor(authorId);
        if(currentAuthor==null)return  false;

        authorRepository.delete(currentAuthor);
        return  true;
    }

    @Override
    public Author findAuthor(int authorId) {

        Optional<Author> author=authorRepository.findById(authorId);
        if(author.isPresent())return  author.get();
        return  null;
    }

    @Override
    public List<Author> viewAllAuthors() {
       return authorRepository.findAll();
    }

    @Override
    public ResponseEntity<String> identifyAuthor(String article) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8089/authorApi/predictAuthor/?article="+article;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return  response;
    }

    public String readPdf(String filePath) throws IOException {
        File file = null;
        if (filePath.startsWith("file:///")) {
            file = new File(new URL(filePath).getFile());
        } else {
            file = new File(filePath);
        }
        PDDocument document = PDDocument.load(file);
        PDFTextStripper stripper = new PDFTextStripper();
        String text = stripper.getText(document);
        text = text.replace("\n", "").replace("\r", "");
        document.close();
        return text;
    }
    @Override
    public Author findAuthorByName(String name) {

        Author author=authorRepository.findAuthorByName(name);
        return  author;

    }
}
