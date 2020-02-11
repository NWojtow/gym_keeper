package Gym_keeper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages={
        "Gym_keeper.config", "Gym_keeper.controler", "Gym_keeper.service"})
public class Application {
    public static void main(String[] args){

SpringApplication.run(Application.class, args);
    }
}
