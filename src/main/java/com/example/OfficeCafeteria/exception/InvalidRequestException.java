package com.example.OfficeCafeteria.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class InvalidRequestException extends RuntimeException{

    private String message;
}
