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

@CrossOrigin(origins = "*")
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
    public ResponseEntity<List<Folder>> getFoldersInMain() {
        ResponseEntity<List<Folder>> response;

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        List<Folder> toReturn = itemService.getFoldersInMain();

        response = new ResponseEntity<List<Folder>>(toReturn,headers,HttpStatus.OK);

        return response;
    }

    @GetMapping (path = "todo")
    public ResponseEntity<List<Todo>> getTodosInMain() {
        ResponseEntity<List<Todo>> response;

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        List<Todo> toReturn = itemService.getTodosInMain();

        response = new ResponseEntity<List<Todo>>(toReturn,headers,HttpStatus.OK);

        return response;
    }

    @GetMapping (path = "folder/{idParentFolder}")
    public ResponseEntity<List<Folder>> getFoldersInFolder(@PathVariable Long idParentFolder) {
        ResponseEntity<List<Folder>> response;

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        List<Folder> toReturn = itemService.getFoldersInFolder(idParentFolder);

        response = new ResponseEntity<List<Folder>>(toReturn,headers,HttpStatus.OK);

        return response;
    }

    @GetMapping (path = "todo/{idParentFolder}")
    public ResponseEntity<List<Todo>> getTodosInFolder(@PathVariable Long idParentFolder) {
        ResponseEntity<List<Todo>> response;

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        List<Todo> toReturn = itemService.getTodosInFolder(idParentFolder);

        response = new ResponseEntity<List<Todo>>(toReturn,headers,HttpStatus.OK);

        return response;
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

    @DeleteMapping (path = "folder/delete/{idFolder}")
    public void deleteFolder(@PathVariable Long idFolder) {
        itemService.deleteFolder(idFolder);
    }

    @DeleteMapping (path = "todo/delete/{idTodo}")
    public void deleteTodo(@PathVariable Long idTodo) {
        itemService.deleteTodo(idTodo);
    }

    @PutMapping (path = "folder/update/{idFolder}")
    public void updateFolder(@PathVariable Long idFolder,
                             @RequestBody Folder folder) {
        itemService.updateFolder(idFolder,folder);
    }

    @PutMapping (path = "todo/update/{idTodo}")
    public void updateTodo(@PathVariable Long idTodo,
                             @RequestBody Todo todo) {
        itemService.updateTodo(idTodo,todo);
    }

}
