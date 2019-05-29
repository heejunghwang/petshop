package example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "example")
public class RestApiApp {
    public static void main(String[] args) {
        SpringApplication.run(RestApiApp.class, args);
    }

}