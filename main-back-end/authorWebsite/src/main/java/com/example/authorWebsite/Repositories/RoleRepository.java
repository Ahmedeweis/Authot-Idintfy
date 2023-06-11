package com.example.authorWebsite.Repositories;

import com.example.authorWebsite.Models.Role;
import com.example.authorWebsite.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    @Query(value = "select *from roles r where r.name=:name",nativeQuery = true)
    public Optional<List<Role>> findByName(String name);
}
