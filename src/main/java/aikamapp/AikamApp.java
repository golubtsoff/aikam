package aikamapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AikamApp {
    public static void main(String[] args) {
        try(ConfigurableApplicationContext ctx = SpringApplication.run(AikamApp.class, args)){

        }
    }
}
