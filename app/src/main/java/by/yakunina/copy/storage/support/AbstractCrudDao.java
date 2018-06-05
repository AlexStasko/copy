package by.yakunina.copy.storage.support;

import by.yakunina.copy.model.support.EntityId;

public abstract class AbstractCrudDao<T> implements CrudDao<T> {
    @Override
    public EntityId create(T entity) {
        return getMapper().create(entity);
    }

    @Override
    public T read(EntityId id) {
        return getMapper().read(id);
    }

    @Override
    public void update(T entity) {
        getMapper().update(entity);
    }

    @Override
    public void delete(EntityId id) {
        getMapper().delete(id);
    }

    protected abstract CrudDao<T> getMapper();
}
