package com.example.Library.Management.repositories;

import com.example.Library.Management.entities.Patron;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class PatronRepositoryTest {
    @Autowired
    private PatronRepository patronRepositoryTest;
    @Test
    void ItChecksIfPatronExistsByEmail() {
        String email = "moataz@gmail.com";
        Patron patron = new Patron("1",
                 email,
                "123456789",
                null,
                null);
        patronRepositoryTest.save(patron);
        Boolean expected = patronRepositoryTest.existsByEmail(email);
        assertThat(expected).isTrue();
    }
}