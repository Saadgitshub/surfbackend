package com.example.Surf.Repositories;

import com.example.Surf.Models.DailyTip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DailyTipRepository extends JpaRepository<DailyTip, Long> {
    List<DailyTip> findByDateAndStatus(String date, String status);
    Optional<DailyTip> findFirstByStatusOrderByDateDesc(String status);
}