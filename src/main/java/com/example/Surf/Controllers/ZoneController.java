package com.example.Surf.Controllers;

import com.example.Surf.DTO.*;
import com.example.Surf.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zones")
public class ZoneController {

    @Autowired
    private ZoneService zoneService;

    @PostMapping
    public ResponseEntity<ZoneDTO> createZone(@RequestBody ZoneDTO zoneDTO) {
        ZoneDTO createdZone = zoneService.createZone(zoneDTO);
        return ResponseEntity.ok(createdZone);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ZoneDTO> getZoneById(@PathVariable Long id) {
        return zoneService.getZoneById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/beach/{beachId}")
    public ResponseEntity<List<ZoneDTO>> getZonesByBeachId(@PathVariable Long beachId) {
        List<ZoneDTO> zones = zoneService.getZonesByBeachId(beachId);
        return ResponseEntity.ok(zones);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<ZoneDTO>> getZonesByType(@PathVariable String type) {
        List<ZoneDTO> zones = zoneService.getZonesByType(type);
        return ResponseEntity.ok(zones);
    }

    @GetMapping
    public ResponseEntity<List<ZoneDTO>> getAllZones() {
        List<ZoneDTO> zones = zoneService.getAllZones();
        return ResponseEntity.ok(zones);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ZoneDTO> updateZone(@PathVariable Long id, @RequestBody ZoneDTO zoneDTO) {
        return zoneService.updateZone(id, zoneDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteZone(@PathVariable Long id) {
        if (zoneService.deleteZone(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}