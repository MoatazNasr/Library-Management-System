package com.example.Library.Management.controllers;

import com.example.Library.Management.entities.Book;
import com.example.Library.Management.services.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BookController {
    private final BookService bookService;
    @GetMapping("/book/{id}")
    public ResponseEntity<Book> findById(@PathVariable("id") String id) {
        return ResponseEntity.ok(bookService.findById(id));
    }
    @GetMapping("/book")
    public ResponseEntity<List<Book>> findAll() {
        return ResponseEntity.ok(bookService.findAll());
    }
    @PostMapping("/book")
    public ResponseEntity<Void> save(@RequestBody @Valid Book book) {
        bookService.save(book);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/book/{id}")
    public ResponseEntity<Void> update(@RequestBody @Valid Book book,
                                       @PathVariable("id") String id) {
        bookService.update(book,id);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/book/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        bookService.delete(id);
        return ResponseEntity.ok().build();
    }
}
