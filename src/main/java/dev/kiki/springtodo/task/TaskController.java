package dev.kiki.springtodo.task;

import dev.kiki.springtodo.common.ApiResponse;
import dev.kiki.springtodo.exception.ResourceNotFoundException;
import dev.kiki.springtodo.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tasks")
@SecurityRequirement(name = "auth")
@Tag(name = "Task Controller")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;


    @Operation(
            description = "Post end-point for Task",
            summary = "This end-point will help inserting the task into the database"
    )
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

    @Operation(
            description = "Get end-point for Task",
            summary = "This end-point will help getting allTasks"
    )
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

    @Operation(
            description = "Get end-point for Task",
            summary = "This end-point will help getting a single task"
    )
    @GetMapping("/{taskId}")
    public ResponseEntity<ApiResponse<TaskDTO>> getTask(@PathVariable("taskId") Long taskId) {
        TaskDTO task = taskService.getTaskByIdAndUserId(taskId, userService.getAuthenticatedUserId());
        ApiResponse<TaskDTO> response = new ApiResponse<>(
                task,
                "Task retrieved Sucessfully",
                HttpStatus.OK.value()
        );
        return ResponseEntity.ok(response);
    }

    @Operation(
            description = "Delete end-point for Task",
            summary = "This end-point will help deleting the task from the database"
    )
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable("taskId") Long taskId) {
        try {
            taskService.deleteTask(taskId, userService.getAuthenticatedUserId());
            return ResponseEntity.noContent().build();
        }catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
            description = "Put end-point for Task",
            summary = "This end-point will help updating the task available the database"
    )
    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(
            @PathVariable("taskId") Long taskId,
            @RequestBody Task task
    ) {
        try {
            Task updatedTask = taskService.updateTask(taskId, userService.getAuthenticatedUserId(), task);
            return ResponseEntity.ok(updatedTask);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
            description = "Patch end-point for Task",
            summary = "This end-point will help updating the task status only"
    )
    @PatchMapping("/{taskId}")
    public ResponseEntity<Task> updateStatus(
            @PathVariable("taskId") Long taskId,
            @RequestBody TaskStatusOnlyDTO taskStatusOnly
    ) {
        try {
            Task updatedTask = taskService.updateStatusOnly(taskId, userService.getAuthenticatedUserId(), taskStatusOnly);
            return ResponseEntity.ok(updatedTask);
        }catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
