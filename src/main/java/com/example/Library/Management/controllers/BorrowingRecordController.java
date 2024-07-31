package com.example.Library.Management.controllers;

import com.example.Library.Management.entities.Patron;
import com.example.Library.Management.services.BorrowingRecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BorrowingRecordController {
    private final BorrowingRecordService borrowingRecordService;
    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<Void> save(@PathVariable("bookId") String bookId,
                                     @PathVariable("patronId") String patronId) {
        borrowingRecordService.save(patronId,bookId);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<Void> update(@PathVariable("bookId") String bookId,
                                       @PathVariable("patronId") String patronId) {
        borrowingRecordService.update(patronId,bookId);
        return ResponseEntity.ok().build();
    }
}
