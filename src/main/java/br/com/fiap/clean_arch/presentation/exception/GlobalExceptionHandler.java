package br.com.fiap.clean_arch.presentation.exception;

import br.com.fiap.clean_arch.domain.exceptions.BusinessRuleViolationException;
import br.com.fiap.clean_arch.domain.exceptions.DomainException;
import br.com.fiap.clean_arch.domain.exceptions.ResouceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /*@ExceptionHandler(DomainException.class)
    public ResponseEntity<Map<String, String>> handleDomainException(DomainException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGenericException(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        error.put("localizedMessage", ex.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }*/
    @ExceptionHandler(BusinessRuleViolationException.class)
    public ProblemDetail handleBusiness(
            BusinessRuleViolationException ex,
            HttpServletRequest request){
            ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
            problem.setType(ProblemType.BUSINESS_ERROR.getUri());
            problem.setTitle(ProblemType.BUSINESS_ERROR.getTitle());
            problem.setDetail(ex.getMessage());
            problem.setInstance(URI.create(request.getRequestURI()));
        return problem;}
    @ExceptionHandler(ResouceNotFoundException.class)
    public ProblemDetail handleNotFound(
            ResouceNotFoundException ex,
            HttpServletRequest request){
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problem.setType(ProblemType.RESOUCE_NOT_FOUND.getUri());
        problem.setTitle(ProblemType.RESOUCE_NOT_FOUND.getTitle());
        problem.setDetail(ex.getMessage());
        problem.setInstance(URI.create(request.getRequestURI()));
        return problem;}
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest request){
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setType(ProblemType.VALIDATION_ERROR.getUri());
        problem.setTitle(ProblemType.VALIDATION_ERROR.getTitle());
        problem.setDetail(ex.getMessage());
        problem.setInstance(URI.create(request.getRequestURI()));

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error ->
                        errors.put(error.getField(), error.getDefaultMessage())
                );

        problem.setProperty("errors", errors);
        return problem;}
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGeneric(
            Exception ex,
            HttpServletRequest request) {

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problem.setType(ProblemType.INTERNAL_ERROR.getUri());
        problem.setTitle(ProblemType.INTERNAL_ERROR.getTitle());
        problem.setDetail("Erro inesperado. Contate o suporte.");
        problem.setInstance(URI.create(request.getRequestURI()));

        return problem;
    }


}
