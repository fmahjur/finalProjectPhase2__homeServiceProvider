package ir.maktab.HomeServiceProvider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableJpaAuditing
public class FinalProjectPhase2Application {

    public static void main(String[] args) {
        SpringApplication.run(FinalProjectPhase2Application.class, args);
    }

    @GetMapping
    public String helloWorld() {
        return "Hello World!";
    }

}
