package com.example.Library.Management.services;


import com.example.Library.Management.entities.BorrowingRecord;

public interface BorrowingRecordService {
    BorrowingRecord save(String patronId, String bookId);
    BorrowingRecord update(String patronId, String bookId);
}
