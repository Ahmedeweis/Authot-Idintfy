package com.example.authorWebsite.Models;


import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NonNull
    private String name;

    @JsonBackReference(value = "User")
    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
    Set<User>users;


}
