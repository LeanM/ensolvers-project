package com.example.ensolverProject.models;

import com.example.ensolverProject.models.Folder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Entity
@Table (name = "items")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Item {

    @SequenceGenerator(
            name="item_sequence",
            sequenceName = "item_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "item_sequence"
    )

    @Id
    @Column(name = "id" , nullable = false , unique = true)
    protected Long id;
    protected String name;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_parent_folder")
    @JsonInclude
    protected Folder parentFolder;

    public Item(String name) {
        this.name = name;
    }

    public Item(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public Folder getParentFolder() {
        return parentFolder;
    }

    @JsonInclude
    public Long getIdParentFolder() {
        return parentFolder.getId();
    }

    public void setParentFolder(Folder parentFolder) {
        this.parentFolder = parentFolder;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentFolder=" + parentFolder +
                '}';
    }
}
