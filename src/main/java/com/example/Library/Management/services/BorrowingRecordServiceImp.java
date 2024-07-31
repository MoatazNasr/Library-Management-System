package com.example.Library.Management.services;

import com.example.Library.Management.entities.Book;
import com.example.Library.Management.entities.BorrowingRecord;
import com.example.Library.Management.entities.Patron;
import com.example.Library.Management.exceptions.ResourceNotFound;
import com.example.Library.Management.repositories.BorrowingRecordRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BorrowingRecordServiceImp implements BorrowingRecordService{
    private final BorrowingRecordRepository borrowingRecordRepository;
    private final PatronService patronService;
    private final BookService bookService;
    @Override
    @Transactional
    public BorrowingRecord save(String patronId, String bookId) {
        Patron patron = patronService.findById(patronId);
        Book book = bookService.findById(bookId);
        Optional<BorrowingRecord> borrowedBook = borrowingRecordRepository.
                findByBookIdAndReturningDate(bookId,null);
        if(borrowedBook.isPresent()) throw new ResourceNotFound("Sorry the book copy is borrowed");
        BorrowingRecord newBorrowingRecord = new BorrowingRecord();
        newBorrowingRecord.setBorrowingDate(LocalDate.now());
        newBorrowingRecord.setPatron(patron);
        newBorrowingRecord.setBook(book);
        return borrowingRecordRepository.save(newBorrowingRecord);
    }
    @Override
    @Transactional
    public BorrowingRecord update(String patronId, String bookId) {
        Optional<BorrowingRecord> borrowedBook = borrowingRecordRepository.
                findByBookIdAndReturningDate(bookId,null);
        if(borrowedBook.isEmpty()) throw new ResourceNotFound("There is no book to update");
        borrowedBook.get().setReturningDate(LocalDate.now());
        return borrowingRecordRepository.save(borrowedBook.get());
    }
}
