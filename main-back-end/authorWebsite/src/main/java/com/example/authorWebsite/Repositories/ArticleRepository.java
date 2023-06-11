package com.example.authorWebsite.Repositories;


import com.example.authorWebsite.Models.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Integer> {

    @Query(value = "select *from articles a where a.author_id=:authorId",nativeQuery = true)
    public Optional<List<Article>> findArticlesForAuthor(int authorId);

}
