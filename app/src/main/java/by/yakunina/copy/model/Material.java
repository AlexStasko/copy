package by.yakunina.copy.model;

import by.yakunina.copy.model.support.EntityId;
import by.yakunina.copy.model.support.Identifiable;

public class Material implements Identifiable {

    private EntityId id;
    private String name;

    public Material() {
        this.id = null;
        this.name = null;
    }

    @Override
    public EntityId getId() {
        return id;
    }

    public void setId(EntityId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
