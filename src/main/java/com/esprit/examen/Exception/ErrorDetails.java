package com.esprit.examen.Exception;

import java.util.Date;

public class ErrorDetails{

    Date timeStamp;
    String message;
    String details;
    Object errors;

    public ErrorDetails(Date timeStamp, String message, String details) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.details = details;
    }

    public ErrorDetails(String message) {
        this.message = message;
    }

    public ErrorDetails(Date timeStamp, String message, Object errors) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.errors = errors;
    }



    public Object getErrors() {
        return errors;
    }

    public void setErrors(Object errors) {
        this.errors = errors;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
