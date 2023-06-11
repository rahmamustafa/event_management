package gov.iti.evento.controllers;

import gov.iti.evento.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventController {
    @Autowired
    CategoryRepository categoryRepository;

    public void m(){
    }
}
