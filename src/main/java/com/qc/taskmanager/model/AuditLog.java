package com.qc.taskmanager.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "audit_logs")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant timestamp;

    private String action;      // Create Task / Update Task / Delete Task

    private Long taskId;

    @Lob
    private String updatedContentJson;   // JSON string of changed fields

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getUpdatedContentJson() {
        return updatedContentJson;
    }

    public void setUpdatedContentJson(String updatedContentJson) {
        this.updatedContentJson = updatedContentJson;
    }
}
