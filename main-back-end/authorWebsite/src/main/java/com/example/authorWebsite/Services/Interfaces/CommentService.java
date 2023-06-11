package com.example.authorWebsite.Services.Interfaces;

import com.example.authorWebsite.Models.Comment;

public interface CommentService {

    public boolean addComment(Comment comment);
    public boolean deleteComment(int commentId);
    public Comment findComment(int commentId);

}
