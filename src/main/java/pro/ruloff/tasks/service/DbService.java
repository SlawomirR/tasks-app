package pro.ruloff.tasks.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import pro.ruloff.tasks.domain.Task;
import pro.ruloff.tasks.repository.TaskRepository;

@Service
public class DbService {

  private final TaskRepository repository;

  public DbService(TaskRepository repository) {
    this.repository = repository;
  }

  public List<Task> getAllTasks() {
    return new ArrayList<>(repository.findAll());
  }

  public Task saveTask(final Task task) {
    return repository.save(task);
  }

  public Optional<Task> getTask(final Long id) {
    return repository.findById(id);
  }

  public void deleteTask(final Long id) {
    repository.deleteById(id);
  }
}
