package com.example.ensolverProject.controllers;

import com.example.ensolverProject.models.Folder;
import com.example.ensolverProject.models.Item;
import com.example.ensolverProject.models.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {

    private final FolderRepository folderRepository;
    private final TodoRepository todoRepository;

    @Autowired
    public ItemService(FolderRepository folderRepository,
                       TodoRepository todoRepository) {
        this.folderRepository = folderRepository;
        this.todoRepository = todoRepository;
    }

    /**
     * Verifies that the id isnt invalid,
     * if its invalid throws an exception
     * @param idParentFolder
     */
    private void verifyIdAccess(Long idParentFolder) {
        if(idParentFolder <= 0)
            throw new IllegalStateException("Invalid folder");
    }

    /**
     * Returns a list of all items (folders and todo´s) that exists in
     * the database.
     * @return List<Item>
     */
    public List<Item> getItems() {
        List<Item> toReturn = new ArrayList<Item>();
        for(Folder f : folderRepository.findAll(Sort.by(Sort.Direction.ASC,"id")))
            toReturn.add(f);

        for(Todo t : todoRepository.findAll(Sort.by(Sort.Direction.ASC,"id")))
            toReturn.add(t);

        return toReturn;
    }

    /**
     * Returns a list of all folders that have
     * the main folder as parent.
     * @return List<Folder>
     */
    public List<Folder> getFoldersInMain() {
        List<Folder> toReturn;
        toReturn = folderRepository.findAllByIdParentFolder(0L,Sort.by(Sort.Direction.ASC,"id"));

        return toReturn;
    }

    /**
     * Returns a list of all todo's that have
     * the main folder as parent.
     * @return List<Todo>
     */
    public List<Todo> getTodosInMain() {
        return todoRepository.findAllByIdParentFolder(0L,Sort.by(Sort.Direction.ASC,"id"));
    }

    /**
     * Return all the folders that have a folder parent which his
     * id is "idParentFolder"
     * @param idParentFolder
     * @return List<Folder>
     */
    public List<Folder> getFoldersInFolder(Long idParentFolder) {
        verifyIdAccess(idParentFolder);
        if(folderRepository.existsById(idParentFolder))
            return folderRepository.findAllByIdParentFolder(idParentFolder, Sort.by(Sort.Direction.ASC,"id"));
        else throw new IllegalStateException("The folder doesnt exists");
    }

    /**
     * Return all the todo's that have a folder parent which his
     * id is "idParentFolder"
     * @param idParentFolder
     * @return List<Todo>
     */
    public List<Todo> getTodosInFolder(Long idParentFolder) {
        verifyIdAccess(idParentFolder);
        if(folderRepository.existsById(idParentFolder))
            return todoRepository.findAllByIdParentFolder(idParentFolder, Sort.by(Sort.Direction.ASC,"id"));
        else throw new IllegalStateException("The folder doesnt exists");
    }

    /**
     * Adds the parametrized folder with parent "idParentFolder"
     * to the database.
     * If the folder with id "idParentFolder" doesnt exists or if
     * the new folder name length is < 3 an exception will be thrown
     * @param idParentFolder
     * @param folder
     */
    public void addFolder(Long idParentFolder, Folder folder) {
        verifyIdAccess(idParentFolder);
        if(folderRepository.existsById(idParentFolder)){
            if(folder.getName().length() > 2) {
                Folder parentFolder = folderRepository.getById(idParentFolder);
                parentFolder.addItemToCollection(folder);
                folder.setParentFolder(parentFolder);
                this.folderRepository.save(folder);
            }
            else throw new IllegalStateException("The name of the new folder is invalid");
        }
        else throw new IllegalStateException("The parent folder doesnt exists");
    }

    /**
     * Adds the parametrized todo with parent "idParentFolder"
     * to the database.
     * If the folder with id "idParentFolder" doesnt exists or if
     * the new todo name length is < 3 an exception will be thrown
     * @param idParentFolder
     * @param todo
     */
    public void addTodo(Long idParentFolder, Todo todo) {
        verifyIdAccess(idParentFolder);
        if(folderRepository.existsById(idParentFolder)){
            if(todo.getName().length() > 2) {
                Folder parentFolder = folderRepository.getById(idParentFolder);
                parentFolder.addItemToCollection(todo);
                todo.setParentFolder(parentFolder);
                this.todoRepository.save(todo);
            }
            else throw new IllegalStateException("The name of the new todo is invalid");
        }
        else throw new IllegalStateException("The parent folder doesnt exists");
    }

    /**
     * Deletes the folder with id "folderId" and all his children from the database
     * If the folder doesnt exists, an exception will be thrown
     * @param idFolder
     */
    public void deleteFolder(Long idFolder) {
        verifyIdAccess(idFolder);
        if(folderRepository.existsById(idFolder)){
            for(Folder f : folderRepository.findAllByIdParentFolder(idFolder,Sort.by(Sort.Direction.ASC,"id")))
                deleteFolder(f.getId());
            for(Todo t : todoRepository.findAllByIdParentFolder(idFolder,Sort.by(Sort.Direction.ASC,"id")))
                todoRepository.delete(t);

            folderRepository.deleteById(idFolder);
        }
        else throw new IllegalStateException("La carpeta no existe.");
    }

    /**
     * Deletes the todo with id "todoId" from the database
     * If the todo doesnt exists, an exception will be thrown
     * @param idTodo
     */
    public void deleteTodo(Long idTodo) {
        verifyIdAccess(idTodo);
        if(todoRepository.existsById(idTodo))
            todoRepository.deleteById(idTodo);
        else
            throw new IllegalStateException("Invalid todo id");
    }

    /**
     * Updates the name of the folder with id "idFolder"
     * if the new name have less than 3 characthers an
     * exception will be thrown
     * if there isnt any folder with id "idFolder" an
     * exception will be thrown
     * @param idFolder
     * @param folder
     */
    public void updateFolder(Long idFolder, Folder folder) {
        verifyIdAccess(idFolder);
        if(folderRepository.existsById(idFolder)){
            if(folder.getName().length() > 2) {
                Folder oldFolder = folderRepository.getById(idFolder);
                oldFolder.setName(folder.getName());
            }
            else throw new IllegalStateException("Invalid folder's new name");
        }
        else throw new IllegalStateException("Invalid folder id");
    }

    /**
     * Updates the name of the todo with id "idTodo"
     * if the new name have less than 3 characthers an
     * exception will be thrown
     * if there isnt any todo with id "idTodo" an
     * exception will be thrown
     * @param idTodo
     * @param todo
     */
    public void updateTodo(Long idTodo, Todo todo) {
        verifyIdAccess(idTodo);
        if(todoRepository.existsById(idTodo)){
            if(todo.getName().length() > 2) {
                Todo oldTodo = todoRepository.getById(idTodo);
                oldTodo.setName(todo.getName());
                oldTodo.setChecked(todo.getChecked());
            }
            else throw new IllegalStateException("Invalid todo's new name");
        }
        else throw new IllegalStateException("Invalid todo id");
    }
}
