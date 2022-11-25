package dev.sharanggupta.favouritemovies;

import dev.sharanggupta.favouritemovies.service.TestService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class FavouriteMoviesApplication {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(FavouriteMoviesApplication.class, args);
        TestService testService = applicationContext.getBean(TestService.class);
        testService.print();
    }

}
