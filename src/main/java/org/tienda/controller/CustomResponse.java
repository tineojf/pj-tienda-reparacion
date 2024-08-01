package org.tienda.controller;

import lombok.Getter;

@Getter
public class CustomResponse {
    private final Boolean ok;
    private final String message;
    private final Object data;

    public CustomResponse(Boolean ok, String message, Object data) {
        this.ok = ok;
        this.message = message;
        this.data = data;
    }
}
