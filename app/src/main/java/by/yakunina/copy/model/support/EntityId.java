package by.yakunina.copy.model.support;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.ibatis.type.Alias;

import java.util.UUID;

@Alias("EntityId")
public class EntityId {

    private final String id;

    private EntityId() {
        this.id = StringUtils.EMPTY;
    }

    public EntityId(UUID id) {
        this.id = id.toString();
    }

    public EntityId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .toString();
    }
}
