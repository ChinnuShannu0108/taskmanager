package com.task.taksmanager.service;

import com.task.taksmanager.model.Task;
import com.task.taksmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public Task updateTask(Long id, Task task) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setStatus(task.getStatus());
        return taskRepository.save(existingTask);
    }
    public String updateAllStatus(String status) {
        List<Task> tasks = taskRepository.findAll();
        for (Task task : tasks) {
            task.setStatus(status);
        }
        taskRepository.saveAll(tasks);
        return "All tasks updated to status: " + status;
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
    public Task patchTask(Long id, Map<String, Object> updates) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (updates.containsKey("title")) {
            task.setTitle((String) updates.get("title"));
        }
        if (updates.containsKey("description")) {
            task.setDescription((String) updates.get("description"));
        }
        if (updates.containsKey("status")) {
            task.setStatus((String) updates.get("status"));
        }

        return taskRepository.save(task);
    }

}