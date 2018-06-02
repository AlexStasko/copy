package by.yakunina.copy.model.support;

import java.io.Serializable;

public abstract class Entity implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;

    public Entity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
