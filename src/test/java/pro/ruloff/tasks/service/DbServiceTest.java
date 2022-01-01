package pro.ruloff.tasks.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mockingDetails;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.ruloff.tasks.domain.Task;
import pro.ruloff.tasks.repository.TaskRepository;

@ExtendWith(MockitoExtension.class)
public class DbServiceTest {

  @InjectMocks
  private DbService dbService;

  @Mock
  private TaskRepository repository;

  @Test
  void shouldGetAllTasks() {
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
  void shouldSaveTask() {
    // Given
    Task task = new Task(1L, "Test task", "Test content");
    when(repository.save(task)).thenReturn(task);
    // When
    String title = dbService.saveTask(task).getTitle();
    // Then
    assertEquals("Test task", title);
  }

  @Test
  void shouldGetTask() {
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
  void shouldDeleteById() {
    // Given
    doNothing().when(repository).deleteById(1L);
    // When
    dbService.deleteTask(1L);
    // Then
    System.out.println(mockingDetails(repository).printInvocations());
    verify(repository).deleteById(1L);
  }

  @Test
  void shouldFindById() {
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
