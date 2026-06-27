package com.example.OfficeCafeteria.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DuplicateResourceException extends RuntimeException {

    private String message;
}
