package dev.kiki.springtodo;

import com.github.javafaker.Faker;
import dev.kiki.springtodo.task.Status;
import dev.kiki.springtodo.task.Task;
import dev.kiki.springtodo.task.TaskRepository;
import dev.kiki.springtodo.user.User;
import dev.kiki.springtodo.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableJpaAuditing
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /*
    @Bean
    CommandLineRunner commandLineRunner(
            TaskRepository taskRepository,
            UserRepository userRepository
            ) {
        Faker faker = new Faker();
        return args -> {
            for(int i = 0; i < 10; i++) {
                User user = new User();
                user.setFirstName(faker.name().firstName());
                user.setLastName(faker.name().lastName());
                user.setEmail(faker.internet().safeEmailAddress());
                user.setPassword(faker.internet().password());
                List<Task> tasks = new ArrayList<>();
                for (int j = 0; j < 5; j++) {
                    Task task = Task.builder()
                            .title(faker.lorem().sentence())
                            .description(faker.lorem().paragraph())
                            .status(Status.TODO)
                            .user(user)
                            .build();
                    tasks.add(task);
                }
                user.setTasks(tasks);
                userRepository.save(user);
                taskRepository.saveAll(tasks);
            }
        };
    }
     */


}
