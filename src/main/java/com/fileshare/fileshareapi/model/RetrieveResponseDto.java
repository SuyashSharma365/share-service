package com.fileshare.fileshareapi.model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RetrieveResponseDto {
    private String message;
    private byte[] data;
}
