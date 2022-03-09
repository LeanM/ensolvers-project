package com.example.ensolverProject.controllers;

import com.example.ensolverProject.models.Folder;
import com.example.ensolverProject.models.Item;
import com.example.ensolverProject.models.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ( path = "api/todolist" )
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<Item> getTodo() {
        return itemService.getItems();
    }

    @PostMapping (path = "todo")
    public void addTodo() {
        itemService.addTodo(new Todo("Dormir"));
    }

    @PostMapping (path = "folder")
    public void addFolder() {
        itemService.addFolder(new Folder("Mañana"));
    }

}
