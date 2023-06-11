package com.example.authorWebsite.Models.Responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SendResponse {

    private String status;
    private String message;

    public SendResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
