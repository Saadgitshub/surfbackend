package com.example.Surf.DTO;

import java.time.LocalDateTime;

public class SafetyInfoDTO {

    private Long id;
    private String title;
    private String category;
    private String content;
    private String status;
    private LocalDateTime lastUpdated;
    private String updatedBy;
    private Long views;
    private BeachDTO beach;

    // Constructor
    public SafetyInfoDTO(Long id, String title, String category, String content, String status, 
                        LocalDateTime lastUpdated, String updatedBy, Long views, BeachDTO beach) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.content = content;
        this.status = status;
        this.lastUpdated = lastUpdated;
        this.updatedBy = updatedBy;
        this.views = views;
        this.beach = beach;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(LocalDateTime lastUpdated) { this.lastUpdated = lastUpdated; }
    public String getUpdatedBy() { return updatedBy; }
    public void setUpdatedBy(String updatedBy) { this.updatedBy = updatedBy; }
    public Long getViews() { return views; }
    public void setViews(Long views) { this.views = views; }
    public BeachDTO getBeach() { return beach; }
    public void setBeach(BeachDTO beach) { this.beach = beach; }
}