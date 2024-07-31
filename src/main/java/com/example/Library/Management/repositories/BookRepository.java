package com.example.Library.Management.repositories;

import com.example.Library.Management.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,String> {
    Boolean existsByTitle(String title);
}
