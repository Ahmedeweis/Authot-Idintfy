package com.example.authorWebsite.Services;

import com.example.authorWebsite.Models.Responses.SendResponse;
import com.example.authorWebsite.Models.Responses.SendResponseWithObject;
import com.example.authorWebsite.Services.Interfaces.SendResponseService;
import org.springframework.stereotype.Service;


@Service
public class SendResponseServiceImp<T> implements SendResponseService {
    @Override
    public SendResponse CreateResponse(String status, String message) {
        return  new SendResponse(status,message);
    }

    @Override
    public <T> SendResponseWithObject CreateResponseWithBody(String status, String message, T body) {
        return new SendResponseWithObject<T>(status,message,body);
    }


}
