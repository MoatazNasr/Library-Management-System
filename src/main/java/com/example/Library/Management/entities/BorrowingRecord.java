package com.example.Library.Management.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class BorrowingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    @JoinColumn(name = "patron_id")
    private Patron patron;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    @Column(name = "borrowing_date")
    private LocalDate borrowingDate;
    @Column(name = "returning_date")
    private LocalDate returningDate;
}
