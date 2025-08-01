package com.example.Surf.Services;

import com.example.Surf.DTO.DailyTipDTO;
import com.example.Surf.Models.DailyTip;
import com.example.Surf.Repositories.DailyTipRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DailyTipService {
    private static final Logger logger = LoggerFactory.getLogger(DailyTipService.class);

    @Autowired
    private DailyTipRepository tipRepository;

    public DailyTipDTO getDailyTip(LocalDate date, String language) {
        logger.info("Fetching daily tip for date: {} and language: {}", date, language);
        Optional<DailyTip> tipOpt = tipRepository.findByValidDateAndLanguage(date, language);
        if (!tipOpt.isPresent()) {
            logger.warn("No tip found for date: {} and language: {}", date, language);
            return null;
        }
        DailyTip tip = tipOpt.get();
        logger.info("Fetched tip: {}", tip.getMessage());
        return mapToDTO(tip);
    }

    public List<DailyTipDTO> getAllTips() {
        logger.info("Fetching all daily tips");
        List<DailyTip> tips = tipRepository.findAll();
        logger.info("Fetched {} tips", tips.size());
        return tips.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public DailyTipDTO createTip(DailyTipDTO tipDTO) {
        logger.info("Creating tip with DTO: {}", tipDTO);
        if (tipDTO.getMessage() == null || tipDTO.getMessage().isEmpty()) {
            logger.error("Message is required");
            throw new IllegalArgumentException("Message is required");
        }
        if (tipDTO.getCategory() == null || tipDTO.getCategory().isEmpty()) {
            logger.error("Category is required");
            throw new IllegalArgumentException("Category is required");
        }
        if (tipDTO.getValidDate() == null) {
            logger.error("Valid date is required");
            throw new IllegalArgumentException("Valid date is required");
        }
        if (tipDTO.getLanguage() == null || tipDTO.getLanguage().isEmpty()) {
            logger.error("Language is required");
            throw new IllegalArgumentException("Language is required");
        }
        DailyTip tip = new DailyTip(
            tipDTO.getMessage(),
            tipDTO.getCategory(),
            tipDTO.getValidDate(),
            tipDTO.getLanguage(),
            tipDTO.getStatus() != null ? tipDTO.getStatus() : "Draft",
            tipDTO.getCreatedBy() != null ? tipDTO.getCreatedBy() : "Admin",
            tipDTO.getViews() != null ? tipDTO.getViews() : 0L,
            tipDTO.getLikes() != null ? tipDTO.getLikes() : 0L
        );
        DailyTip savedTip = tipRepository.save(tip);
        logger.info("Saved tip: {}", savedTip.getMessage());
        return mapToDTO(savedTip);
    }

    public Optional<DailyTipDTO> updateTip(Long id, DailyTipDTO tipDTO) {
        logger.info("Updating tip ID: {} with DTO: {}", id, tipDTO);
        return tipRepository.findById(id).map(tip -> {
            if (tipDTO.getMessage() == null || tipDTO.getMessage().isEmpty()) {
                logger.error("Message is required");
                throw new IllegalArgumentException("Message is required");
            }
            if (tipDTO.getCategory() == null || tipDTO.getCategory().isEmpty()) {
                logger.error("Category is required");
                throw new IllegalArgumentException("Category is required");
            }
            if (tipDTO.getValidDate() == null) {
                logger.error("Valid date is required");
                throw new IllegalArgumentException("Valid date is required");
            }
            if (tipDTO.getLanguage() == null || tipDTO.getLanguage().isEmpty()) {
                logger.error("Language is required");
                throw new IllegalArgumentException("Language is required");
            }
            tip.setMessage(tipDTO.getMessage());
            tip.setCategory(tipDTO.getCategory());
            tip.setValidDate(tipDTO.getValidDate());
            tip.setLanguage(tipDTO.getLanguage());
            tip.setStatus(tipDTO.getStatus() != null ? tipDTO.getStatus() : "Draft");
            tip.setCreatedBy(tipDTO.getCreatedBy() != null ? tipDTO.getCreatedBy() : "Admin");
            tip.setViews(tipDTO.getViews() != null ? tipDTO.getViews() : 0L);
            tip.setLikes(tipDTO.getLikes() != null ? tipDTO.getLikes() : 0L);
            DailyTip updatedTip = tipRepository.save(tip);
            logger.info("Updated tip: {}", updatedTip.getMessage());
            return mapToDTO(updatedTip);
        });
    }

    public boolean deleteTip(Long id) {
        logger.info("Deleting tip ID: {}", id);
        if (tipRepository.existsById(id)) {
            tipRepository.deleteById(id);
            logger.info("Deleted tip ID: {}", id);
            return true;
        }
        logger.warn("Tip ID: {} not found", id);
        return false;
    }

    private DailyTipDTO mapToDTO(DailyTip tip) {
        return new DailyTipDTO(
            tip.getId(),
            tip.getMessage(),
            tip.getCategory(),
            tip.getValidDate(),
            tip.getLanguage(),
            tip.getStatus(),
            tip.getCreatedBy(),
            tip.getViews(),
            tip.getLikes()
        );
    }
}