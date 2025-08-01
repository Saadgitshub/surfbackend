package com.example.Surf.Repositories;

import com.example.Surf.Models.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Long> {
    List<Alert> findByBeachId(Long beachId);
    List<Alert> findByType(String type);
}