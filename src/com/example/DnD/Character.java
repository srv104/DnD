package com.example.DnD;

/**
 * Created by mkorte on 1/30/14.
 */
public class Character {
    private long id;
    private String name;
    private String cClass;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getcClass() {
        return cClass;
    }

    public void setcClass(String cClass) {
        this.cClass = cClass;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return getName();
    }
}
