package com.example.Library.Management.repositories;

import com.example.Library.Management.entities.Book;
import com.example.Library.Management.entities.BorrowingRecord;
import com.example.Library.Management.entities.Patron;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalDate;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class BorrowingRecordRepositoryTest {
    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PatronRepository patronRepository;
    @Test
    void ItFindsByBookIdAndReturningDate() {
        Patron patron = patronRepository.save(new Patron("1",
                "moataz@gmail.com",
                "123456789",
                null,
                null));
        Book book = bookRepository.save(new Book("1",
                "Good Writing tips",
                "Sarah Adam",
                "200-3450-6000-120",
                (short) 1500,
                null));;
        borrowingRecordRepository.save(new BorrowingRecord("1",
                patron,
                book,
                LocalDate.now(),
                null));
        Optional<BorrowingRecord> borrowedBook =
        borrowingRecordRepository.findByBookIdAndReturningDate(book.getId(),null);
        assertThat(borrowedBook.isPresent()).isTrue();
    }
}