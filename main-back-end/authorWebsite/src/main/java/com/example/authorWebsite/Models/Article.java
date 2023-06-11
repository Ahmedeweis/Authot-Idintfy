package com.example.authorWebsite.Models;


import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "articles")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Article {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int id;


    @NonNull
    @Column(columnDefinition = "TEXT")
    private String content;



    @Column(name = "author_id")
    private int author_id;

    @ManyToOne
    @JoinColumn(name = "author_id",nullable = false,insertable = false,updatable = false)
    @JsonIgnore
    Author author;

    @OneToMany(mappedBy = "article",cascade = CascadeType.ALL)
    Set<Comment>comments;

}
