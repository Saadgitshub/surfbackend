package com.example.Surf.Services;


import com.example.Surf.Models.Beach;
import com.example.Surf.Models.Zone;
import com.example.Surf.DTO.*;
import com.example.Surf.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ZoneService {

    @Autowired
    private ZoneRepository zoneRepository;

    public ZoneDTO createZone(ZoneDTO zoneDTO) {
        Zone zone = new Zone();
        zone.setName(zoneDTO.getName());
        zone.setType(zoneDTO.getType());
        zone.setColor(zoneDTO.getColor());
        zone.setCoordinates(zoneDTO.getCoordinates());
        zone.setRules(zoneDTO.getRules());
        if (zoneDTO.getBeach() != null) {
            Beach beach = new Beach();
            beach.setId(zoneDTO.getBeach().getId());
            zone.setBeach(beach);
        }
        Zone savedZone = zoneRepository.save(zone);
        return mapToDTO(savedZone);
    }

    public Optional<ZoneDTO> getZoneById(Long id) {
        return zoneRepository.findById(id).map(this::mapToDTO);
    }

    public List<ZoneDTO> getZonesByBeachId(Long beachId) {
        return zoneRepository.findByBeachId(beachId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<ZoneDTO> getZonesByType(String type) {
        return zoneRepository.findByType(type).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<ZoneDTO> getAllZones() {
        return zoneRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ZoneDTO> updateZone(Long id, ZoneDTO updatedZoneDTO) {
        return zoneRepository.findById(id).map(existingZone -> {
            existingZone.setName(updatedZoneDTO.getName());
            existingZone.setType(updatedZoneDTO.getType());
            existingZone.setColor(updatedZoneDTO.getColor());
            existingZone.setCoordinates(updatedZoneDTO.getCoordinates());
            existingZone.setRules(updatedZoneDTO.getRules());
            if (updatedZoneDTO.getBeach() != null) {
                Beach beach = new Beach();
                beach.setId(updatedZoneDTO.getBeach().getId());
                existingZone.setBeach(beach);
            }
            Zone updatedZone = zoneRepository.save(existingZone);
            return mapToDTO(updatedZone);
        });
    }

    public boolean deleteZone(Long id) {
        if (zoneRepository.existsById(id)) {
            zoneRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private ZoneDTO mapToDTO(Zone zone) {
        ZoneDTO zoneDTO = new ZoneDTO();
        zoneDTO.setId(zone.getId());
        zoneDTO.setName(zone.getName());
        zoneDTO.setType(zone.getType());
        zoneDTO.setColor(zone.getColor());
        zoneDTO.setCoordinates(zone.getCoordinates());
        zoneDTO.setRules(zone.getRules());
        if (zone.getBeach() != null) {
            BeachDTO beachDTO = new BeachDTO();
            beachDTO.setId(zone.getBeach().getId());
            beachDTO.setName(zone.getBeach().getName());
            beachDTO.setCity(zone.getBeach().getCity());
            beachDTO.setDescription(zone.getBeach().getDescription());
            zoneDTO.setBeach(beachDTO);
        }
        return zoneDTO;
    }
     public List<Zone> findAllZones() {
        return zoneRepository.findAll();
    }
}