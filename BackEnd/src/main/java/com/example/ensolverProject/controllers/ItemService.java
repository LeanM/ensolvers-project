package com.example.ensolverProject.controllers;

import com.example.ensolverProject.models.Folder;
import com.example.ensolverProject.models.Item;
import com.example.ensolverProject.models.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    //Discriminator Type
    private final int folderType = 1, todoType = 2;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    public void addTodo(Todo todo) {
        this.itemRepository.save(todo);
    }

    public void addFolder(Folder folder) {
        this.itemRepository.save(folder);
    }
}
