package dev.kiki.springtodo.task;

import dev.kiki.springtodo.common.ApiResponse;
import dev.kiki.springtodo.exception.ResourceNotFoundException;
import dev.kiki.springtodo.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;

    Logger logger = LoggerFactory.getLogger(TaskController.class);

    @PostMapping
    public ResponseEntity<ApiResponse<Task>> createTask(
            @Valid @RequestBody TaskCreationDTO taskCreationDTO
    ) {
        Task createdTask = taskService.createTask(taskCreationDTO);
        ApiResponse<Task> response = new ApiResponse<>(
                createdTask,
                "Task created Successfully",
                HttpStatus.CREATED.value()
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TaskDTO>>> getAllTasks() {
        var tasks = taskService.getAllTasksByUserId(userService.getAuthenticatedUserId());
        ApiResponse<List<TaskDTO>> response = new ApiResponse<>(
                tasks,
                "Tasks retrieved Successfully",
                HttpStatus.OK.value()
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<ApiResponse<Task>> getTask(@PathVariable("taskId") Long taskId) {
        Task task = taskService.getTaskById(taskId);
        ApiResponse<Task> response = new ApiResponse<>(
                task,
                "Task retrieved Sucessfully",
                HttpStatus.OK.value()
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable("taskId") Long taskId) {
        try {
            taskService.deleteTask(taskId);
            return ResponseEntity.noContent().build();
        }catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(
            @PathVariable("taskId") Long taskId,
            @RequestBody Task task
    ) {
        try {
            Task updatedTask = taskService.updateTask(taskId, task);
            return ResponseEntity.ok(updatedTask);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{taskId}")
    public ResponseEntity<Task> updateStatus(
            @PathVariable("taskId") Long taskId,
            @RequestBody TaskStatusOnlyDTO taskStatusOnly
    ) {
        try {
            Task updatedTask = taskService.updateStatusOnly(taskId, taskStatusOnly);
            return ResponseEntity.ok(updatedTask);
        }catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
