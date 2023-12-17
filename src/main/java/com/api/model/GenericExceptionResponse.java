package com.api.model;


import java.util.Date;


public record GenericExceptionResponse(String errorMessage,Date errorDate) {
}
