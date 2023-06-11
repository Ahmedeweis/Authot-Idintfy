package com.example.authorWebsite.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NonNull
    private String content;

    @JsonIgnore
    private Date date;

    @Column(name = "article_id")
    private int article_id;

    @ManyToOne
    @JoinColumn(name = "article_id",nullable = false,insertable = false,updatable = false)
    @JsonIgnore
    Article article;


}
