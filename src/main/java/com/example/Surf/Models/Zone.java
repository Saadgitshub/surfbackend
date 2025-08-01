package com.example.Surf.Models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "zone")
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;

    private String color;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String coordinates;

    @ElementCollection
    private List<String> rules;

    @ManyToOne
    @JoinColumn(name = "beach_id")
    private Beach beach;

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
    public Beach getBeach() { return beach; }
    public void setBeach(Beach beach) { this.beach = beach; }
}