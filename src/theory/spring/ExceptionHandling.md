Global exception handling allows you to manage exceptions at a global level, meaning you can define how all exceptions
should be handled throughout the application. This can be done using the @ControllerAdvice annotation in combination
with @ExceptionHandler.

Steps for Global Exception Handling
Create a Custom Exception Class (optional): Define custom exceptions that your application may throw.

public class ResourceNotFoundException extends RuntimeException {
public ResourceNotFoundException(String message) {
super(message);
}
}

Create an Exception Handler Using @ControllerAdvice: You can define a global exception handler that will catch
exceptions thrown across all controllers in the application.

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // You can also handle other exceptions here as needed

}

@ControllerAdvice: This annotation marks the class as an exception handler that applies to all controllers in the Spring
application.
@ExceptionHandler: This annotation is used to specify which exception the method should handle. In this case,
ResourceNotFoundException and Exception.
Customize the Response: You can return more detailed error information (e.g., a custom error object) in the
ResponseEntity to provide better responses to the client.

Example:
public class ErrorResponse {
private String message;
private long timestamp;

    public ErrorResponse(String message, long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    // Getters and setters

}

@ExceptionHandler(ResourceNotFoundException.class)
public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {
ErrorResponse error = new ErrorResponse(ex.getMessage(), System.currentTimeMillis());
return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
}

Handling Validation Errors (e.g., using @Valid): For validation errors that occur in your request bodies (e.g.,
@RequestBody), Spring has built-in support for handling constraint violations.

@RestController
public class MyController {

    @PostMapping("/user")
    public ResponseEntity<String> createUser(@Valid @RequestBody User user) {
        return new ResponseEntity<>("User created", HttpStatus.CREATED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(error -> error.getDefaultMessage())
            .collect(Collectors.toList());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}


// we can have multipule @controllerAdvice , spring will maintain this but spring dosent maintain the orde rso  we can use @Order  to tell spring 
give this this preference to this one 