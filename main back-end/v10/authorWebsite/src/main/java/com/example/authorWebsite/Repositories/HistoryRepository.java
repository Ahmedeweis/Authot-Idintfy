package com.example.authorWebsite.Repositories;


import com.example.authorWebsite.Models.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History , Integer> {


    @Query(value = "select *from history h where h.user_id=:userId order by h.date asc",nativeQuery = true)
    public List<History> viewHistory(int userId);

}
