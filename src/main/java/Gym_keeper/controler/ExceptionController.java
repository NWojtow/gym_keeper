package Gym_keeper.controler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.xml.ws.Response;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {EntityNotFoundException.class})
    protected ResponseEntity handleNotFoundException(EntityNotFoundException e){
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value={RuntimeException.class})
    protected ResponseEntity handleRuntimeException(RuntimeException e){
        return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value={EntityExistsException.class})
    protected ResponseEntity handleEntityExistsException(EntityExistsException e){
        return new ResponseEntity(null, HttpStatus.CONFLICT);
    }
}
