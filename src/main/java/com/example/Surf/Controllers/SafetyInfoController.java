package com.example.Surf.Controllers;

import com.example.Surf.DTO.SafetyInfoDTO;
import com.example.Surf.Services.SafetyInfoService;
import com.example.Surf.Models.SafetyInfo;
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
@RequestMapping("/api/safety-info")
public class SafetyInfoController {

    private static final Logger logger = LoggerFactory.getLogger(SafetyInfoController.class);

    @Autowired
    private SafetyInfoService safetyInfoService;

    @PostMapping
    public ResponseEntity<SafetyInfoDTO> createSafetyInfo(@RequestBody SafetyInfoDTO safetyInfoDTO) {
        logger.info("Received POST request: {}", safetyInfoDTO);
        try {
            if (safetyInfoDTO.getContent() != null) {
                JSONObject json = new JSONObject(safetyInfoDTO.getContent());
                if (!json.has("lang") || !json.has("description") || !json.has("icon")) {
                    throw new IllegalArgumentException("Content must include 'lang', 'description', and 'icon' fields");
                }
            } else {
                throw new IllegalArgumentException("Content cannot be null");
            }
            if (safetyInfoDTO.getStatus() != null) {
                safetyInfoDTO.setStatus(safetyInfoDTO.getStatus().toUpperCase());
            }
            SafetyInfo createdSafetyInfo = safetyInfoService.createSafetyInfo(safetyInfoDTO);
            SafetyInfoDTO dto = safetyInfoService.mapToDTO(createdSafetyInfo);
            logger.info("Created safety info: {}", dto);
            return ResponseEntity.ok(dto);
        } catch (IllegalArgumentException e) {
            logger.error("Invalid content JSON: {}", e.getMessage());
            SafetyInfoDTO errorDto = new SafetyInfoDTO(null, null, null, null, null, null, null, null, null);
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
    public ResponseEntity<SafetyInfoDTO> getSafetyInfoById(@PathVariable Long id) {
        logger.info("Fetching safety info by ID: {}", id);
        return safetyInfoService.getSafetyInfoById(id)
                .map(safetyInfoService::mapToDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/beach/{beachId}")
    public ResponseEntity<List<SafetyInfoDTO>> getSafetyInfoByBeachId(@PathVariable Long beachId) {
        logger.info("Fetching safety info by beach ID: {}", beachId);
        List<SafetyInfoDTO> safetyInfos = safetyInfoService.getSafetyInfoByBeachId(beachId)
                .stream()
                .map(safetyInfoService::mapToDTO)
                .collect(Collectors.toList());
        logger.info("Returning {} safety infos for beachId {}", safetyInfos.size(), beachId);
        return ResponseEntity.ok(safetyInfos);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<SafetyInfoDTO>> getSafetyInfoByCategory(@PathVariable String category) {
        logger.info("Fetching safety info by category: {}", category);
        List<SafetyInfoDTO> safetyInfos = safetyInfoService.getSafetyInfoByCategory(category)
                .stream()
                .map(safetyInfoService::mapToDTO)
                .collect(Collectors.toList());
        logger.info("Returning {} safety infos for category {}", safetyInfos.size(), category);
        return ResponseEntity.ok(safetyInfos);
    }

    @GetMapping
    public ResponseEntity<List<SafetyInfoDTO>> getAllSafetyInfo() {
        logger.info("Fetching all safety info");
        List<SafetyInfoDTO> safetyInfos = safetyInfoService.getAllSafetyInfo()
                .stream()
                .map(safetyInfoService::mapToDTO)
                .collect(Collectors.toList());
        logger.info("Returning {} safety infos", safetyInfos.size());
        return ResponseEntity.ok(safetyInfos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SafetyInfoDTO> updateSafetyInfo(@PathVariable Long id, @RequestBody SafetyInfoDTO safetyInfoDTO) {
        logger.info("Received PUT request for ID {}: {}", id, safetyInfoDTO);
        try {
            if (safetyInfoDTO.getContent() != null) {
                JSONObject json = new JSONObject(safetyInfoDTO.getContent());
                if (!json.has("lang") || !json.has("description") || !json.has("icon")) {
                    throw new IllegalArgumentException("Content must include 'lang', 'description', and 'icon' fields");
                }
            } else {
                throw new IllegalArgumentException("Content cannot be null");
            }
            if (safetyInfoDTO.getStatus() != null) {
                safetyInfoDTO.setStatus(safetyInfoDTO.getStatus().toUpperCase());
            }
            return safetyInfoService.updateSafetyInfo(id, safetyInfoDTO)
                    .map(safetyInfoService::mapToDTO)
                    .map(dto -> {
                        logger.info("Updated safety info: {}", dto);
                        return ResponseEntity.ok(dto);
                    })
                    .orElse(ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            logger.error("Invalid content JSON: {}", e.getMessage());
            SafetyInfoDTO errorDto = new SafetyInfoDTO(null, null, null, null, null, null, null, null, null);
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
    public ResponseEntity<Void> deleteSafetyInfo(@PathVariable Long id) {
        logger.info("Deleting safety info ID: {}", id);
        if (safetyInfoService.deleteSafetyInfo(id)) {
            logger.info("Deleted safety info ID: {}", id);
            return ResponseEntity.noContent().build();
        }
        logger.warn("Safety info ID {} not found", id);
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