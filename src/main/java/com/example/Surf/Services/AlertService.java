package com.example.Surf.Services;

import com.example.Surf.DTO.AlertDTO;
import com.example.Surf.DTO.*;
import com.example.Surf.Models.Alert;
import com.example.Surf.Models.Beach;
import com.example.Surf.Models.Zone;
import com.example.Surf.Repositories.AlertRepository;
import com.example.Surf.Repositories.BeachRepository;
import com.example.Surf.Repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlertService {

    private static final Logger logger = LoggerFactory.getLogger(AlertService.class);

    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private BeachRepository beachRepository;

    @Autowired
    private ZoneRepository zoneRepository;

    public AlertDTO createAlert(AlertDTO alertDTO) {
        logger.info("Creating alert with DTO: {}", alertDTO);
        if (alertDTO.getBeach() != null && alertDTO.getBeach().getId() != null) {
            Optional<Beach> beach = beachRepository.findById(alertDTO.getBeach().getId());
            if (!beach.isPresent()) {
                logger.error("Invalid beach ID: {}", alertDTO.getBeach().getId());
                throw new IllegalArgumentException("Invalid beach ID: " + alertDTO.getBeach().getId());
            }
        }
        Zone zone = null;
        if (alertDTO.getZoneId() != null) {
            Optional<Zone> zoneOpt = zoneRepository.findById(alertDTO.getZoneId());
            if (zoneOpt.isPresent()) {
                zone = zoneOpt.get();
            } else {
                logger.warn("Invalid zone ID: {}. Setting zone to null.", alertDTO.getZoneId());
            }
        }
        if (alertDTO.getMessage() == null || alertDTO.getMessage().isEmpty()) {
            logger.error("Message is required");
            throw new IllegalArgumentException("Message is required");
        }
        if (alertDTO.getType() == null || alertDTO.getType().isEmpty()) {
            logger.error("Type is required");
            throw new IllegalArgumentException("Type is required");
        }

        Alert alert = new Alert();
        alert.setTitle(alertDTO.getTitle() != null && !alertDTO.getTitle().isEmpty() ? alertDTO.getTitle() : null);
        alert.setMessage(alertDTO.getMessage());
        alert.setType(alertDTO.getType());
        alert.setCreatedAt(LocalDateTime.now()); // Always set by backend
        alert.setBeach(alertDTO.getBeach() != null && alertDTO.getBeach().getId() != null ? 
                       beachRepository.findById(alertDTO.getBeach().getId()).orElse(null) : null);
        alert.setZone(zone);
        alert.setStatus(alertDTO.getStatus() != null ? alertDTO.getStatus() : "Draft");
        alert.setCreatedBy(alertDTO.getCreatedBy() != null ? alertDTO.getCreatedBy() : "Admin");
        alert.setExpiresAt(alertDTO.getExpiresAt());
        alert.setViews(alertDTO.getViews() != null ? alertDTO.getViews() : 0L);

        logger.info("Saving alert: {}", alert);
        Alert savedAlert = alertRepository.save(alert);
        logger.info("Saved alert: {}", savedAlert);
        return mapToDTO(savedAlert);
    }

    public Optional<AlertDTO> getAlertById(Long id) {
        logger.info("Fetching alert by ID: {}", id);
        Optional<AlertDTO> alert = alertRepository.findById(id).map(this::mapToDTO);
        logger.info("Fetched alert: {}", alert);
        return alert;
    }

    public List<AlertDTO> getAlertsByBeachId(Long beachId) {
        logger.info("Fetching alerts by beach ID: {}", beachId);
        List<AlertDTO> alerts = alertRepository.findByBeachId(beachId).stream().map(this::mapToDTO).collect(Collectors.toList());
        logger.info("Fetched {} alerts for beach ID: {}", alerts.size(), beachId);
        return alerts;
    }

    public List<AlertDTO> getAlertsByType(String type) {
        logger.info("Fetching alerts by type: {}", type);
        List<AlertDTO> alerts = alertRepository.findByType(type).stream().map(this::mapToDTO).collect(Collectors.toList());
        logger.info("Fetched {} alerts for type: {}", alerts.size(), type);
        return alerts;
    }

    public List<AlertDTO> getAllAlerts() {
        logger.info("Fetching all alerts");
        List<AlertDTO> alerts = alertRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
        logger.info("Fetched {} alerts", alerts.size());
        return alerts;
    }

    public Optional<AlertDTO> updateAlert(Long id, AlertDTO alertDTO) {
        logger.info("Updating alert ID: {} with DTO: {}", id, alertDTO);
        return alertRepository.findById(id).map(alert -> {
            if (alertDTO.getBeach() != null && alertDTO.getBeach().getId() != null) {
                Optional<Beach> beach = beachRepository.findById(alertDTO.getBeach().getId());
                if (!beach.isPresent()) {
                    logger.error("Invalid beach ID: {}", alertDTO.getBeach().getId());
                    throw new IllegalArgumentException("Invalid beach ID: " + alertDTO.getBeach().getId());
                }
                alert.setBeach(beach.get());
            } else {
                alert.setBeach(null);
            }
            Zone zone = null;
            if (alertDTO.getZoneId() != null) {
                Optional<Zone> zoneOpt = zoneRepository.findById(alertDTO.getZoneId());
                if (zoneOpt.isPresent()) {
                    zone = zoneOpt.get();
                } else {
                    logger.warn("Invalid zone ID: {}. Setting zone to null.", alertDTO.getZoneId());
                }
            }
            alert.setZone(zone);
            if (alertDTO.getMessage() == null || alertDTO.getMessage().isEmpty()) {
                logger.error("Message is required");
                throw new IllegalArgumentException("Message is required");
            }
            if (alertDTO.getType() == null || alertDTO.getType().isEmpty()) {
                logger.error("Type is required");
                throw new IllegalArgumentException("Type is required");
            }
            alert.setTitle(alertDTO.getTitle() != null && !alertDTO.getTitle().isEmpty() ? alertDTO.getTitle() : null);
            alert.setMessage(alertDTO.getMessage());
            alert.setType(alertDTO.getType());
            alert.setStatus(alertDTO.getStatus() != null ? alertDTO.getStatus() : "Active");
            alert.setCreatedBy(alertDTO.getCreatedBy() != null ? alertDTO.getCreatedBy() : "Admin");
            alert.setExpiresAt(alertDTO.getExpiresAt());
            alert.setViews(alertDTO.getViews() != null ? alertDTO.getViews() : 0L);
            logger.info("Updating alert: {}", alert);
            Alert updatedAlert = alertRepository.save(alert);
            logger.info("Updated alert: {}", updatedAlert);
            return mapToDTO(updatedAlert);
        });
    }

    public boolean deleteAlert(Long id) {
        logger.info("Deleting alert ID: {}", id);
        if (alertRepository.existsById(id)) {
            alertRepository.deleteById(id);
            logger.info("Deleted alert ID: {}", id);
            return true;
        }
        logger.warn("Alert ID: {} not found", id);
        return false;
    }

    private AlertDTO mapToDTO(Alert alert) {
        AlertDTO dto = new AlertDTO();
        dto.setId(alert.getId());
        dto.setTitle(alert.getTitle());
        dto.setMessage(alert.getMessage());
        dto.setType(alert.getType());
        dto.setCreatedAt(alert.getCreatedAt());
        dto.setBeach(alert.getBeach() != null ? new BeachDTO() {{ 
            setId(alert.getBeach().getId()); 
            setName(alert.getBeach().getName()); 
        }} : null);
        dto.setZoneId(alert.getZone() != null ? alert.getZone().getId() : null);
        dto.setStatus(alert.getStatus());
        dto.setCreatedBy(alert.getCreatedBy());
        dto.setExpiresAt(alert.getExpiresAt());
        dto.setViews(alert.getViews());
        return dto;
    }
}