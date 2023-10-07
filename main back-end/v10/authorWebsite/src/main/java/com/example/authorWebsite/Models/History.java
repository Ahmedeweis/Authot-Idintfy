package com.example.authorWebsite.Models;


import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "history")
@Setter
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String action;
    private Date date;


    @Column(name = "user_id")
    private int user_id;


    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false,insertable = false,updatable = false)
    @JsonIgnore
    User user;

}
