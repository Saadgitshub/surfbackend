package com.example.Surf.Controllers;

import com.example.Surf.DTO.BeachDTO;
import com.example.Surf.Services.BeachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/beaches")
public class BeachController {

    @Autowired
    private BeachService beachService;

    @GetMapping
    public ResponseEntity<List<BeachDTO>> getAllBeaches() {
        return ResponseEntity.ok(beachService.getAllBeaches());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BeachDTO> getBeachById(@PathVariable Long id) {
        return beachService.getBeachById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BeachDTO> createBeach(@RequestBody BeachDTO beachDTO) {
        return ResponseEntity.ok(beachService.createBeach(beachDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BeachDTO> updateBeach(@PathVariable Long id, @RequestBody BeachDTO beachDTO) {
        return beachService.updateBeach(id, beachDTO)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBeach(@PathVariable Long id) {
        return beachService.deleteBeach(id)
            ? ResponseEntity.ok().build()
            : ResponseEntity.notFound().build();
    }
}