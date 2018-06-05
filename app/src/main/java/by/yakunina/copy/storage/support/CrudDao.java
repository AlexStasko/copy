package by.yakunina.copy.storage.support;

import by.yakunina.copy.model.support.EntityId;

public interface CrudDao<T> {

    EntityId create(T entity);

    T read(EntityId id);

    void update(T entity);

    void delete(EntityId id);
}
