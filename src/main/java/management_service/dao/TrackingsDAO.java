package management_service.dao;

import java.util.List;

public interface TrackingsDAO<T> extends DAO<T> {
    T getById(long id);
    List<T> getUserTrackings(long user);
}