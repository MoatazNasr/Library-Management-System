package com.example.Library.Management.controllers;

import com.example.Library.Management.entities.Patron;
import com.example.Library.Management.services.PatronService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PatronController {
    private final PatronService patronService;
    @GetMapping("/patron/{id}")
    public ResponseEntity<Patron> findById(@PathVariable("id") String id) {
        return ResponseEntity.ok(patronService.findById(id));
    }
    @GetMapping("/patron")
    public ResponseEntity<List<Patron>> findAll() {
        return ResponseEntity.ok(patronService.findAll());
    }
    @PostMapping("/patron")
    public ResponseEntity<Void> save(@RequestBody @Valid Patron patron) {
        patronService.save(patron);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/patron/{id}")
    public ResponseEntity<Void> update(@RequestBody @Valid Patron patron,
                                       @PathVariable("id") String id) {
        patronService.update(patron,id);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/patron/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") String id) {
        patronService.delete(id);
        return ResponseEntity.ok().build();
    }

}
