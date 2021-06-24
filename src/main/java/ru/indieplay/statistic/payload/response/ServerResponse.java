package ru.indieplay.statistic.payload.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by Fadesml on 23.05.2021.
 */

@Getter
@Setter
public class ServerResponse<T> {
    private boolean success;
    private String message;
    private T body;

    private ServerResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    private ServerResponse(boolean success, String message, T body) {
        this.success = success;
        this.message = message;
        this.body = body;
    }

    private ServerResponse(boolean success, T body) {
        this.success = success;
        this.body = body;
    }

    @SneakyThrows
    public static ResponseEntity<?> response(boolean success, String message, HttpStatus status) {
        return new ResponseEntity<>(new ObjectMapper().writeValueAsString(new ServerResponse<>(success, message)), status);
    }

    @SneakyThrows
    public static <T> ResponseEntity<?> response(boolean success, String message, T body, HttpStatus status) {
        return new ResponseEntity<>(new ObjectMapper().writeValueAsString(new ServerResponse<>(success, message, body)), status);
    }

    @SneakyThrows
    public static <T> ResponseEntity<?> response(boolean success, T body, HttpStatus status) {
        return new ResponseEntity<>(new ObjectMapper().writeValueAsString(new ServerResponse<>(success, body)), status);
    }
}
