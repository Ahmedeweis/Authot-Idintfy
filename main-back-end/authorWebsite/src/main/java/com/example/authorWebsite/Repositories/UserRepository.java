package com.example.authorWebsite.Repositories;


import com.example.authorWebsite.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query(value = "select *from users u where u.email=:email",nativeQuery = true)
    public Optional<List<User>> findByEmail(String email);

    @Query(value = "select  *from users u where u.email=:email and u.password=:password",nativeQuery = true)
    public Optional<List<User>> login(String email,String password);


}
