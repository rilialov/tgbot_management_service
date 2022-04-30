package management_service.dao;

import java.util.List;

public interface DAO<T> {
    List<T> getAll();

    T create(T t);

    void update(T t);

    void delete(T t);
}
