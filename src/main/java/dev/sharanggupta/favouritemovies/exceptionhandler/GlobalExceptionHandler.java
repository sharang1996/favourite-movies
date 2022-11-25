package dev.sharanggupta.favouritemovies.exceptionhandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;

@ControllerAdvice(basePackages = "dev.sharanggupta.favouritemovies.controller")
@Order(Ordered.LOWEST_PRECEDENCE - 1)
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleException(NoSuchElementException noSuchElementException, ServletWebRequest servletWebRequest) {
        String requestDetails = extractRequestDetails(servletWebRequest);
        LOGGER.info("Error message={} Cause={}, Request={}",
                noSuchElementException.getMessage(),
                noSuchElementException.getCause(),
                requestDetails);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(noSuchElementException.getMessage());
    }

    private String extractRequestDetails(ServletWebRequest servletWebRequest) {
        if (servletWebRequest == null) return "unknown";
        if (servletWebRequest.getRequest() == null) return "unknown";

        HttpServletRequest httpServletRequest = servletWebRequest.getRequest();
        return String.format("User=%s, HTTP Method=%s, URI=%s, Address=%s",
                httpServletRequest.getRemoteUser(),
                httpServletRequest.getMethod(),
                httpServletRequest.getRequestURI(),
                httpServletRequest.getRemoteAddr());
    }
}
