package management_service.dao;

import management_service.entity.Tracking;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrackingsDAOImplTest {

    private TrackingsDAOImpl trackingsDAO;
    private TasksDAOImpl tasksDAO;

    @BeforeEach
    void init() {
        trackingsDAO = new TrackingsDAOImpl();
    }

    @Test
    void getById() {
        Tracking tracking = trackingsDAO.getById(1L);

        assertNotNull(tracking);
    }

    @Test
    void getAll() {
        List<Tracking> trackings = trackingsDAO.getAll();

        assertNotNull(trackings);
        assertTrue(trackings.size() > 0);
    }

    @Test
    void getUserTrackings() {
        List<Tracking> trackings = trackingsDAO.getUserTrackings(1L);

        assertNotNull(trackings);
        assertTrue(trackings.size() > 0);
    }

    @Test
    void create() {
        tasksDAO = new TasksDAOImpl();
        Tracking tracking = new Tracking("Track Note", tasksDAO.getById(1L), 1L);
        Tracking created = trackingsDAO.create(tracking);

        trackingsDAO.delete(created);
        assertNotNull(created);
    }

    @Test
    void update() {
        tasksDAO = new TasksDAOImpl();
        Tracking tracking = new Tracking("Track Note", tasksDAO.getById(1L), 1L);
        Tracking created = trackingsDAO.create(tracking);

        created.setEndTime(LocalDateTime.of(2022, 5, 23, 16, 10, 30));
        created.setTrackingNote("Changed Note");
        trackingsDAO.update(created);

        Tracking updated = trackingsDAO.getById(created.getId());
        trackingsDAO.delete(created);
        assertEquals(LocalDateTime.of(2022, 5, 23, 16, 10, 30), updated.getEndTime());
        assertEquals("Changed Note", updated.getTrackingNote());
    }

    @Test
    void delete() {
        tasksDAO = new TasksDAOImpl();
        Tracking tracking = new Tracking("Track Note", tasksDAO.getById(1L), 1L);
        Tracking created = trackingsDAO.create(tracking);

        trackingsDAO.delete(created);

        Tracking deleted = trackingsDAO.getById(created.getId());
        assertNull(deleted);
    }
}