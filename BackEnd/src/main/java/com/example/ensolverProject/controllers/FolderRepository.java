package com.example.ensolverProject.controllers;

import com.example.ensolverProject.models.Folder;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FolderRepository extends JpaRepository<Folder,Long> {

    @Query("SELECT f FROM Folder f where f.parentFolder.id = ?1")
    List<Folder> findAllByIdParentFolder(Long idParentFolder, Sort sort);
}
