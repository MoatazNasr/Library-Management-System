package com.example.Library.Management.services;

import com.example.Library.Management.entities.Patron;
import com.example.Library.Management.exceptions.ResourceNotFound;
import com.example.Library.Management.exceptions.ResourceAlreadyExists;
import com.example.Library.Management.repositories.PatronRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatronServiceImp implements PatronService{
    private final PatronRepository patronRepository;
    public Patron findById(String id) {
       Optional<Patron> patron = patronRepository.findById(id);
       if(patron.isEmpty()) throw new ResourceNotFound("Patron Doesn't Exist");
       return patron.get();
    }
    @Override
    public List<Patron> findAll() {
        return patronRepository.findAll();
    }
    @Override
    public Patron save(Patron patron) {
        if(patronRepository.existsByEmail(patron.getEmail()))
            throw new ResourceAlreadyExists("Email Already Exists");
        return patronRepository.save(patron);
    }
    @Override
    @Transactional
    public Patron update(Patron patron, String id) {
        this.findById(id);
        patron.setId(id);
        return patronRepository.save(patron);
    }
    @Override
    @Transactional
    public void delete(String id) {
        Patron patron = this.findById(id);
        patronRepository.delete(patron);
    }
}
