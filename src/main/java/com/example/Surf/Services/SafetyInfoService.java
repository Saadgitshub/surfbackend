package com.example.Surf.Services;

import com.example.Surf.DTO.SafetyInfoDTO;
import com.example.Surf.DTO.BeachDTO;
import com.example.Surf.Models.SafetyInfo;
import com.example.Surf.Models.Beach;
import com.example.Surf.Repositories.SafetyInfoRepository;
import com.example.Surf.Repositories.BeachRepository;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SafetyInfoService {

    private static final Logger logger = LoggerFactory.getLogger(SafetyInfoService.class);

    @Autowired
    private SafetyInfoRepository safetyInfoRepository;

    @Autowired
    private BeachRepository beachRepository;

    public SafetyInfo createSafetyInfo(SafetyInfoDTO safetyInfoDTO) {
        logger.info("Creating safety info with DTO: {}", safetyInfoDTO);
        if (safetyInfoDTO.getTitle() == null || safetyInfoDTO.getTitle().isEmpty()) {
            logger.error("Title is required");
            throw new IllegalArgumentException("Title is required");
        }
        if (safetyInfoDTO.getContent() == null || safetyInfoDTO.getContent().isEmpty()) {
            logger.error("Content is required");
            throw new IllegalArgumentException("Content is required");
        }
        if (safetyInfoDTO.getCategory() == null || safetyInfoDTO.getCategory().isEmpty()) {
            logger.error("Category is required");
            throw new IllegalArgumentException("Category is required");
        }
        // Validate JSON content
        try {
            JSONObject json = new JSONObject(safetyInfoDTO.getContent());
            if (!json.has("lang") || !json.has("description") || !json.has("icon")) {
                logger.error("Content must include 'lang', 'description', and 'icon' fields");
                throw new IllegalArgumentException("Content must include 'lang', 'description', and 'icon' fields");
            }
        } catch (Exception e) {
            logger.error("Invalid content JSON: {}", e.getMessage());
            throw new IllegalArgumentException("Invalid content JSON: " + e.getMessage());
        }
        Beach beach = null;
        if (safetyInfoDTO.getBeach() != null && safetyInfoDTO.getBeach().getId() != null) {
            Optional<Beach> beachOpt = beachRepository.findById(safetyInfoDTO.getBeach().getId());
            if (!beachOpt.isPresent()) {
                logger.error("Invalid beach ID: {}", safetyInfoDTO.getBeach().getId());
                throw new IllegalArgumentException("Invalid beach ID: " + safetyInfoDTO.getBeach().getId());
            }
            beach = beachOpt.get();
        }

        SafetyInfo safetyInfo = new SafetyInfo();
        safetyInfo.setTitle(safetyInfoDTO.getTitle());
        safetyInfo.setContent(safetyInfoDTO.getContent());
        safetyInfo.setCategory(safetyInfoDTO.getCategory());
        safetyInfo.setStatus(safetyInfoDTO.getStatus() != null ? safetyInfoDTO.getStatus() : "Draft");
        safetyInfo.setLastUpdated(LocalDateTime.now());
        safetyInfo.setUpdatedBy(safetyInfoDTO.getUpdatedBy() != null ? safetyInfoDTO.getUpdatedBy() : "Admin");
        safetyInfo.setViews(safetyInfoDTO.getViews() != null ? safetyInfoDTO.getViews() : 0L);
        safetyInfo.setBeach(beach);

        logger.info("Saving safety info: {}", safetyInfo);
        SafetyInfo savedSafetyInfo = safetyInfoRepository.save(safetyInfo);
        logger.info("Saved safety info: {}", savedSafetyInfo);
        return savedSafetyInfo;
    }

    public Optional<SafetyInfo> getSafetyInfoById(Long id) {
        logger.info("Fetching safety info by ID: {}", id);
        Optional<SafetyInfo> safetyInfo = safetyInfoRepository.findById(id);
        logger.info("Fetched safety info: {}", safetyInfo);
        return safetyInfo;
    }

    public List<SafetyInfo> getSafetyInfoByBeachId(Long beachId) {
        logger.info("Fetching safety info by beach ID: {}", beachId);
        List<SafetyInfo> safetyInfos = safetyInfoRepository.findByBeachId(beachId);
        logger.info("Fetched {} safety infos for beach ID: {}", safetyInfos.size(), beachId);
        return safetyInfos;
    }

    public List<SafetyInfo> getSafetyInfoByCategory(String category) {
        logger.info("Fetching safety info by category: {}", category);
        List<SafetyInfo> safetyInfos = safetyInfoRepository.findByCategory(category);
        logger.info("Fetched {} safety infos for category: {}", safetyInfos.size(), category);
        return safetyInfos;
    }

    public List<SafetyInfo> getAllSafetyInfo() {
        logger.info("Fetching all safety info");
        List<SafetyInfo> safetyInfos = safetyInfoRepository.findAll();
        logger.info("Fetched {} safety infos", safetyInfos.size());
        return safetyInfos;
    }

    public Optional<SafetyInfo> updateSafetyInfo(Long id, SafetyInfoDTO safetyInfoDTO) {
        logger.info("Updating safety info ID: {} with DTO: {}", id, safetyInfoDTO);
        return safetyInfoRepository.findById(id).map(safetyInfo -> {
            if (safetyInfoDTO.getTitle() == null || safetyInfoDTO.getTitle().isEmpty()) {
                logger.error("Title is required");
                throw new IllegalArgumentException("Title is required");
            }
            if (safetyInfoDTO.getContent() == null || safetyInfoDTO.getContent().isEmpty()) {
                logger.error("Content is required");
                throw new IllegalArgumentException("Content is required");
            }
            if (safetyInfoDTO.getCategory() == null || safetyInfoDTO.getCategory().isEmpty()) {
                logger.error("Category is required");
                throw new IllegalArgumentException("Category is required");
            }
            // Validate JSON content
            try {
                JSONObject json = new JSONObject(safetyInfoDTO.getContent());
                if (!json.has("lang") || !json.has("description") || !json.has("icon")) {
                    logger.error("Content must include 'lang', 'description', and 'icon' fields");
                    throw new IllegalArgumentException("Content must include 'lang', 'description', and 'icon' fields");
                }
            } catch (Exception e) {
                logger.error("Invalid content JSON: {}", e.getMessage());
                throw new IllegalArgumentException("Invalid content JSON: " + e.getMessage());
            }
            Beach beach = null;
            if (safetyInfoDTO.getBeach() != null && safetyInfoDTO.getBeach().getId() != null) {
                Optional<Beach> beachOpt = beachRepository.findById(safetyInfoDTO.getBeach().getId());
                if (!beachOpt.isPresent()) {
                    logger.error("Invalid beach ID: {}", safetyInfoDTO.getBeach().getId());
                    throw new IllegalArgumentException("Invalid beach ID: " + safetyInfoDTO.getBeach().getId());
                }
                beach = beachOpt.get();
            }
            safetyInfo.setTitle(safetyInfoDTO.getTitle());
            safetyInfo.setContent(safetyInfoDTO.getContent());
            safetyInfo.setCategory(safetyInfoDTO.getCategory());
            safetyInfo.setStatus(safetyInfoDTO.getStatus() != null ? safetyInfoDTO.getStatus() : "Active");
            safetyInfo.setLastUpdated(LocalDateTime.now());
            safetyInfo.setUpdatedBy(safetyInfoDTO.getUpdatedBy() != null ? safetyInfoDTO.getUpdatedBy() : "Admin");
            safetyInfo.setViews(safetyInfoDTO.getViews() != null ? safetyInfoDTO.getViews() : 0L);
            safetyInfo.setBeach(beach);
            logger.info("Updating safety info: {}", safetyInfo);
            SafetyInfo updatedSafetyInfo = safetyInfoRepository.save(safetyInfo);
            logger.info("Updated safety info: {}", updatedSafetyInfo);
            return updatedSafetyInfo;
        });
    }

    public boolean deleteSafetyInfo(Long id) {
        logger.info("Deleting safety info ID: {}", id);
        if (safetyInfoRepository.existsById(id)) {
            safetyInfoRepository.deleteById(id);
            logger.info("Deleted safety info ID: {}", id);
            return true;
        }
        logger.warn("Safety info ID: {} not found", id);
        return false;
    }

    public SafetyInfoDTO mapToDTO(SafetyInfo safetyInfo) {
        BeachDTO beachDTO = safetyInfo.getBeach() != null ? new BeachDTO() {{
            setId(safetyInfo.getBeach().getId());
            setName(safetyInfo.getBeach().getName());
            setCity(safetyInfo.getBeach().getCity());
            setDescription(safetyInfo.getBeach().getDescription());
            setStatus("Active"); // Default or fetch from Beach if available
            setActiveUsers(0); // Default, adjust if tracking active users
            setAlerts(0); // Default, adjust if tracking alerts
            setLastUpdated(safetyInfo.getLastUpdated().toString()); // Align with SafetyInfo
            setZones(null); // Avoid fetching zones to prevent lazy loading issues
        }} : null;
        return new SafetyInfoDTO(
            safetyInfo.getId(),
            safetyInfo.getTitle(),
            safetyInfo.getCategory(),
            safetyInfo.getContent(),
            safetyInfo.getStatus(),
            safetyInfo.getLastUpdated(),
            safetyInfo.getUpdatedBy(),
            safetyInfo.getViews(),
            beachDTO
        );
    }
}