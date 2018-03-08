package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/task")
public class TaskController {

    @RequestMapping(method = RequestMethod.GET, value = "getTasks")
    public List<TaskDto> getTasks() {
        return new ArrayList<>();
    }

    @GetMapping("getTask/{taskId}")
    @ResponseBody
    public TaskDto getTask(@PathVariable("taskId") long taskId) {
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
        return new TaskDto(1L, "Edited test title", "Test content");
    }

    @PostMapping("createTask/{taskDto}")
    @ResponseBody
    public void createTask(@PathVariable("taskDto") TaskDto taskDto) {
        System.out.println("You order me to create: " + taskDto);
    }
}
