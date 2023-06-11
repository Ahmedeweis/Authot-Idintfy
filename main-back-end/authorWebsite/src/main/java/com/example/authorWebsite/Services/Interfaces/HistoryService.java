package com.example.authorWebsite.Services.Interfaces;

import com.example.authorWebsite.Models.History;

import java.util.List;

public interface HistoryService {
    public  void addToHistory(String action,int userId);
    public  boolean deleteFromHistory(int historyId);
    public List<History> viewHistory(int userId);
    public  History findHistory(int historyId);

}
