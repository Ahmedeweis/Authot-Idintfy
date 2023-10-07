package com.example.authorWebsite.Models;



import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "authors")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private int numberOfArticles;


    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    @JsonIgnore
    Set<Article>articles;


}
