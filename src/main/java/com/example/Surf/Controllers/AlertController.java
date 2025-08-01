package com.example.Surf.Controllers;

import com.example.Surf.Services.*;
import com.example.Surf.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
public class AlertController {

    @Autowired
    private AlertService alertService;

    @PostMapping
    public ResponseEntity<AlertDTO> createAlert(@RequestBody AlertDTO alertDTO) {
        AlertDTO createdAlert = alertService.createAlert(alertDTO);
        return ResponseEntity.ok(createdAlert);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlertDTO> getAlertById(@PathVariable Long id) {
        return alertService.getAlertById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/beach/{beachId}")
    public ResponseEntity<List<AlertDTO>> getAlertsByBeachId(@PathVariable Long beachId) {
        List<AlertDTO> alerts = alertService.getAlertsByBeachId(beachId);
        return ResponseEntity.ok(alerts);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<AlertDTO>> getAlertsByType(@PathVariable String type) {
        List<AlertDTO> alerts = alertService.getAlertsByType(type);
        return ResponseEntity.ok(alerts);
    }

    @GetMapping
    public ResponseEntity<List<AlertDTO>> getAllAlerts() {
        List<AlertDTO> alerts = alertService.getAllAlerts();
        return ResponseEntity.ok(alerts);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlertDTO> updateAlert(@PathVariable Long id, @RequestBody AlertDTO alertDTO) {
        return alertService.updateAlert(id, alertDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlert(@PathVariable Long id) {
        if (alertService.deleteAlert(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}