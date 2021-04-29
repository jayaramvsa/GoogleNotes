package com.jay.gnotes.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Response {
    private Integer statusCode;
    private String statusMessage;
    private Object data;

}
