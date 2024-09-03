package org.rest_api.pet.responses;


public class MeasurementErrorResponse {


    private String message;
    private String timestamp;

    public MeasurementErrorResponse(){}
    public MeasurementErrorResponse(String message, String timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }


}