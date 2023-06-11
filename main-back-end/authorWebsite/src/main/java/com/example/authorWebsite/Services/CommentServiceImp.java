package com.example.authorWebsite.Services;

import com.example.authorWebsite.Models.Comment;
import com.example.authorWebsite.Repositories.CommentRepository;
import com.example.authorWebsite.Services.Interfaces.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;


@Service
public class CommentServiceImp implements CommentService {

    @Autowired
    CommentRepository commentRepository;
    @Override
    public boolean addComment(Comment comment) {
        comment.setDate(new Date());
       commentRepository.save(comment);
       return true;
    }

    @Override
    public boolean deleteComment(int commentId) {

        Comment comment=findComment(commentId);
        if(comment==null) return false;

        commentRepository.delete(comment);
        return true;
    }

    @Override
    public Comment findComment(int commentId) {

        Optional<Comment> comment=commentRepository.findById(commentId);

        if(comment.isPresent())return comment.get();
        return null;

    }


}
