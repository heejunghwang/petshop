package example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "example")
public class CoreApp {
    public static void main(String[] args) {
        SpringApplication.run(CoreApp.class, args);
    }

}