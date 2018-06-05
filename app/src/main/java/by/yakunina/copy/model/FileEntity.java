package by.yakunina.copy.model;

import by.yakunina.copy.model.support.Entity;

public class FileEntity extends Entity {

    private final String name;
    private final byte[] data;

    public FileEntity(int id, String name, byte[] data) {
        super(id);
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public byte[] getData() {
        return data;
    }
}
