package com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.rest.errorhandler;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.exceptions.AdapterException;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.exceptions.InvalidResourceException;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.rest.errorhandler.json.ApiErrorResponse;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.entities.exceptions.EntityException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

@ResponseBody
@ControllerAdvice
@RequiredArgsConstructor
public class CustomRestControllerErrorHandler {
//    private final String

    @ExceptionHandler(value = {InvalidResourceException.class})
    protected ResponseEntity<ApiErrorResponse> handleInvalidResourceException(Exception e){
        var status = HttpStatus.BAD_REQUEST;
        var msg = retrieveMessage(e);
        return buildResponseEntity(status, msg);
    }


    @ExceptionHandler(value = {EntityException.class})
    protected ResponseEntity<ApiErrorResponse> handleEntityException(Exception e){
        var status = HttpStatus.BAD_REQUEST;
        var msg = retrieveMessage(e);
        return buildResponseEntity(status, msg);
    }

    @ExceptionHandler(value = {AdapterException.class})
    protected ResponseEntity<ApiErrorResponse> handleAdapterException(Exception e){
        var status = HttpStatus.NOT_FOUND;
        var msg = retrieveMessage(e);
        return buildResponseEntity(status, msg);
    }



    private String retrieveMessage(Exception e) {
        return Objects.isNull(e.getCause()) ? e.getLocalizedMessage() : e.getCause().getMessage();
    }

    private ResponseEntity<ApiErrorResponse> buildResponseEntity(HttpStatus status, String msg) {
        return new ResponseEntity<>(new ApiErrorResponse(status, msg), status);
    }


}
