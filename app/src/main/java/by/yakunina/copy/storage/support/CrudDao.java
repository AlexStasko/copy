package by.yakunina.copy.storage.support;

public interface CrudDao<T> {

    int create(T entity);

    T read(int id);

    void update(T entity);

    void delete(int id);
}
