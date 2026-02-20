package com.albertsilva.cursomc.resources.exception;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.albertsilva.cursomc.services.exceptions.BusinessException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /*
     * ======================================================
     * DOMAIN / BUSINESS EXCEPTIONS
     * ======================================================
     */
    @ExceptionHandler(BusinessException.class)
    public ProblemDetail handleBusinessException(BusinessException ex, HttpServletRequest request) {

        HttpStatusCode status = HttpStatusCode.valueOf(ex.getStatus());

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(status, ex.getMessage());

        problem.setTitle(resolveTitle(status.value()));

        enrich(problem, request);

        return problem;
    }

    /*
     * ======================================================
     * BEAN VALIDATION (DTO Validation)
     * ======================================================
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request) {

        HttpStatusCode status = HttpStatusCode.valueOf(422); // UNPROCESSABLE_CONTENT

        ProblemDetail problem = ProblemDetail.forStatus(status);
        problem.setTitle("Validation error");
        problem.setDetail("One or more fields are invalid.");

        Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage,
                        (existing, replacement) -> existing));

        problem.setProperty("errors", errors);

        enrich(problem, request);

        return problem;
    }

    /*
     * ======================================================
     * SPRING DATA INTEGRITY (Infraestrutura)
     * ======================================================
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail handleSpringDataIntegrity(DataIntegrityViolationException ex, HttpServletRequest request) {

        HttpStatusCode status = HttpStatusCode.valueOf(409);

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(status,
                "Database constraint violation.");

        problem.setTitle("Data integrity violation");

        enrich(problem, request);

        return problem;
    }

    /*
     * ======================================================
     * GENERIC / UNEXPECTED
     * ======================================================
     */
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGenericException(Exception ex, HttpServletRequest request) {

        HttpStatusCode status = HttpStatusCode.valueOf(500);

        ProblemDetail problem = ProblemDetail.forStatus(status);

        problem.setTitle("Unexpected internal error");
        problem.setDetail("An unexpected error occurred.");

        enrich(problem, request);

        return problem;
    }

    /*
     * ======================================================
     * COMMON ENRICHMENT (Metadata)
     * ======================================================
     */
    private void enrich(ProblemDetail problem, HttpServletRequest request) {

        problem.setProperty("timestamp", OffsetDateTime.now());
        problem.setProperty("path", request.getRequestURI());
        problem.setProperty("method", request.getMethod());
        problem.setProperty("traceId", UUID.randomUUID().toString());
    }

    /*
     * ======================================================
     * OPTIONAL: Custom Titles by Status
     * ======================================================
     */
    private String resolveTitle(int status) {
        return switch (status) {
            case 400 -> "Business rule violation";
            case 404 -> "Resource not found";
            case 409 -> "Conflict";
            case 422 -> "Unprocessable content";
            default -> "Application error";
        };
    }
}