package pro.ruloff.tasks.controller;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.ruloff.tasks.controller.exception.TaskNotFoundException;
import pro.ruloff.tasks.domain.dto.TaskDto;
import pro.ruloff.tasks.mapper.TaskMapper;
import pro.ruloff.tasks.service.DbService;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class TaskController {

  private final DbService service;
  private final TaskMapper taskMapper;

  public TaskController(DbService service, TaskMapper taskMapper) {
    this.service = service;
    this.taskMapper = taskMapper;
  }

  @GetMapping("/tasks")
  public List<TaskDto> getTasks() {
    return taskMapper.mapToTaskDtoList(service.getAllTasks());
  }

  @GetMapping("/tasks/{taskId}")
  public TaskDto getTask(@PathVariable Long taskId) throws TaskNotFoundException {
    return taskMapper.mapToTaskDto(service.getTask(taskId).orElseThrow(TaskNotFoundException::new));
  }

  @DeleteMapping(value = "/tasks/{taskId}")
  public void deleteTask(@PathVariable Long taskId) {
    service.deleteTask(taskId);
  }

  @PutMapping(value = "/tasks", consumes = APPLICATION_JSON_VALUE)
  public TaskDto updateTask(@RequestBody TaskDto taskDto) {
    return taskMapper.mapToTaskDto(service.saveTask(taskMapper.mapToTask(taskDto)));
  }

  @PostMapping(value = "/tasks", consumes = APPLICATION_JSON_VALUE)
  public void createTask(@RequestBody TaskDto taskDto) {
    service.saveTask(taskMapper.mapToTask(taskDto));
  }
}
