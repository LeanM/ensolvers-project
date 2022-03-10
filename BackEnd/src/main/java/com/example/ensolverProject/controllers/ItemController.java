package com.example.ensolverProject.controllers;

import com.example.ensolverProject.models.Folder;
import com.example.ensolverProject.models.Item;
import com.example.ensolverProject.models.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin ("*")
@RestController
@RequestMapping ( path = "api/todolist" )
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAll() {
        ResponseEntity<List<Item>> response;

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        List<Item> toReturn = itemService.getItems();

        response = new ResponseEntity<List<Item>>(toReturn,headers, HttpStatus.OK);

        return response;
    }

    @GetMapping (path = "folder")
    public List<Folder> getFoldersInMain() {
        return itemService.getFoldersInMain();
    }

    @GetMapping (path = "todo")
    public List<Todo> getTodosInMain() {
        return itemService.getTodosInMain();
    }

    @GetMapping (path = "folder/{idParentFolder}")
    public List<Folder> getFoldersInFolder(@PathVariable Long idParentFolder) {
        return itemService.getFoldersInFolder(idParentFolder);
    }

    @GetMapping (path = "todo/{idParentFolder}")
    public List<Todo> getTodosInFolder(@PathVariable Long idParentFolder) {
        return itemService.getTodosInFolder(idParentFolder);
    }

    @PostMapping (path = "folder")
    public void addFolder(@RequestParam (required = true) Long idParentFolder,
                          @RequestBody Folder folder) {
        itemService.addFolder(idParentFolder,folder);
    }

    @PostMapping (path = "todo")
    public void addTodo(@RequestParam (required = true) Long idParentFolder,
                        @RequestBody Todo todo) {
        itemService.addTodo(idParentFolder,todo);
    }

    @DeleteMapping (path = "folder/delete")
    public void deleteFolder(@RequestParam (required = true) Long idFolder) {
        itemService.deleteFolder(idFolder);
    }

    @DeleteMapping (path = "todo/delete")
    public void deleteTodo(@RequestParam (required = true) Long idTodo) {
        itemService.deleteTodo(idTodo);
    }

    @PutMapping (path = "folder/update")
    public void updateFolder(@RequestParam (required = true) Long idFolder,
                             @RequestBody Folder folder) {
        itemService.updateFolder(idFolder,folder);
    }

    @PutMapping (path = "todo/update")
    public void updateTodo(@RequestParam (required = true) Long idTodo,
                             @RequestBody Todo todo) {
        itemService.updateTodo(idTodo,todo);
    }

}
