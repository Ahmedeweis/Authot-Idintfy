package com.example.authorWebsite.Services.Interfaces;

import com.example.authorWebsite.Models.Responses.SendResponse;
import com.example.authorWebsite.Models.Responses.SendResponseWithObject;

import org.springframework.stereotype.Component;


public interface SendResponseService {

    public SendResponse CreateResponse(String status, String message);
    public<T> SendResponseWithObject CreateResponseWithBody(String status , String message,T body);

}
