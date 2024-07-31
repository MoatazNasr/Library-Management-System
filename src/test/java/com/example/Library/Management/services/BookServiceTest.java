package com.example.Library.Management.services;

import com.example.Library.Management.entities.Book;
import com.example.Library.Management.exceptions.ResourceAlreadyExists;
import com.example.Library.Management.exceptions.ResourceNotFound;
import com.example.Library.Management.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private BookServiceImp bookService;
    @Test
    void ItFindsBookById() {
        // Arrange
        Book mockBook = Mockito.mock(Book.class);
        when(bookRepository.findById("1")).
                thenReturn(Optional.of(mockBook));
        // Act
        Book result = bookService.findById("1");
        // Assert
        assertEquals(result,mockBook);
    }
    @Test
    void ItFindsAllBooks() {
        // Arrange
        List<Book> books = new ArrayList<>();
        when(bookRepository.findAll()).thenReturn(books);
        // Act
        List<Book> tempBooks = bookService.findAll();
        // Assert
        assertEquals(tempBooks,books);
    }

    @Test
    void ItSavesBookWithNewTitle() {
        // Arrange
        Book mockBook = Mockito.mock(Book.class);
        when(bookRepository.existsByTitle(mockBook.getTitle())).thenReturn(false);
        when(bookRepository.save(mockBook)).thenReturn(mockBook);
        // Act
        Book savedBook = bookService.save(mockBook);
        // Assert
        assertThat(savedBook).isNotNull();
    }
    @Test
    void ItSkipsSavingBookWithExistentTitle() {
        Book savedBook = null;
        try {
            // Arrange
            Book mockBook = Mockito.mock(Book.class);
            when(bookRepository.existsByTitle(mockBook.getTitle())).thenReturn(true);
            // Act
            savedBook = bookService.save(mockBook);
        } catch (ResourceAlreadyExists e) {
            // Assert
            assertThat(savedBook).isNull();
        }

    }
    @Test
    void ItUpdatesBookIfExists() {
        // Arrange
        Book mockBook = Mockito.mock(Book.class);
        when(bookRepository.findById(mockBook.getId())).thenReturn(Optional.of(mockBook));
        when(bookRepository.save(mockBook)).thenReturn(mockBook);
        // Act
        Book updatedBook = bookService.update(mockBook,mockBook.getId());
        // Assert
        assertEquals(mockBook,updatedBook);
    }
    @Test
    void ItSkipsUpdateBookIfNotExists() {
        Book updatedBook = null;
        try {
            // Arrange
            Book mockBook = Mockito.mock(Book.class);
            when(bookRepository.findById(mockBook.getId())).thenReturn(Optional.empty());
            // Act
            updatedBook = bookService.update(mockBook,mockBook.getId());
        } catch (ResourceNotFound e) {
            // Assert
            assertThat(updatedBook).isNull();
        }
    }
    @Test
    void ItDeletesBookIfExists() {
        // Arrange
        boolean deleted = false;
        Book book = new Book("1",
                "Good Writing tips",
                "Sarah Adam",
                "200-3450-6000-120",
                (short) 1500,
                null);
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        doNothing().when(bookRepository).delete(book);
        // Act
        bookService.delete(book.getId());
        deleted = true;
        assertSame(deleted,true);
    }
    @Test
    void ItSkipsDeleteBookIfNotExists() {
        // Arrange
        boolean deleted;
        try {
            Book book = new Book("1",
                    "Good Writing tips",
                    "Sarah Adam",
                    "200-3450-6000-120",
                    (short) 1500,
                    null);
            when(bookRepository.findById(book.getId())).thenReturn(Optional.empty());
            // Act
            bookService.delete(book.getId());
            deleted = true;
        }
        catch (ResourceNotFound e) {
            // Assert
            deleted = false;
        }
        assertSame(deleted,false);
    }
}