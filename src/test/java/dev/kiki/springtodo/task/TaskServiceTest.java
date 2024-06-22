package dev.kiki.springtodo.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;
    @Mock
    private TaskMapper taskMapper;
    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_successfully_save_a_task() {
        //Given
        TaskCreationDTO taskCreationDto = new TaskCreationDTO(
                "Finish coding",
                "I will make a task manager API"
        );

        Task task = new Task();
        task.setTitle("Finish coding");
        task.setDescription("I will make a task manager API");

        Task savedTask = new Task();
        savedTask.setId(1L);
        savedTask.setTitle("Finish coding");
        savedTask.setDescription("I will make a task manager API");
        savedTask.setStatus(Status.TODO);
        savedTask.setCreateAt(LocalDateTime.now());

        when(taskMapper.taskDtoToTask(taskCreationDto)).thenReturn(task);
        when(taskRepository.save(task)).thenReturn(savedTask);

        //When
        Task response = taskService.createTask(taskCreationDto);

        //Then
        assertNotNull(response);
        assertEquals(savedTask.getId(), response.getId());
        assertEquals(savedTask.getTitle(), response.getTitle());
        assertEquals(savedTask.getDescription(), response.getDescription());
        assertEquals(savedTask.getStatus(), response.getStatus());
        assertEquals(savedTask.getCreateAt(), response.getCreateAt());

        verify(taskMapper, times(1)).taskDtoToTask(taskCreationDto);
        verify(taskRepository, times(1)).save(task);


    }

}