package com.example.Surf.DTO;

import java.util.List;

public class BeachDTO {
    private Long id;
    private String name;
    private String city;
    private String description;
    private String status;
    private Integer activeUsers;
    private Integer alerts;
    private String lastUpdated;
    private List<ZoneDTO> zones;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Integer getActiveUsers() { return activeUsers; }
    public void setActiveUsers(Integer activeUsers) { this.activeUsers = activeUsers; }
    public Integer getAlerts() { return alerts; }
    public void setAlerts(Integer alerts) { this.alerts = alerts; }
    public String getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(String lastUpdated) { this.lastUpdated = lastUpdated; }
    public List<ZoneDTO> getZones() { return zones; }
    public void setZones(List<ZoneDTO> zones) { this.zones = zones; }
}