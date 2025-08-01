package com.example.Surf.Repositories;

import com.example.Surf.Models.DailyTip;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.Optional;

public interface DailyTipRepository extends JpaRepository<DailyTip, Long> {
    Optional<DailyTip> findByValidDateAndLanguage(LocalDate validDate, String language);
}