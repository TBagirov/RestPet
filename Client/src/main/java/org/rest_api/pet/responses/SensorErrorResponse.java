package org.rest_api.pet.responses;

import java.time.LocalDateTime;

public class SensorErrorResponse {
    private String message;

    // тип данныех не LocalDateTime потому что это сильно усложняет десериализацию для Jackson
    private String  timestamp;

    public SensorErrorResponse() {}

    public SensorErrorResponse(String message, String timestamp) {
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

