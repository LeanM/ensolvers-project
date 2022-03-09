package com.example.ensolverProject.controllers;

import com.example.ensolverProject.models.Todo;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Long> {

    @Query("SELECT t FROM Todo t where t.parentFolder.id = ?1")
    List<Todo> findAllByIdParentFolder(Long idParentFolder, Sort sort);
}
