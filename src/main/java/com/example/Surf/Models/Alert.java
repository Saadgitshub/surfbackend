package com.example.Surf.Models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title; // Added
    private String message;
    private String type; // e.g., "Critical", "High"
    private String status; // e.g., "Active", "Draft", "Expired"
    private String createdBy; // e.g., "Admin John"
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt; // Added
    private Long views; // Added

    @ManyToOne
    @JoinColumn(name = "beach_id")
    private Beach beach;

    @ManyToOne
    @JoinColumn(name = "zone_id")
    private Zone zone;

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
    public Beach getBeach() { return beach; }
    public void setBeach(Beach beach) { this.beach = beach; }
    public Zone getZone() { return zone; }
    public void setZone(Zone zone) { this.zone = zone; }
}