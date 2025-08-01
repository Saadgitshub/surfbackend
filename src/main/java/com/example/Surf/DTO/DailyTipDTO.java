package com.example.Surf.DTO;

import java.time.LocalDate;

public class DailyTipDTO {
    private Long id;
    private String message;
    private String category; // Changed from 'type' to align with frontend
    private LocalDate validDate;
    private String language;
    private String status;
    private String createdBy;
    private Long views;
    private Long likes;

    // Constructors
    public DailyTipDTO() {}

    public DailyTipDTO(Long id, String message, String category, LocalDate validDate, String language, String status, String createdBy, Long views, Long likes) {
        this.id = id;
        this.message = message;
        this.category = category;
        this.validDate = validDate;
        this.language = language;
        this.status = status;
        this.createdBy = createdBy;
        this.views = views;
        this.likes = likes;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getValidDate() {
        return validDate;
    }

    public void setValidDate(LocalDate validDate) {
        this.validDate = validDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }
}
