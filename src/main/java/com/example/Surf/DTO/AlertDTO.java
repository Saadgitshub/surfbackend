package com.example.Surf.DTO;

import java.time.LocalDateTime;

public class AlertDTO {
    private Long id;
    private String title;
    private String message;
    private String type;
    private String status;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private Long views;
    private BeachDTO beach;
    private Long zoneId;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getExpiresAt() { return expiresAt; }
    public void setExpiresAt(LocalDateTime expiresAt) { this.expiresAt = expiresAt; }
    public Long getViews() { return views; }
    public void setViews(Long views) { this.views = views; }
    public BeachDTO getBeach() { return beach; }
    public void setBeach(BeachDTO beach) { this.beach = beach; }
    public Long getZoneId() { return zoneId; }
    public void setZoneId(Long zoneId) { this.zoneId = zoneId; }
}