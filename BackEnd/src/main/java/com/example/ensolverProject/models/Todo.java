package com.example.ensolverProject.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table (name = "todos")
public class Todo extends Item {

    protected Boolean checked;

    public Todo(String name) {
        super(name);
        this.checked = false;
    }

    public Todo() {}

    public Boolean getChecked() {
        return this.checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentFolder=" + parentFolder +
                ", isChecked=" + checked +
                '}';
    }
}
