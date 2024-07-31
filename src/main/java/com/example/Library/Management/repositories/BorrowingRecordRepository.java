package com.example.Library.Management.repositories;

import com.example.Library.Management.entities.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, String> {
    Optional<BorrowingRecord> findByBookIdAndReturningDate(String bookId, LocalDate date);
}
