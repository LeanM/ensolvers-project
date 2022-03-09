package com.example.ensolverProject.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("2")
public class Todo extends Item {

    protected Boolean isChecked;

    public Todo(String name) {
        super(name);
        this.isChecked = false;
    }

    public Todo() {}

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentFolder=" + parentFolder +
                ", isChecked=" + isChecked +
                '}';
    }
}
