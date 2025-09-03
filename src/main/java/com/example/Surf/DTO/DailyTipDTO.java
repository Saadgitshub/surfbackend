package com.example.Surf.DTO;

import java.time.LocalDateTime;

public class DailyTipDTO {

    private Long id;
    private String title;
    private String content;
    private String category;
    private String status;
    private String date;
    private LocalDateTime lastUpdated;
    private String updatedBy;
    private Long views;
    private Long likes;

    // Constructor with all fields
    public DailyTipDTO(Long id, String title, String content, String category, String status, 
                      String date, LocalDateTime lastUpdated, String updatedBy, 
                      Long views, Long likes) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.category = category;
        this.status = status;
        this.date = date;
        this.lastUpdated = lastUpdated;
        this.updatedBy = updatedBy;
        this.views = views;
        this.likes = likes;
    }

    // Default constructor
    public DailyTipDTO() {
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public LocalDateTime getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(LocalDateTime lastUpdated) { this.lastUpdated = lastUpdated; }
    public String getUpdatedBy() { return updatedBy; }
    public void setUpdatedBy(String updatedBy) { this.updatedBy = updatedBy; }
    public Long getViews() { return views; }
    public void setViews(Long views) { this.views = views; }
    public Long getLikes() { return likes; }
    public void setLikes(Long likes) { this.likes = likes; }
}