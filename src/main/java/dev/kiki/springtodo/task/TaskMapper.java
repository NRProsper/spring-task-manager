package dev.kiki.springtodo.task;

import org.springframework.stereotype.Service;

@Service
public class TaskMapper {

    public Task taskDtoToTask(TaskDTO taskDTO) {
        var task = new Task();
        task.setTitle(taskDTO.title());
        task.setDescription(taskDTO.description());

        return task;
    }

}
