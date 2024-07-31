package com.example.Library.Management.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Size(min = 3, max = 50, message = "Invalid Size, min is 3, max is 50")
    @NotBlank
    @Column(unique = true)
    private String title;
    @Size(min = 3, max = 20, message = "Invalid Size, min is 3, max is 20")
    @NotBlank
    private String author;
    @Size(min = 9, max = 19, message = "Invalid Size, min is 9, max is 19")
    @NotBlank
    private String ISBN;
    @Min(value = 1000)
    @Max(value = 2050)
    @Column(name = "publication_year")
    private short publicationYear;
    @OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE)
    private Set<BorrowingRecord> borrowingRecords;
}
