package com.example.Surf.Repositories;

import com.example.Surf.Models.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ZoneRepository extends JpaRepository<Zone, Long> {
    List<Zone> findByBeachId(Long beachId);
    List<Zone> findByType(String type);
}