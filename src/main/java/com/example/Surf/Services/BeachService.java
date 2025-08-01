package com.example.Surf.Services;

import com.example.Surf.DTO.*;
import com.example.Surf.Models.*;
import com.example.Surf.Repositories.BeachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BeachService {

    @Autowired
    private BeachRepository beachRepository;

    public List<BeachDTO> getAllBeaches() {
        return beachRepository.findAll().stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }

    public Optional<BeachDTO> getBeachById(Long id) {
        return beachRepository.findById(id).map(this::mapToDTO);
    }

    public BeachDTO createBeach(BeachDTO beachDTO) {
        Beach beach = new Beach();
        beach.setName(beachDTO.getName());
        beach.setCity(beachDTO.getCity());
        beach.setDescription(beachDTO.getDescription());
        beach.setZones(beachDTO.getZones() != null ? beachDTO.getZones().stream()
            .map(this::mapToZone)
            .collect(Collectors.toList()) : List.of());
        Beach savedBeach = beachRepository.save(beach);
        return mapToDTO(savedBeach);
    }

    public Optional<BeachDTO> updateBeach(Long id, BeachDTO beachDTO) {
        return beachRepository.findById(id).map(beach -> {
            beach.setName(beachDTO.getName());
            beach.setCity(beachDTO.getCity());
            beach.setDescription(beachDTO.getDescription());
            beach.setZones(beachDTO.getZones() != null ? beachDTO.getZones().stream()
                .map(this::mapToZone)
                .collect(Collectors.toList()) : beach.getZones());
            return mapToDTO(beachRepository.save(beach));
        });
    }

    public boolean deleteBeach(Long id) {
        if (beachRepository.existsById(id)) {
            beachRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private BeachDTO mapToDTO(Beach beach) {
        BeachDTO dto = new BeachDTO();
        dto.setId(beach.getId());
        dto.setName(beach.getName());
        dto.setCity(beach.getCity());
        dto.setDescription(beach.getDescription());
        dto.setZones(beach.getZones() != null ? beach.getZones().stream()
            .map(this::mapToZoneDTO)
            .collect(Collectors.toList()) : List.of());
        return dto;
    }

    private Zone mapToZone(ZoneDTO zoneDTO) {
        Zone zone = new Zone();
        zone.setId(zoneDTO.getId());
        zone.setName(zoneDTO.getName());
        zone.setColor(zoneDTO.getColor());
        zone.setCoordinates(zoneDTO.getCoordinates());
        zone.setType(zoneDTO.getType());
        return zone;
    }

    private ZoneDTO mapToZoneDTO(Zone zone) {
        ZoneDTO zoneDTO = new ZoneDTO();
        zoneDTO.setId(zone.getId());
        zoneDTO.setName(zone.getName());
        zoneDTO.setColor(zone.getColor());
        zoneDTO.setCoordinates(zone.getCoordinates());
        zoneDTO.setType(zone.getType());
        return zoneDTO;
    }
    
}