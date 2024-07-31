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

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContactInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Size(min = 5, max = 20, message = "Invalid Size, min is 5, max is 20")
    @NotBlank
    private String name;
    @Column(name = "phone_number")
    @Size(min = 11, max = 11, message = "Invalid Size, size must be 11")
    @NotBlank
    private String phoneNumber;
    @Min(value = 8, message = "Age must be 8 or higher")
    @Max(value = 100, message = "Age must be 100 or smaller")
    private short age;
}
