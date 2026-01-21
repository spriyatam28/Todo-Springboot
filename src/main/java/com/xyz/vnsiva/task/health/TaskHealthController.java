package com.xyz.vnsiva.task.health;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/service")
public class TaskHealthController {
    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> root() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "active");
        response.put("healthy", true);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
