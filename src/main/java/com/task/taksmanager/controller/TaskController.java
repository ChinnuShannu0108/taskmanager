package com.task.taksmanager.controller;

import com.task.taksmanager.model.Task;
import com.task.taksmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }
    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }
    @PatchMapping("/{id}")
    public Task patchTask(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return taskService.patchTask(id, updates);
    }
    @PatchMapping("/update-all-status")
    public String updateAllStatus(@RequestBody Map<String, String> request) {
        return taskService.updateAllStatus(request.get("status"));
    }

    @PostMapping
    public Task createTask(@RequestBody Task task, Authentication authentication) {
        task.setCreatedBy(authentication.getName());
        return taskService.createTask(task);
    }


    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}