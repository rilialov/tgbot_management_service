package management_service.dao;

public interface TasksDAO<T> extends DAO<T> {
    T getById(Long id);
}
