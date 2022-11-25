package dev.sharanggupta.favouritemovies.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    @Value("${omdb.api.key}")
    String apiKey;

    public void print() {
        System.out.println(apiKey);
    }
}
