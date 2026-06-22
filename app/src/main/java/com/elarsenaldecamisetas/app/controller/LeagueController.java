package com.elarsenaldecamisetas.app.controller;

import com.elarsenaldecamisetas.app.dto.LeagueDTO;
import com.elarsenaldecamisetas.app.service.LeagueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leagues")
@RequiredArgsConstructor
public class LeagueController {

    private final LeagueService leagueService;

    @GetMapping
    public ResponseEntity<List<LeagueDTO>> findAll() {
        return ResponseEntity.ok(leagueService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeagueDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(leagueService.findById(id));
    }

    @PostMapping
    public ResponseEntity<LeagueDTO> create(@Valid @RequestBody LeagueDTO leagueDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(leagueService.create(leagueDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeagueDTO> update(@PathVariable Long id, @Valid @RequestBody LeagueDTO leagueDTO) {
        return ResponseEntity.ok(leagueService.update(id, leagueDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        leagueService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
