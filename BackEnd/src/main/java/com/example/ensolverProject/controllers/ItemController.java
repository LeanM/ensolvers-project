package com.example.ensolverProject.controllers;

import com.example.ensolverProject.models.Item;
import com.example.ensolverProject.models.Todo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ( path = "api/todolist" )
public class ItemController {

    @GetMapping
    public Item getTodo() {
        return new Todo("Dormir");
    }
    
}
