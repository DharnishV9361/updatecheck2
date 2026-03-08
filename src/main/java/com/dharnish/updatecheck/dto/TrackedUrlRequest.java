package com.dharnish.updatecheck.dto;

import lombok.Data;

@Data
public class TrackedUrlRequest {

    private String url;
    private Long userId;

}