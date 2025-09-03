package com.example.Surf.Models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "daily_tips")
public class DailyTip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title; // e.g., "Stay Hydrated"
    private String content; // JSON string with lang, description, icon
    private String category; // e.g., "GENERAL", "SAFETY"
    private String status; // e.g., "ACTIVE", "DRAFT"
    private String date; // e.g., "2025-08-05"
    private LocalDateTime lastUpdated; // Last update timestamp
    private String updatedBy; // e.g., "Admin"
    private Long views; // View count
    private Long likes; // Like count

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