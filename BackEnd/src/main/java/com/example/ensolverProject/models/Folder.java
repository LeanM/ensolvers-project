package com.example.ensolverProject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "folders")
public class Folder extends Item {

    @OneToMany (fetch = FetchType.LAZY,
                mappedBy = "parentFolder"
    )
    protected List<Item> itemCollection;

    public Folder(String name) {
        super(name);
        this.itemCollection = new ArrayList<Item>();
    }

    public Folder() {}

    @JsonIgnore
    public List<Item> getItemCollection() {
        return itemCollection;
    }

    public void setItemCollection(List<Item> itemCollection) {
        this.itemCollection = itemCollection;
    }

    public void addItemToCollection(Item itemToAdd) {
        itemCollection.add(itemToAdd);
    }

    @Override
    public String toString() {
        return "Folder{" +
                "itemCollection=" + itemCollection +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", parentFolder=" + parentFolder +
                '}';
    }
}
