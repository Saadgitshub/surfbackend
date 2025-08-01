package com.example.Surf.Repositories;

import com.example.Surf.Models.Beach;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BeachRepository extends JpaRepository<Beach, Long> {
    List<Beach> findByCity(String city);
}