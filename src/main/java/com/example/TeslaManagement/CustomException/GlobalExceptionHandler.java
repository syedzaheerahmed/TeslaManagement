package com.example.TeslaManagement.CustomException;

//@ControllerAdvice
//public class GlobalExceptionHandler {
//    @ExceptionHandler(ReferenceNotFoundException.class)
//    public ResponseEntity<ErrorResponse> handleReferenceNotFound(ReferenceNotFoundException ex) {
//        ErrorResponse error = new ErrorResponse(
//                HttpStatus.NOT_FOUND.value(),
//                ex.getMessage(),
//                LocalDateTime.now()
//        );
//        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<List<ErrorResponse>> handleValidationExceptions(
//            MethodArgumentNotValidException ex) {
//        List<ErrorResponse> errors = ex.getBindingResult()
//                .getFieldErrors()
//                .stream()
//                .map(err -> new ErrorResponse(
//                        HttpStatus.BAD_REQUEST.value(),
//                        err.getDefaultMessage(),
//                        LocalDateTime.now()
//                ))
//                .collect(Collectors.toList());
//
//        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//    }
//}
