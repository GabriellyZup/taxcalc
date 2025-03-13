////package com.taxcalc.exception;
////
////import org.springframework.http.*;
////import org.springframework.web.bind.MethodArgumentNotValidException;
////import org.springframework.web.bind.annotation.ControllerAdvice;
////import org.springframework.web.bind.annotation.ExceptionHandler;
////import org.springframework.web.bind.annotation.RestControllerAdvice;
////import org.springframework.web.context.request.WebRequest;
////import org.springframework.web.server.ResponseStatusException;
////
////import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
////import java.util.stream.Collectors;
////
////import io.swagger.v3.oas.annotations.Hidden;
////
////@Hidden
////@RestControllerAdvice
////
////@ControllerAdvice
////public class GlobalExceptionHandler extends ResponseEntityExceptionHandler { // 1. Herda classe base
////
////    // Captura ResponseStatusException (ex: lançadas nos controllers)
////    @ExceptionHandler(ResponseStatusException.class)
////    public ResponseEntity<ErrorResponse> handleResponseStatusException(
////            ResponseStatusException ex, WebRequest request
////    ) {
////        HttpStatusCode statusCode = ex.getStatusCode();
////        HttpStatus status = HttpStatus.resolve(statusCode.value()); // Converte para HttpStatus
////        String reason = ex.getReason() != null ? ex.getReason() : "Erro desconhecido";
////
////        ErrorResponse error = new ErrorResponse(
////                status != null ? status : HttpStatus.INTERNAL_SERVER_ERROR,
////                reason,
////                request.getDescription(false).replace("uri=", "")
////        );
////
////        return new ResponseEntity<>(error, statusCode);
////    }
////
//////    // 2. Trata ResponseStatusException
//////    @ExceptionHandler(ResponseStatusException.class)
//////    public ProblemDetail handleResponseStatusException(ResponseStatusException ex) { // 3. Usa ProblemDetail
//////        ProblemDetail problemDetail = ProblemDetail.forStatus(ex.getStatusCode());
//////        problemDetail.setTitle(ex.getReason());
//////        return problemDetail;
//////    }
////
////    // Captura erros de validação (ex: @Valid em DTOs)
////    @ExceptionHandler(MethodArgumentNotValidException.class)
////    public ResponseEntity<ErrorResponse> handleValidationExceptions(
////            MethodArgumentNotValidException ex, WebRequest request
////    ) {
////        String errorMessage = ex.getBindingResult()
////                .getFieldErrors()
////                .stream()
////                .map(error -> error.getField() + ": " + error.getDefaultMessage())
////                .findFirst()
////                .orElse(ex.getMessage());
////
////        ErrorResponse error = new ErrorResponse(
////                HttpStatus.BAD_REQUEST,
////                errorMessage,
////                request.getDescription(false).replace("uri=", "")
////        );
////        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
////    }
////
//////    // 4. Substitui tratamento padrão de validação
//////    @Override
//////    protected ResponseEntity<Object> handleMethodArgumentNotValid(
//////            MethodArgumentNotValidException ex,
//////            HttpHeaders headers,
//////            HttpStatusCode status,
//////            WebRequest request
//////    ) {
//////        String errors = ex.getBindingResult()
//////                .getFieldErrors()
//////                .stream()
//////                .map(error -> error.getField() + ": " + error.getDefaultMessage())
//////                .collect(Collectors.joining("; "));
//////
//////        ProblemDetail problemDetail = ProblemDetail.forStatus(status);
//////        problemDetail.setTitle("Erro de validação");
//////        problemDetail.setDetail(errors);
//////        return new ResponseEntity<>(problemDetail, headers, status);
//////    }
////
//////    // Captura outras exceções não tratadas
//////    @ExceptionHandler(Exception.class)
//////    public ResponseEntity<ErrorResponse> handleGlobalExceptions(
//////            Exception ex, WebRequest request
//////    ) {
//////        ErrorResponse error = new ErrorResponse(
//////                HttpStatus.INTERNAL_SERVER_ERROR,
//////                "Ocorreu um erro interno no servidor",
//////                request.getDescription(false).replace("uri=", "")
//////        );
//////        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
//////    }
////
////    // 5. Trata exceções genéricas
////    @ExceptionHandler(Exception.class)
////    public ProblemDetail handleAllExceptions(Exception ex) {
////        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
////        problemDetail.setTitle("Erro interno");
////        problemDetail.setDetail(ex.getMessage());
////        return problemDetail;
////    }
////}
//
//package com.taxcalc.exception;
//
//import org.springframework.http.*;
////import org.springframework.security.access.AccessDeniedException;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.server.ResponseStatusException;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import java.nio.file.AccessDeniedException;
//import java.util.stream.Collectors;
//
//@ControllerAdvice(basePackages = "com.taxcalc.controller") // 1. Restringe ao seu pacote
//public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
//
//    // 2. Trata ResponseStatusException
//    @ExceptionHandler(ResponseStatusException.class)
//    public ProblemDetail handleResponseStatusException(ResponseStatusException ex) {
//        ProblemDetail problemDetail = ProblemDetail.forStatus(ex.getStatusCode());
//        problemDetail.setTitle(ex.getReason());
//        return problemDetail;
//    }
//
//    // 3. Trata erros de validação (@Valid)
//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(
//            MethodArgumentNotValidException ex,
//            HttpHeaders headers,
//            HttpStatusCode status,
//            WebRequest request
//    ) {
//        String errors = ex.getBindingResult()
//                .getFieldErrors()
//                .stream()
//                .map(error -> error.getField() + ": " + error.getDefaultMessage())
//                .collect(Collectors.joining(", "));
//
//        ProblemDetail problemDetail = ProblemDetail.forStatus(status);
//        problemDetail.setTitle("Erro de validação");
//        problemDetail.setDetail(errors);
//        return ResponseEntity.status(status).body(problemDetail);
//    }
//
//    // 4. Trata exceções genéricas (evita conflito com Swagger)
//    @ExceptionHandler(Exception.class)
//    public ProblemDetail handleAllExceptions(Exception ex, WebRequest request) {
//        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
//        problemDetail.setTitle("Erro interno");
//        problemDetail.setDetail("Mensagem técnica: " + ex.getMessage()); // Evite expor detalhes internos
//        return problemDetail;
//    }
//    @ExceptionHandler(UnauthorizedAccessException.class)
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    public ProblemDetail handleUnauthorizedAccess(UnauthorizedAccessException ex) {
//        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.FORBIDDEN);
//        problemDetail.setTitle("Acesso negado");
//        problemDetail.setDetail("Você não tem permissão para acessar este recurso");
//        return problemDetail;
//    }
//
//
//}