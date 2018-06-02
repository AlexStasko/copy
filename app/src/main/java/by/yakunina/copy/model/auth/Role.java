package by.yakunina.copy.model.auth;

import by.yakunina.copy.model.support.Entity;

import java.io.Serializable;

/**
 * Role entity.
 */
public class Role extends Entity implements Serializable {

    private static final long serialVersionUID = 1L;
    private final String name;

    public Role(int id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
