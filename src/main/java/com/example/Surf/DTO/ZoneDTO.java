package com.example.Surf.DTO;

import java.util.List;

public class ZoneDTO {
    private Long id;
    private String name;
    private String type;
    private String color;
    private String coordinates;
    private List<String> rules;
    private BeachDTO beach;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public String getCoordinates() { return coordinates; }
    public void setCoordinates(String coordinates) { this.coordinates = coordinates; }
    public List<String> getRules() { return rules; }
    public void setRules(List<String> rules) { this.rules = rules; }
    public BeachDTO getBeach() { return beach; }
    public void setBeach(BeachDTO beach) { this.beach = beach; }
}