package io.github.slawomirr.tasks.service;

import io.github.slawomirr.tasks.domain.Task;
import io.github.slawomirr.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTestSuite {
    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository repository;

    @Test
    public void shouldGetAllTasks() {
        // Given
        Task task = new Task();
        Task task1 = new Task();
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        tasks.add(task1);
        when(repository.findAll()).thenReturn(tasks);
        // When
        int size = dbService.getAllTasks().size();
        // Then
        assertEquals(2, size);
    }

    @Test
    public void shouldSaveTask() {
        // Given
        Task task = new Task(1L, "Test task", "Test content");
        when(repository.save(task)).thenReturn(task);
        // When
        String title = dbService.saveTask(task).getTitle();
        // Then
        assertEquals("Test task", title);
    }

    @Test
    public void shouldGetTask() {
        // Given
        Task task = new Task(1L, "Test task 1", "Testing1");
        Optional<Task> optionalTask = Optional.of(task);
        when(repository.findById(1L)).thenReturn(optionalTask);
        // When
        Optional<Task> fetchedTask = dbService.getTask(1L);
        // Then
        assertEquals(optionalTask, fetchedTask);
    }

    @Test
    public void shouldDeleteById() {
        // Given
        doNothing().when(repository).deleteById(1L);
        // When
        dbService.deleteTask(1L);
        // Then
        System.out.println(mockingDetails(repository).printInvocations());
        verify(repository).deleteById(1L);
    }

    @Test
    public void shouldFindById() {
        // Given
        Task task = new Task(2L, "Test task 2", "Testing2");
        Optional<Task> optionalTask = Optional.of(task);
        when(repository.findById(2L)).thenReturn(optionalTask);
        // When
        Optional<Task> foundTask = dbService.getTask(2L);
        // Then
        assertEquals(optionalTask, foundTask);
    }
}
