package com.example.Library.Management.repositories;

import com.example.Library.Management.entities.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatronRepository extends JpaRepository<Patron,String> {
    boolean existsByEmail(String email);
}
