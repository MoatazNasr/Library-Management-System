package com.example.Library.Management.services;

import com.example.Library.Management.entities.Book;
import com.example.Library.Management.entities.BorrowingRecord;
import com.example.Library.Management.entities.Patron;
import com.example.Library.Management.exceptions.ResourceNotFound;
import com.example.Library.Management.repositories.BorrowingRecordRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BorrowingRecordServiceTest {
    @Mock
    private PatronServiceImp patronService;
    @Mock
    private BookServiceImp bookService;
    @Mock
    private BorrowingRecordRepository borrowingRecordRepository;
    @InjectMocks
    private BorrowingRecordServiceImp borrowingRecordService;
    @Test
    void ItSavesBorrowingRecordIfBookNotBorrowed() {
        Boolean borrowingRecordSaved = false;
        // Arrange
        when(bookService.findById("12")).
                thenReturn(mock(Book.class));
        when(patronService.findById("123")).
                thenReturn(mock(Patron.class));
        when(borrowingRecordRepository.findByBookIdAndReturningDate(
                "12",
                null)).thenReturn(Optional.empty());
        borrowingRecordRepository.save(mock(BorrowingRecord.class));
        // Act
        borrowingRecordService.save("123", "12");
        borrowingRecordSaved = true;
        // Assert
        assertThat(borrowingRecordSaved).isTrue();
    }
    @Test
    void ItSkipsSavingBorrowingRecordIfBookIsBorrowed() {
        // Arrange
        Boolean borrowingRecordSaved = false;
        try {
            when(bookService.findById("1")).
                    thenReturn(mock(Book.class));
            when(patronService.findById("1")).
                    thenReturn(mock(Patron.class));
            when(borrowingRecordRepository.findByBookIdAndReturningDate(
                    "1",
                    null)).thenReturn(Optional.of(mock(BorrowingRecord.class)));
            // Act
             borrowingRecordService.save("1", "1");
            borrowingRecordSaved = true;
        }
        catch(ResourceNotFound e){};
        // Assert
        assertThat(borrowingRecordSaved).isFalse();
    }
    @Test
    void ItUpdatesBorrowingRecordIfBookIsBorrowed() {
        Boolean borrowingRecordUpdated = false;
        // Arrange
        when(borrowingRecordRepository.findByBookIdAndReturningDate(
                "1",
                null)).thenReturn(Optional.of(mock(BorrowingRecord.class)));
        borrowingRecordRepository.save(mock(BorrowingRecord.class));
        // Act
        borrowingRecordService.update("1","1");
        borrowingRecordUpdated = true;
        // Assert
        assertThat(borrowingRecordUpdated).isTrue();
    }
    @Test
    void ItSkipsUpdatingBorrowingRecordIfBookIsNotBorrowed() {
        Boolean borrowingRecordUpdated = false;
        try {
            // Arrange
            when(borrowingRecordRepository.findByBookIdAndReturningDate(
                    "1",
                    null)).thenReturn(Optional.empty());
            // Act
            borrowingRecordService.update("1", "1");
            borrowingRecordUpdated = true;
        }
        catch (ResourceNotFound e) {}
        // Assert
        assertThat(borrowingRecordUpdated).isFalse();
    }
}