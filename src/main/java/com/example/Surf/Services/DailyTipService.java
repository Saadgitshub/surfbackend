package com.example.Surf.Services;

import com.example.Surf.DTO.DailyTipDTO;
import com.example.Surf.Models.DailyTip;
import com.example.Surf.Repositories.DailyTipRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DailyTipService {

    @Autowired
    private DailyTipRepository dailyTipRepository;

    public DailyTip createDailyTip(DailyTipDTO dto) {
        DailyTip dailyTip = new DailyTip();
        dailyTip.setTitle(dto.getTitle());
        dailyTip.setContent(dto.getContent());
        dailyTip.setCategory(dto.getCategory());
        dailyTip.setStatus(dto.getStatus());
        dailyTip.setDate(dto.getDate());
        dailyTip.setLastUpdated(dto.getLastUpdated());
        dailyTip.setUpdatedBy(dto.getUpdatedBy());
        dailyTip.setViews(dto.getViews() != null ? dto.getViews() : 0);
        dailyTip.setLikes(dto.getLikes() != null ? dto.getLikes() : 0);
        return dailyTipRepository.save(dailyTip);
    }

    public Optional<DailyTip> getDailyTipById(Long id) {
        return dailyTipRepository.findById(id);
    }

    public Optional<DailyTip> getDailyTip(String language, String date) {
        List<DailyTip> tips = dailyTipRepository.findByDateAndStatus(date, "ACTIVE");
        return tips.stream()
                .filter(tip -> {
                    try {
                        JSONObject content = new JSONObject(tip.getContent());
                        return language.equals(content.getString("lang"));
                    } catch (Exception e) {
                        return false;
                    }
                })
                .findFirst();
    }

    public Optional<DailyTip> getLatestActiveTip() {
        return dailyTipRepository.findFirstByStatusOrderByDateDesc("ACTIVE");
    }

    public List<DailyTip> getAllDailyTips() {
        return dailyTipRepository.findAll();
    }

    public Optional<DailyTip> updateDailyTip(Long id, DailyTipDTO dto) {
        return dailyTipRepository.findById(id).map(dailyTip -> {
            dailyTip.setTitle(dto.getTitle());
            dailyTip.setContent(dto.getContent());
            dailyTip.setCategory(dto.getCategory());
            dailyTip.setStatus(dto.getStatus());
            dailyTip.setDate(dto.getDate());
            dailyTip.setLastUpdated(dto.getLastUpdated());
            dailyTip.setUpdatedBy(dto.getUpdatedBy());
            dailyTip.setViews(dto.getViews() != null ? dto.getViews() : dailyTip.getViews());
            dailyTip.setLikes(dto.getLikes() != null ? dto.getLikes() : dailyTip.getLikes());
            return dailyTipRepository.save(dailyTip);
        });
    }

    public boolean deleteDailyTip(Long id) {
        return dailyTipRepository.findById(id).map(dailyTip -> {
            dailyTipRepository.delete(dailyTip);
            return true;
        }).orElse(false);
    }

    public DailyTipDTO mapToDTO(DailyTip dailyTip) {
        DailyTipDTO dto = new DailyTipDTO();
        dto.setId(dailyTip.getId());
        dto.setTitle(dailyTip.getTitle());
        dto.setContent(dailyTip.getContent());
        dto.setCategory(dailyTip.getCategory());
        dto.setStatus(dailyTip.getStatus());
        dto.setDate(dailyTip.getDate());
        dto.setLastUpdated(dailyTip.getLastUpdated());
        dto.setUpdatedBy(dailyTip.getUpdatedBy());
        dto.setViews(dailyTip.getViews());
        dto.setLikes(dailyTip.getLikes());
        return dto;
    }
}