package com.example.authorWebsite.Models.Responses;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SendResponseWithObject<T> extends SendResponse{


    private T body;
   public SendResponseWithObject(String status, String message,T body) {
        super(status, message);
        this.body=body;
    }
}
