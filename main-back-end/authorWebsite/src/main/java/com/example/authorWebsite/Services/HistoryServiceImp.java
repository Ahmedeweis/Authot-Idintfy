package com.example.authorWebsite.Services;

import com.example.authorWebsite.Models.History;
import com.example.authorWebsite.Repositories.HistoryRepository;
import com.example.authorWebsite.Services.Interfaces.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.Option;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class HistoryServiceImp implements HistoryService {


    @Autowired
    HistoryRepository historyRepository;

    @Autowired
    HttpServletRequest httpRequest;
    @Override
    public void addToHistory(String action,int userId) {

        History history=History.builder().date(new Date()).user_id(userId).action(action).build();
        historyRepository.save(history);
    }

    @Override
    public boolean deleteFromHistory(int historyId) {

        History history=findHistory(historyId);
        if(history==null)return false;
        historyRepository.delete(history);
        return true;

    }

    @Override
    public List<History> viewHistory(int userId) {
        return historyRepository.viewHistory(userId);
    }

    @Override
    public History findHistory(int historyId) {

        Optional<History> history=historyRepository.findById(historyId);
        if(history.isPresent())return history.get();
        return  null;
    }




}
