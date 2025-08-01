package com.example.Surf.Repositories;

import com.example.Surf.Models.SafetyInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SafetyInfoRepository extends JpaRepository<SafetyInfo, Long> {
    List<SafetyInfo> findByBeachId(Long beachId);
    List<SafetyInfo> findByCategory(String category);
}