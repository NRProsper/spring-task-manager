package dev.kiki.springtodo.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskMapperTest {

    private TaskMapper taskMapper;

    @BeforeEach
    void setUp() {
        taskMapper = new TaskMapper();
    }

    @Test
    public void shouldMapTaskDTOtoTask() {
        TaskDTO dto = new TaskDTO("Finish Coding", "I will finish the task manager API");
        Task task = taskMapper.taskDtoToTask(dto);

        assertEquals(dto.title(), task.getTitle());
        assertEquals(dto.description(), task.getDescription());
    }

}