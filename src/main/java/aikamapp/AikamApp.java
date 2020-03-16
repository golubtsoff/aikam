package aikamapp;

import aikamapp.controller.ControllerApp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class AikamApp {
    public static void main(String[] args) {
        try(ConfigurableApplicationContext ctx = SpringApplication.run(AikamApp.class, args)){
            ControllerApp controllerApp = (ControllerApp) ctx.getBean("controllerApp");
            controllerApp.run();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
