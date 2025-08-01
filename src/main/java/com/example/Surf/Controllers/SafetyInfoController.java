package com.example.Surf.Controllers;

import com.example.Surf.DTO.SafetyInfoDTO;
import com.example.Surf.Services.SafetyInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.Surf.Models.*;
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
        SafetyInfo createdSafetyInfo = safetyInfoService.createSafetyInfo(safetyInfoDTO);
        SafetyInfoDTO dto = safetyInfoService.mapToDTO(createdSafetyInfo);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SafetyInfoDTO> getSafetyInfoById(@PathVariable Long id) {
        return safetyInfoService.getSafetyInfoById(id)
                .map(safetyInfoService::mapToDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/beach/{beachId}")
    public ResponseEntity<List<SafetyInfoDTO>> getSafetyInfoByBeachId(@PathVariable Long beachId) {
        List<SafetyInfoDTO> safetyInfos = safetyInfoService.getSafetyInfoByBeachId(beachId)
                .stream()
                .map(safetyInfoService::mapToDTO)
                .collect(Collectors.toList());
        logger.info("Returning {} safety infos for beachId {}", safetyInfos.size(), beachId);
        return ResponseEntity.ok(safetyInfos);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<SafetyInfoDTO>> getSafetyInfoByCategory(@PathVariable String category) {
        List<SafetyInfoDTO> safetyInfos = safetyInfoService.getSafetyInfoByCategory(category)
                .stream()
                .map(safetyInfoService::mapToDTO)
                .collect(Collectors.toList());
        logger.info("Returning {} safety infos for category {}", safetyInfos.size(), category);
        return ResponseEntity.ok(safetyInfos);
    }

    @GetMapping
    public ResponseEntity<List<SafetyInfoDTO>> getAllSafetyInfo() {
        List<SafetyInfoDTO> safetyInfos = safetyInfoService.getAllSafetyInfo()
                .stream()
                .map(safetyInfoService::mapToDTO)
                .collect(Collectors.toList());
        logger.info("Returning {} safety infos", safetyInfos.size());
        return ResponseEntity.ok(safetyInfos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SafetyInfoDTO> updateSafetyInfo(@PathVariable Long id, @RequestBody SafetyInfoDTO safetyInfoDTO) {
        return safetyInfoService.updateSafetyInfo(id, safetyInfoDTO)
                .map(safetyInfoService::mapToDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSafetyInfo(@PathVariable Long id) {
        if (safetyInfoService.deleteSafetyInfo(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        Map<String, Object> problemDetails = new HashMap<>();
        problemDetails.put("type", "https://example.com/probs/internal-server-error");
        problemDetails.put("title", "Internal Server Error");
        problemDetails.put("status", 500);
        problemDetails.put("detail", ex.getMessage());
        return new ResponseEntity<>(problemDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}