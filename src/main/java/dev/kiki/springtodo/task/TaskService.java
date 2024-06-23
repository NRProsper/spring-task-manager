package dev.kiki.springtodo.task;

import dev.kiki.springtodo.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public Task createTask(TaskCreationDTO taskCreationDTO) {
        var task = taskMapper.taskDtoToTask(taskCreationDTO);
        return taskRepository.save(task);
    }
    
    public List<TaskDTO> getAllTasksByUserId(Long userId) {
        return taskRepository.findAllByUserId(userId)
                .stream()
                .map(taskMapper::toTaskDTO)
                .collect(Collectors.toList());
    }

    public TaskDTO getTaskByIdAndUserId(Long taskId, Long userId) {
        return taskRepository.findByIdAndUserId(taskId, userId)
                .map(taskMapper::toTaskDTO)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public void deleteTask(Long id) {
        if(!taskRepository.existsById(id)) {
            throw  new ResourceNotFoundException("Task with id " + id + " not found");
        }
        taskRepository.deleteById(id);
    }

    public Task updateTask(Long id, Task task) {
        Optional<Task> existingTaskOpt = taskRepository.findById(id);
        if(!existingTaskOpt.isPresent()) {
            throw new  ResourceNotFoundException("Task with id " + id + " not found");
        }

        Task existingTask = existingTaskOpt.get();
        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setStatus(task.getStatus());

        return taskRepository.save(existingTask);
    }

    public Task updateStatusOnly(Long id, TaskStatusOnlyDTO taskStatusOnly) {
        Optional<Task> existingTaskOpt = taskRepository.findById(id);
        if(!existingTaskOpt.isPresent()) {
            throw new  ResourceNotFoundException("Task with id " + id + " not found");
        }

        var existingTask = existingTaskOpt.get();
        existingTask.setStatus(taskStatusOnly.status());

        return taskRepository.save(existingTask);
    }

}
