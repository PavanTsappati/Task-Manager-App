package com.qc.taskmanager.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qc.taskmanager.model.AuditLog;
import com.qc.taskmanager.repository.AuditLogRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;

@Service
public class AuditLogService {

    private final AuditLogRepository repo;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public AuditLogService(AuditLogRepository repo) {
        this.repo = repo;
    }

    public void log(String action, Long taskId, Map<String, Object> updatedContent) {
        AuditLog log = new AuditLog();
        log.setTimestamp(Instant.now());
        log.setAction(action);
        log.setTaskId(taskId);

        if (updatedContent != null && !updatedContent.isEmpty()) {
            try {
                log.setUpdatedContentJson(objectMapper.writeValueAsString(updatedContent));
            } catch (JsonProcessingException e) {
                log.setUpdatedContentJson("{}");
            }
        }

        repo.save(log);
    }

    public Page<AuditLog> getLogs(Pageable pageable) {
        return repo.findAll(pageable);
    }
}
