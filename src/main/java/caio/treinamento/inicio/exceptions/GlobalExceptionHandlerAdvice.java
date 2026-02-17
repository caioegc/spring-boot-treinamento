package caio.treinamento.inicio.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandlerAdvice {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<DefaultErrorMensagem> handlerNotFoundException(NotFoundException e) {
        var erroeNotFound = new DefaultErrorMensagem(HttpStatus.NOT_FOUND.value(), e.getReason());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erroeNotFound);
    }
}

//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public ResponseEntity<DefaultErrorMensagem> handlerDataIntegrityViolationException(DataIntegrityViolationException e){
//        var erroeNotFound = new DefaultErrorMensagem(HttpStatus.BAD_REQUEST.value(), "Email duplicado");
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroeNotFound);
//    }
//}
