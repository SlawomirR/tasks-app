package io.github.slawomirr.tasks.mapper;

import io.github.slawomirr.tasks.domain.Task;
import io.github.slawomirr.tasks.domain.dto.TaskDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTestSuite {
    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void testMapToTask() {
        // Given
        Long taskDtoId = 1L;
        String taskDtoTitle = "taskDtoTitle";
        String taskDtoContent = "taskDtoContent";
        TaskDto taskDto = new TaskDto(taskDtoId, taskDtoTitle, taskDtoContent);
        // When
        Task task = taskMapper.mapToTask(taskDto);
        // Then
        Assert.assertEquals(taskDtoId, task.getId());
        Assert.assertEquals(taskDtoTitle, task.getTitle());
        Assert.assertEquals(taskDtoContent, task.getContent());
    }

    @Test
    public void testMapToTaskDto() {
        // Given
        Long taskId = 1L;
        String taskTitle = "taskTitle";
        String taskContent = "taskContent";
        Task task = new Task(taskId, taskTitle, taskContent);
        // When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        // Then
        Assert.assertEquals(taskId, task.getId());
        Assert.assertEquals(taskTitle, task.getTitle());
        Assert.assertEquals(taskContent, task.getContent());
    }

    @Test
    public void testMapToTaskDtoList() {
        // Given
        Long taskId = 1L;
        String taskTitle = "taskTitle";
        String taskContent = "taskContent";
        Task task = new Task(taskId, taskTitle, taskContent);
        List<Task> taskList = new ArrayList<>();
        taskList.add(task);
        // When
        List<TaskDto> taskDtos = taskMapper.mapToTaskDtoList(taskList);
        // Then
        Assert.assertEquals(1, taskDtos.size());
        Assert.assertEquals(taskId, taskDtos.get(0).getId());
        Assert.assertEquals(taskTitle, taskDtos.get(0).getTitle());
        Assert.assertEquals(taskContent, taskDtos.get(0).getContent());
    }
}
