package com.qc.taskmanager.controller;

import com.qc.taskmanager.model.AuditLog;
import com.qc.taskmanager.service.AuditLogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/logs")
@CrossOrigin(origins = "http://localhost:4200")
public class AuditLogController {

    private final AuditLogService service;
    private final ObjectMapper mapper = new ObjectMapper();

    public AuditLogController(AuditLogService service) {
        this.service = service;
    }

    @GetMapping
    public Page<Map<String, Object>> getLogs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<AuditLog> logs = service.getLogs(
                org.springframework.data.domain.PageRequest.of(page, size));

        return logs.map(log -> {
            Map<String, Object> map = new HashMap<>();
            map.put("timestamp", log.getTimestamp());
            map.put("action", log.getAction());
            map.put("taskId", log.getTaskId());

            if (log.getUpdatedContentJson() != null) {
                try {
                    map.put("updatedContent",
                            mapper.readValue(log.getUpdatedContentJson(), Map.class));
                } catch (Exception e) {
                    map.put("updatedContent", Collections.emptyMap());
                }
            } else {
                map.put("updatedContent", null);
            }
            return map;
        });
    }
}
