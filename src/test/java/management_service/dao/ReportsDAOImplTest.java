package management_service.dao;

import management_service.entity.Report;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReportsDAOImplTest {

    private ReportsDAOImpl reportsDAO;

    @BeforeEach
    void init() {
        reportsDAO = new ReportsDAOImpl();
    }

    @Test
    void getById() {
        Report report = reportsDAO.getById(1L);

        assertNotNull(report);
    }

    @Test
    void getAll() {
        List<Report> reports = reportsDAO.getAll();

        assertNotNull(reports);
        assertTrue(reports.size() > 0);
    }

    @Test
    void create() {
        Report report = new Report(LocalDate.of(2022, 5, 17), 216000L, 100L);
        Report created = reportsDAO.create(report);

        reportsDAO.delete(created);
        assertNotNull(created);
    }

    @Test
    void update() {
        Report report = new Report(LocalDate.of(2022, 5, 7), 216000L, 1L);
        Report created = reportsDAO.create(report);

        created.setDate(LocalDate.of(2022, 7, 18));
        created.setFullTime(864000L);
        reportsDAO.update(created);

        Report updated = reportsDAO.getById(created.getId());
        reportsDAO.delete(created);
        assertEquals(LocalDate.of(2022, 7, 18), updated.getDate());
        assertEquals(864000L, updated.getFullTime());
    }

    @Test
    void delete() {
        Report report = new Report(LocalDate.of(2022, 5, 7), 216000L, 1L);
        Report created = reportsDAO.create(report);

        reportsDAO.delete(created);

        Report deleted = reportsDAO.getById(created.getId());
        assertNull(deleted);
    }
}