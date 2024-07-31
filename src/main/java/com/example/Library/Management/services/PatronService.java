package com.example.Library.Management.services;

import com.example.Library.Management.entities.Patron;

import java.util.List;

public interface PatronService {
    Patron save(Patron patron);
    Patron update(Patron patron,String id);
    Patron findById(String id);
    List<Patron> findAll();
    void delete(String id);

}
