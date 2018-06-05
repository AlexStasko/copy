package by.yakunina.copy.storage.support;

public abstract class AbstractCrudDao<T> implements CrudDao<T> {
    @Override
    public int create(T entity) {
        return getMapper().create(entity);
    }

    @Override
    public T read(int id) {
        return getMapper().read(id);
    }

    @Override
    public void update(T entity) {
        getMapper().update(entity);
    }

    @Override
    public void delete(int id) {
        getMapper().delete(id);
    }

    protected abstract CrudDao<T> getMapper();
}
