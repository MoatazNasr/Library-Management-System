package com.example.Library.Management.services;

import com.example.Library.Management.entities.Book;
import com.example.Library.Management.exceptions.ResourceAlreadyExists;
import com.example.Library.Management.exceptions.ResourceNotFound;
import com.example.Library.Management.repositories.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImp implements BookService{
    private final BookRepository bookRepository;
    @Override
    public Book findById(String id) {
        Optional<Book> book = bookRepository.findById(id);
        if(book.isEmpty()) throw new ResourceNotFound("Book Doesn't Exist");
        return book.get();
    }
    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
    @Override
    public Book save(Book book) {
        if(bookRepository.existsByTitle(book.getTitle()))
            throw new ResourceAlreadyExists("Book Already Exists");
        return bookRepository.save(book);
    }
    @Override
    @Transactional
    public Book update(Book book,String id) {
        this.findById(id);
        book.setId(id);
        return bookRepository.save(book);
    }
    @Override
    @Transactional
    public void delete(String id) {
        Book book = this.findById(id);
        bookRepository.delete(book);
    }
}
