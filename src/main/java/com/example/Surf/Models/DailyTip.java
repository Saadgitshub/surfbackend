package com.example.Surf.Models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "daily_tip")
public class DailyTip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private String category; // Changed from 'type' to align with frontend (Health, Safety, Environment, Recreation)

    @Column(nullable = false)
    private LocalDate validDate;

    @Column(nullable = false)
    private String language; // e.g., "fr", "en"

    @Column(nullable = false)
    private String status; // Published, Scheduled, Draft

    @Column(nullable = false)
    private String createdBy;

    @Column(nullable = false)
    private Long views = 0L;

    @Column(nullable = false)
    private Long likes = 0L;

    // Constructors
    public DailyTip() {}

    public DailyTip(String message, String category, LocalDate validDate, String language, String status, String createdBy, Long views, Long likes) {
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