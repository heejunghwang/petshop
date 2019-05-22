package example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by hwang on 21/05/2019.
 */
@SpringBootApplication(scanBasePackages = "example")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}