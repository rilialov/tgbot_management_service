package management_service.dao;

public interface ReportsDAO<T> extends DAO<T> {
    T getById(long id);
}
