package by.yakunina.copy.model.auth;

import by.yakunina.copy.model.support.EntityId;
import by.yakunina.copy.model.support.Identifiable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * Role entity.
 */
@Alias("Role")
public class Role implements Identifiable, Serializable {

    private static final long serialVersionUID = 1L;

    private EntityId id;
    private String name;

    public Role() {
        this.id = null;
        this.name = null;
    }

    public Role(EntityId id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("name", name)
                .toString();
    }
}
