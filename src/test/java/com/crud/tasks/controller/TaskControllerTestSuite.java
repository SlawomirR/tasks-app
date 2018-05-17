package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTestSuite {

    private static final String PATH = "/v1/tasks";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void shouldGetTasksEmptyList() throws Exception{
        // Given
        List<TaskDto> taskDtos = new ArrayList<>();
        when(taskMapper.mapToTaskDtoList(dbService.getAllTasks())).thenReturn(taskDtos);
        // When & Then
        mockMvc.perform(get(PATH)
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))//or isOk()
                .andExpect(jsonPath("$", hasSize(0)))
                .andDo(print());
        System.out.println(mockingDetails(dbService).printInvocations());
    }

    @Test
    public void shouldGetTask() throws Exception{
        // Given
        Task testTask = new Task(1L,"Test Task 1", "Testing 1");
        Optional<Task> optionalTask = Optional.of(testTask);
        TaskDto taskDto = new TaskDto(1L, "Test Task 1", "Testing 1");
        when(dbService.getTask(1L)).thenReturn(optionalTask);
        when(taskMapper.mapToTaskDto(testTask)).thenReturn(taskDto);
        // When & Then
        mockMvc.perform(get(PATH + "/1")
                .characterEncoding("UTF-8")
                .param("taskId", "1L"))
                .andDo(print());
        System.out.println(mockingDetails(dbService).printInvocations());
    }

    @Test
    public void shouldDeleteTask() throws Exception{
        // Given
        doNothing().when(dbService).deleteTask(anyLong());
        // When & Then
        mockMvc.perform(delete(PATH + "/1")
                .characterEncoding("UTF-8")
                .param("taskId", "1"))
                .andExpect(status().isOk())
                .andDo(print());
        System.out.println(mockingDetails(dbService).printInvocations());
        verify(dbService).deleteTask(anyLong());
        verifyNoMoreInteractions(dbService);
    }

    @Test
    public void shouldUpdateTask() throws Exception{
        // Given
        Gson gson = new Gson();
        TaskDto taskDto = new TaskDto(1L, "Test Task 1", "Testing 1");
        String jsonContent = gson.toJson(taskDto);
        when(taskMapper.mapToTaskDto(dbService.saveTask(taskMapper.mapToTask(taskDto)))).thenReturn(taskDto);
        // When & Then
        mockMvc.perform(put(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Test Task 1")))
                .andExpect(jsonPath("$.content", is("Testing 1")))
                .andDo(print());
        System.out.println(mockingDetails(dbService).printInvocations());
    }

    @Test
    public void shouldCreateTask() throws Exception{
        // Given
        Gson gson = new Gson();
        TaskDto taskDto = new TaskDto(1L, "Test Task 1", "Testing 1");
        String jsonContent = gson.toJson(taskDto);
        // When & Then
        mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andDo(print());
        System.out.println(mockingDetails(dbService).printInvocations());
    }
}
