package com.albertsilva.cursomc.resources.exception;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.albertsilva.cursomc.services.exceptions.BusinessException;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Handler global responsável por interceptar exceções da aplicação
 * e convertê-las para o padrão Problem Details (RFC 7807).
 *
 * <p>
 * Centraliza o tratamento de erros garantindo:
 * </p>
 * <ul>
 * <li>Padronização da resposta HTTP</li>
 * <li>Separação entre domínio e infraestrutura</li>
 * <li>Enriquecimento com metadados (timestamp, traceId, etc.)</li>
 * <li>Logging estruturado para observabilidade</li>
 * </ul>
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /*
     * ======================================================
     * DOMAIN / BUSINESS EXCEPTIONS
     * ======================================================
     */

    /**
     * Trata exceções de regra de negócio.
     *
     * <p>
     * Representam violações de invariantes de domínio.
     * O status HTTP é definido pela própria exceção.
     * </p>
     */
    @ExceptionHandler(BusinessException.class)
    public ProblemDetail handleBusinessException(BusinessException ex, HttpServletRequest request) {

        HttpStatusCode status = HttpStatusCode.valueOf(ex.getStatus());

        logger.warn("Business exception: {}", ex.getMessage());

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

    /**
     * Trata erros de validação de entrada (DTO).
     *
     * <p>
     * Retorna status 422 com detalhes por campo inválido.
     * </p>
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationException(MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        HttpStatusCode status = HttpStatusCode.valueOf(422);

        logger.warn("Validation error on path {}: {}", request.getRequestURI(), ex.getMessage());

        ProblemDetail problem = ProblemDetail.forStatus(status);
        problem.setTitle("Validation error");
        problem.setDetail("One or more fields are invalid.");

        Map<String, String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        FieldError::getDefaultMessage,
                        (existing, replacement) -> existing));

        problem.setProperty("errors", errors);

        enrich(problem, request);

        return problem;
    }

    /*
     * ======================================================
     * DATA INTEGRITY (INFRASTRUCTURE)
     * ======================================================
     */

    /**
     * Trata violações de integridade do banco de dados.
     *
     * <p>
     * Exemplo: tentativa de exclusão com chave estrangeira vinculada.
     * </p>
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail handleSpringDataIntegrity(DataIntegrityViolationException ex,
            HttpServletRequest request) {

        HttpStatusCode status = HttpStatusCode.valueOf(409);

        logger.error("Database constraint violation", ex);

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                status,
                "Database constraint violation.");

        problem.setTitle("Data integrity violation");

        enrich(problem, request);

        return problem;
    }

    /*
     * ======================================================
     * GENERIC / UNEXPECTED ERRORS
     * ======================================================
     */

    /**
     * Fallback para exceções não tratadas.
     *
     * <p>
     * Nunca expõe detalhes internos ao cliente.
     * </p>
     */
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGenericException(Exception ex, HttpServletRequest request) {

        HttpStatusCode status = HttpStatusCode.valueOf(500);

        logger.error("Unexpected error occurred", ex);

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

    /**
     * Enriquecimento comum aplicado a todas as respostas de erro.
     *
     * <p>
     * Adiciona:
     * </p>
     * <ul>
     * <li>timestamp</li>
     * <li>path</li>
     * <li>HTTP method</li>
     * <li>traceId (correlação para logs distribuídos)</li>
     * </ul>
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

    /**
     * Resolve títulos amigáveis baseados no status HTTP.
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