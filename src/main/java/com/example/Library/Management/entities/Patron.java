package com.example.Library.Management.entities;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
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
public class Patron {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Size(min = 10, max = 20, message = "Invalid Size, min is 10, max is 20")
    @Email(regexp = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,}")
    @Column(unique = true)
    private String email;
    @Size(min = 5, max = 30, message = "Invalid Size, min is 5, max is 30")
    @NotBlank
    private String password;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "contact_info_id", referencedColumnName = "id")
    @Valid
    private ContactInformation contactInformation;
    @OneToMany(mappedBy = "patron", cascade = CascadeType.REMOVE)
    private Set<BorrowingRecord> borrowingRecords;
}
