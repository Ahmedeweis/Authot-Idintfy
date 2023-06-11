package com.example.authorWebsite.Services;

import com.example.authorWebsite.Services.Interfaces.SendResponseEntity;
import com.example.authorWebsite.Services.Interfaces.SendResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SendResponseEntityImp implements SendResponseEntity {

    @Autowired
    SendResponseService sendResponseService;
    @Override
    public ResponseEntity SendResponseEntity(String status, String message, HttpStatus httpStatus) {
        return new ResponseEntity(sendResponseService.CreateResponse(
                status,message), httpStatus);
    }

    @Override
    public <T> ResponseEntity SendResponseEntityWithBody(String status, String message, HttpStatus httpStatus, T body) {
        return new ResponseEntity(sendResponseService.CreateResponseWithBody(
                status,message,body), httpStatus);
    }
}
