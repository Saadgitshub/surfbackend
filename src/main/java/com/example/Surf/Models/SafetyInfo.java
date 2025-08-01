package com.example.Surf.Models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class SafetyInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title; // e.g., "Bathing Safety Rules"
    private String content; // Detailed safety instructions
    private String category; // e.g., "SAFETY", "RECOMMENDATIONS", "ACCESS"
    private String status; // e.g., "Active", "Draft"
    private LocalDateTime lastUpdated; // Last update timestamp
    private String updatedBy; // e.g., "Admin John"
    private Long views; // View count

    @ManyToOne
    @JoinColumn(name = "beach_id")
    private Beach beach;

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
    public LocalDateTime getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(LocalDateTime lastUpdated) { this.lastUpdated = lastUpdated; }
    public String getUpdatedBy() { return updatedBy; }
    public void setUpdatedBy(String updatedBy) { this.updatedBy = updatedBy; }
    public Long getViews() { return views; }
    public void setViews(Long views) { this.views = views; }
    public Beach getBeach() { return beach; }
    public void setBeach(Beach beach) { this.beach = beach; }
}