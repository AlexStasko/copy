package by.yakunina.copy.model;

import by.yakunina.copy.model.support.EntityId;
import by.yakunina.copy.model.support.Identifiable;
import org.apache.ibatis.type.Alias;

@Alias("FileEntity")
public class FileEntity implements Identifiable {

    private final EntityId id;
    private final String name;
    private final byte[] data;

    public FileEntity(EntityId id, String name, byte[] data) {
        this.id = id;
        this.name = name;
        this.data = data;
    }

    @Override
    public EntityId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public byte[] getData() {
        return data;
    }
}
