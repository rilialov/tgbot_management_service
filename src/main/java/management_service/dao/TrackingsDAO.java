package management_service.dao;

public interface TrackingsDAO<T> extends DAO<T> {
    T getById(Long id);
}