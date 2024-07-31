package com.example.Library.Management.services;

import com.example.Library.Management.entities.Book;
import java.util.List;

public interface BookService {
    Book save(Book book);
    Book update(Book book,String id);
    Book findById(String id);
    List<Book> findAll();
    void delete(String id);

}
