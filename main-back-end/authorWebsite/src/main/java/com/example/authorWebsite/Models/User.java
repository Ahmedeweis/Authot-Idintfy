package com.example.authorWebsite.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NonNull
    private String email;

    @NonNull
    private String password;
    @NonNull
    private String name;
    @NonNull
    private int age;


    @ManyToMany
    @JoinTable(name = "user_roles",
            joinColumns =@JoinColumn(name = "user_id")
            ,inverseJoinColumns = @JoinColumn(name = "role_id"))
    Set<Role> roles;


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    Set<History>histories;


}
