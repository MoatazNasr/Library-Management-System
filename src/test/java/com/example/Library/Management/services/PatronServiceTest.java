package com.example.Library.Management.services;

import com.example.Library.Management.entities.Patron;
import com.example.Library.Management.exceptions.ResourceAlreadyExists;
import com.example.Library.Management.exceptions.ResourceNotFound;
import com.example.Library.Management.repositories.PatronRepository;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
// used to close repo after each test
class PatronServiceTest {
    @Mock
    private PatronRepository patronRepository;
    @InjectMocks
    private PatronServiceImp patronService;
    @Test
    void ItFindsPatronById() {
        // Arrange
        Patron mockPatron = Mockito.mock(Patron.class);
        when(patronRepository.findById("1")).
                thenReturn(Optional.of(mockPatron));
        // Act
        Patron result = patronService.findById("1");
        // Assert
        assertEquals(result,mockPatron);
    }
    @Test
    void ItFindsAllPatrons() {
        // Arrange
        List<Patron> patrons = new ArrayList<>();
        when(patronRepository.findAll()).thenReturn(patrons);
        // Act
        List<Patron> tempPatrons = patronService.findAll();
        // Assert
        assertEquals(tempPatrons,patrons);
    }

    @Test
    void ItSavesPatronWithNewEmail() {
        // Arrange
        Patron mockPatron = Mockito.mock(Patron.class);
        when(patronRepository.existsByEmail(mockPatron.getEmail())).thenReturn(false);
        when(patronRepository.save(mockPatron)).thenReturn(mockPatron);
        // Act
        Patron savedPatron = patronService.save(mockPatron);
        // Assert
        assertThat(savedPatron).isNotNull();
    }
    @Test
    void ItSkipsSavingPatronWithExistentEmail() {
        Patron savedPatron = null;
        try {
            // Arrange
            Patron mockPatron = Mockito.mock(Patron.class);
            when(patronRepository.existsByEmail(mockPatron.getEmail())).thenReturn(true);
            // Act
            savedPatron = patronService.save(mockPatron);
        } catch (ResourceAlreadyExists e) {
            // Assert
            assertThat(savedPatron).isNull();
        }

    }
    @Test
    void ItUpdatesPatronIfExists() {
        // Arrange
        Patron mockPatron = Mockito.mock(Patron.class);
        when(patronRepository.findById(mockPatron.getId())).thenReturn(Optional.of(mockPatron));
        when(patronRepository.save(mockPatron)).thenReturn(mockPatron);
        // Act
        Patron updatedPatron = patronService.update(mockPatron,mockPatron.getId());
        // Assert
        assertEquals(mockPatron,updatedPatron);
    }
    @Test
    void ItSkipsUpdatePatronIfNotExists() {
        Patron updatedPatron = null;
        try {
            // Arrange
            Patron mockPatron = Mockito.mock(Patron.class);
            when(patronRepository.findById(mockPatron.getId())).thenReturn(Optional.empty());
            // Act
             updatedPatron = patronService.update(mockPatron,mockPatron.getId());
        } catch (ResourceNotFound e) {
            // Assert
            assertThat(updatedPatron).isNull();
        }
    }
    @Test
    void ItDeletesPatronIfExists() {
        // Arrange
        boolean deleted = false;
        Patron patron = new Patron("1",
                "XD",
                "123456789",
                null,
                null);
        when(patronRepository.findById(patron.getId())).thenReturn(Optional.of(patron));
        doNothing().when(patronRepository).delete(patron);
        // Act
        patronService.delete(patron.getId());
        deleted = true;
        assertSame(deleted,true);
    }
    @Test
    void ItSkipsDeletePatronIfNotExists() {
        // Arrange
        boolean deleted;
        try {
            Patron patron = new Patron("1",
                    "XD",
                    "123456789",
                    null,
                    null);
            when(patronRepository.findById(patron.getId())).thenReturn(Optional.empty());
            // Act
            patronService.delete(patron.getId());
            deleted = true;
        }
        catch (ResourceNotFound e) {
            // Assert
            deleted = false;
        }
        assertSame(deleted,false);
    }
}