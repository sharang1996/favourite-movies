package dev.sharanggupta.favouritemovies.exceptionhandler;

import dev.sharanggupta.favouritemovies.controller.MovieController;
import dev.sharanggupta.favouritemovies.exception.MovieValidationException;
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

@ControllerAdvice(basePackageClasses = MovieController.class)
@Order(Ordered.LOWEST_PRECEDENCE - 1)
public class MovieExceptionHandler {
  private static final Logger LOGGER = LoggerFactory.getLogger(MovieExceptionHandler.class);

  @ExceptionHandler(MovieValidationException.class)
  public ResponseEntity<String> handleException(
      MovieValidationException movieValidationException, ServletWebRequest servletWebRequest) {
    String requestDetails = extractRequestDetails(servletWebRequest);
    LOGGER.info(
        "Error message={} Cause={}, Request={}",
        movieValidationException.getMessage(),
        movieValidationException.getCause(),
        requestDetails);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(movieValidationException.getMessage());
  }

  private String extractRequestDetails(ServletWebRequest servletWebRequest) {
    if (servletWebRequest == null) return "unknown";

    HttpServletRequest httpServletRequest = servletWebRequest.getRequest();
    return String.format(
        "User=%s, HTTP Method=%s, URI=%s, Address=%s",
        httpServletRequest.getRemoteUser(),
        httpServletRequest.getMethod(),
        httpServletRequest.getRequestURI(),
        httpServletRequest.getRemoteAddr());
  }
}
