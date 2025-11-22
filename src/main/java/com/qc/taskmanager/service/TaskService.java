package com.qc.taskmanager.service;

import com.qc.taskmanager.model.Task;
import com.qc.taskmanager.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class TaskService {

    private final TaskRepository taskRepo;
    private final AuditLogService auditLogService;

    public TaskService(TaskRepository taskRepo, AuditLogService auditLogService) {
        this.taskRepo = taskRepo;
        this.auditLogService = auditLogService;
    }

    public Page<Task> listTasks(String search, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        if (search == null || search.isBlank()) {
            return taskRepo.findAll(pageable);
        }

        return taskRepo.search(search, pageable);
    }

    public Task createTask(Task task) {
        task.setCreatedAt(Instant.now());
        Task saved = taskRepo.save(task);

        Map<String, Object> data = new HashMap<>();
        data.put("title", saved.getTitle());
        data.put("description", saved.getDescription());
        data.put("createdAt", saved.getCreatedAt());

        auditLogService.log("Create Task", saved.getId(), data);

        return saved;
    }

    public Task updateTask(Long id, Task updated) {
        Task existing = taskRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));

        Map<String, Object> changed = new HashMap<>();

        if (!Objects.equals(existing.getTitle(), updated.getTitle())) {
            existing.setTitle(updated.getTitle());
            changed.put("title", updated.getTitle());
        }

        if (!Objects.equals(existing.getDescription(), updated.getDescription())) {
            existing.setDescription(updated.getDescription());
            changed.put("description", updated.getDescription());
        }

        Task saved = taskRepo.save(existing);

        if (!changed.isEmpty()) {
            auditLogService.log("Update Task", saved.getId(), changed);
        }

        return saved;
    }

    public void deleteTask(Long id) {
        if (!taskRepo.existsById(id)) {
            throw new EntityNotFoundException("Task not found");
        }
        taskRepo.deleteById(id);
        auditLogService.log("Delete Task", id, null);
    }
}
