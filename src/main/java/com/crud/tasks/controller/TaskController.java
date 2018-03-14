package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/task")
public class TaskController {
    @Autowired
    private DbService service;

    @Autowired
    private TaskMapper taskMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getTasks")
    public List<TaskDto> getTasks() {
        System.out.println("Doing: \"getTasks()\"");
        return taskMapper.mapToTaskDtoList(service.getAllTasks());
    }

    @GetMapping("getTask/{taskId}")
    @ResponseBody
    public TaskDto getTask(@PathVariable("taskId") long taskId) {
        System.out.println("Doing: \"getTask(@PathVariable(\"taskId\") long taskId)\"");
        return new TaskDto(1L, "Test title", "test_content");
    }

    @DeleteMapping("deleteTask/{taskId}")
    @ResponseBody
    public void deleteTask(@PathVariable("taskId") int taskId) {
        System.out.println("Delete request for ID: " + taskId);
    }

    @PutMapping("updateTask/{taskDto}")
    @ResponseBody
    public TaskDto updateTask(@PathVariable("taskDto") TaskDto taskDto) {
        System.out.println("Doing: \"updateTask(@PathVariable(\"taskDto\") TaskDto taskDto)\"");
        return new TaskDto(1L, "Edited test title", "Test content");
    }

    @PostMapping("createTask/{taskDto}")
    @ResponseBody
    public void createTask(@PathVariable("taskDto") TaskDto taskDto) {
        System.out.println("You order me to create: " + taskDto);
    }
}
