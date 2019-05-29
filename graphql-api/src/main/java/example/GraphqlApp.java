package example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "example")
public class GraphqlApp {
    public static void main(String[] args) {
        SpringApplication.run(GraphqlApp.class, args);
    }

}