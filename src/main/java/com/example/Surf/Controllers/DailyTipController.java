package com.example.Surf.Controllers;

import com.example.Surf.DTO.DailyTipDTO;
import com.example.Surf.Services.DailyTipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/tips")
public class DailyTipController {

    @Autowired
    private DailyTipService tipService;

    @GetMapping("/daily")
    public ResponseEntity<DailyTipDTO> getDailyTip(@RequestParam String language) {
        LocalDate today = LocalDate.now();
        try {
            DailyTipDTO tip = tipService.getDailyTip(today, language);
            if (tip == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new DailyTipDTO(null, "No tip available today.", null, today, language, "Draft", "System", 0L, 0L));
            }
            return ResponseEntity.ok(tip);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new DailyTipDTO(null, e.getMessage(), null, today, language, "Draft", "System", 0L, 0L));
        } catch (Exception e) {
            System.err.println("Error fetching daily tip: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new DailyTipDTO(null, "Server error, please try again later.", null, today, language, "Draft", "System", 0L, 0L));
        }
    }

    @GetMapping
    public ResponseEntity<List<DailyTipDTO>> getAllTips() {
        try {
            List<DailyTipDTO> tips = tipService.getAllTips();
            return ResponseEntity.ok(tips);
        } catch (Exception e) {
            System.err.println("Error fetching all tips: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<DailyTipDTO> createTip(@RequestBody DailyTipDTO tipDTO) {
        try {
            DailyTipDTO createdTip = tipService.createTip(tipDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTip);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new DailyTipDTO(null, e.getMessage(), null, null, null, "Draft", "System", 0L, 0L));
        } catch (Exception e) {
            System.err.println("Error creating tip: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new DailyTipDTO(null, "Server error, please try again later.", null, null, null, "Draft", "System", 0L, 0L));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DailyTipDTO> updateTip(@PathVariable Long id, @RequestBody DailyTipDTO tipDTO) {
        try {
            Optional<DailyTipDTO> updatedTip = tipService.updateTip(id, tipDTO);
            if (updatedTip.isPresent()) {
                return ResponseEntity.ok(updatedTip.get());
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new DailyTipDTO(null, "Tip not found.", null, null, null, "Draft", "System", 0L, 0L));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new DailyTipDTO(null, e.getMessage(), null, null, null, "Draft", "System", 0L, 0L));
        } catch (Exception e) {
            System.err.println("Error updating tip: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new DailyTipDTO(null, "Server error, please try again later.", null, null, null, "Draft", "System", 0L, 0L));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTip(@PathVariable Long id) {
        try {
            boolean deleted = tipService.deleteTip(id);
            if (deleted) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            System.err.println("Error deleting tip: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}