package com.example.Library.Management.repositories;

import com.example.Library.Management.entities.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;
    @Test
    void ItChecksIfBookExistsByTitle() {
        Book book = bookRepository.save(new Book("1",
                "Good Writing tips",
                "Sarah Adam",
                "200-3450-6000-120",
                (short) 1500,
                null));
        bookRepository.save(book);
        Boolean expected = bookRepository.existsByTitle(book.getTitle());
        assertThat(expected).isTrue();
    }
}