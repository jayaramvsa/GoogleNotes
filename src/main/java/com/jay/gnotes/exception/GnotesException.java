package com.jay.gnotes.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GnotesException extends RuntimeException {

    private static final Long serialVersionUID = -3407636098889460334L;
    private  Integer statusCode;
    private String statusMessage;
}
