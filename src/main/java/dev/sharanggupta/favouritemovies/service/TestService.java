package dev.sharanggupta.favouritemovies.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/*
This service is used only during development to test any functionality, feature or behaviour
 */
@Service
public class TestService {
  @Value("${omdb.api.key}")
  String apiKey;

  private static final Logger LOGGER = LoggerFactory.getLogger(TestService.class);

  public void print() {
    LOGGER.info(apiKey);
  }
}
