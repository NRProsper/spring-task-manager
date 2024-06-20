package dev.kiki.springtodo;

import com.github.javafaker.Faker;
import dev.kiki.springtodo.task.Task;
import dev.kiki.springtodo.task.TaskRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(TaskRepository taskRepository) {
        Faker faker = new Faker();
        return args -> {
            for (int i = 0; i < 5; i++) {
                var task = Task.builder()
                        .title(faker.lorem().sentence())
                        .description(faker.lorem().paragraph())
                        .build();
                taskRepository.save(task);
            }
        };
    }


}
