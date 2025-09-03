package com.example.Surf.Controllers;

import com.example.Surf.DTO.DailyTipDTO;
import com.example.Surf.Services.DailyTipService;
import com.example.Surf.Models.DailyTip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tips")
public class DailyTipController {

    private static final Logger logger = LoggerFactory.getLogger(DailyTipController.class);

    @Autowired
    private DailyTipService dailyTipService;

    @PostMapping
    public ResponseEntity<DailyTipDTO> createDailyTip(@RequestBody DailyTipDTO dailyTipDTO) {
        logger.info("Received POST request: {}", dailyTipDTO);
        try {
            if (dailyTipDTO.getContent() != null) {
                JSONObject json = new JSONObject(dailyTipDTO.getContent());
                if (!json.has("lang") || !json.has("description") || !json.has("icon")) {
                    throw new IllegalArgumentException("Content must include 'lang', 'description', and 'icon' fields");
                }
            } else {
                throw new IllegalArgumentException("Content cannot be null");
            }
            if (dailyTipDTO.getStatus() != null) {
                dailyTipDTO.setStatus(dailyTipDTO.getStatus().toUpperCase());
            }
            DailyTip createdDailyTip = dailyTipService.createDailyTip(dailyTipDTO);
            DailyTipDTO dto = dailyTipService.mapToDTO(createdDailyTip);
            logger.info("Created daily tip: {}", dto);
            return ResponseEntity.ok(dto);
        } catch (IllegalArgumentException e) {
            logger.error("Invalid content JSON: {}", e.getMessage());
            DailyTipDTO errorDto = new DailyTipDTO();
            Map<String, Object> problemDetails = new HashMap<>();
            problemDetails.put("type", "https://example.com/probs/invalid-content");
            problemDetails.put("title", "Invalid Content");
            problemDetails.put("status", 400);
            problemDetails.put("detail", e.getMessage());
            errorDto.setContent(new JSONObject(problemDetails).toString());
            return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DailyTipDTO> getDailyTipById(@PathVariable Long id) {
        logger.info("Fetching daily tip by ID: {}", id);
        return dailyTipService.getDailyTipById(id)
                .map(dailyTipService::mapToDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    logger.warn("Daily tip ID {} not found", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @GetMapping("/latest")
    public ResponseEntity<DailyTipDTO> getLatestTip() {
        logger.info("Fetching latest active daily tip");
        return dailyTipService.getLatestActiveTip()
                .map(dailyTipService::mapToDTO)
                .map(dto -> {
                    logger.info("Returning latest daily tip: {}", dto);
                    return ResponseEntity.ok(dto);
                })
                .orElseGet(() -> {
                    logger.warn("No active daily tip found");
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
                });
    }

    @GetMapping("/daily")
    public ResponseEntity<DailyTipDTO> getDailyTip(@RequestParam String language, @RequestParam String date) {
        logger.info("Fetching daily tip for language: {}, date: {}", language, date);
        return dailyTipService.getDailyTip(language, date)
                .map(dailyTipService::mapToDTO)
                .map(dto -> {
                    logger.info("Returning daily tip: {}", dto);
                    return ResponseEntity.ok(dto);
                })
                .orElseGet(() -> {
                    logger.warn("No daily tip found for language: {}, date: {}", language, date);
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
                });
    }

    @GetMapping
    public ResponseEntity<List<DailyTipDTO>> getAllDailyTips() {
        logger.info("Fetching all daily tips");
        List<DailyTipDTO> dailyTips = dailyTipService.getAllDailyTips()
                .stream()
                .map(dailyTipService::mapToDTO)
                .collect(Collectors.toList());
        logger.info("Returning {} daily tips", dailyTips.size());
        return ResponseEntity.ok(dailyTips);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DailyTipDTO> updateDailyTip(@PathVariable Long id, @RequestBody DailyTipDTO dailyTipDTO) {
        logger.info("Received PUT request for ID {}: {}", id, dailyTipDTO);
        try {
            if (dailyTipDTO.getContent() != null) {
                JSONObject json = new JSONObject(dailyTipDTO.getContent());
                if (!json.has("lang") || !json.has("description") || !json.has("icon")) {
                    throw new IllegalArgumentException("Content must include 'lang', 'description', and 'icon' fields");
                }
            } else {
                throw new IllegalArgumentException("Content cannot be null");
            }
            if (dailyTipDTO.getStatus() != null) {
                dailyTipDTO.setStatus(dailyTipDTO.getStatus().toUpperCase());
            }
            return dailyTipService.updateDailyTip(id, dailyTipDTO)
                    .map(dailyTipService::mapToDTO)
                    .map(dto -> {
                        logger.info("Updated daily tip: {}", dto);
                        return ResponseEntity.ok(dto);
                    })
                    .orElseGet(() -> {
                        logger.warn("Daily tip ID {} not found", id);
                        return ResponseEntity.notFound().build();
                    });
        } catch (IllegalArgumentException e) {
            logger.error("Invalid content JSON: {}", e.getMessage());
            DailyTipDTO errorDto = new DailyTipDTO();
            Map<String, Object> problemDetails = new HashMap<>();
            problemDetails.put("type", "https://example.com/probs/invalid-content");
            problemDetails.put("title", "Invalid Content");
            problemDetails.put("status", 400);
            problemDetails.put("detail", e.getMessage());
            errorDto.setContent(new JSONObject(problemDetails).toString());
            return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDailyTip(@PathVariable Long id) {
        logger.info("Deleting daily tip ID: {}", id);
        if (dailyTipService.deleteDailyTip(id)) {
            logger.info("Deleted daily tip ID: {}", id);
            return ResponseEntity.noContent().build();
        }
        logger.warn("Daily tip ID {} not found", id);
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(Exception ex) {
        logger.error("Internal server error: {}", ex.getMessage());
        Map<String, Object> problemDetails = new HashMap<>();
        problemDetails.put("type", "https://example.com/probs/internal-server-error");
        problemDetails.put("title", "Internal Server Error");
        problemDetails.put("status", 500);
        problemDetails.put("detail", ex.getMessage());
        return new ResponseEntity<>(problemDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}