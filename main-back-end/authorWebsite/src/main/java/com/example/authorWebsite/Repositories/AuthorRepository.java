package com.example.authorWebsite.Repositories;

import com.example.authorWebsite.Models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {


    @Query(value = "select *from authors a where a.name=:name",nativeQuery = true)
    public Author findAuthorByName(String name);

}
