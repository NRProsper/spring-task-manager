package dev.kiki.springtodo.task;

import org.springframework.stereotype.Service;

@Service
public class TaskMapper {

    public Task taskDtoToTask(TaskCreationDTO taskCreationDTO) {
        var task = new Task();
        task.setTitle(taskCreationDTO.title());
        task.setDescription(taskCreationDTO.description());

        return task;
    }

    public TaskDTO toTaskDTO(Task task) {
        return new TaskDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getUser().getId()
        );
    }

}
