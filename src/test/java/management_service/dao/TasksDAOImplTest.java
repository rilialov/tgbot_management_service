package management_service.dao;

import management_service.entity.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TasksDAOImplTest {

    private TasksDAOImpl tasksDAO;

    @BeforeEach
    void init() {
        tasksDAO = new TasksDAOImpl();
    }

    @Test
    void getById() {
        Task task = tasksDAO.getById(1L);

        assertNotNull(task);
    }

    @Test
    void getAll() {
        List<Task> tasks = tasksDAO.getAll();

        assertNotNull(tasks);
        assertTrue(tasks.size() > 0);
    }

    @Test
    void create() {
        Task task = new Task("Task Name", "Task Note");
        Task created = tasksDAO.create(task);

        tasksDAO.delete(created);
        assertNotNull(created);
    }

    @Test
    void update() {
        Task task = new Task("Task Name", "Task Note");
        Task created = tasksDAO.create(task);

        created.setTaskName("Changed Name");
        created.setTaskNote("Changed Note");
        tasksDAO.update(created);

        Task updated = tasksDAO.getById(created.getId());
        tasksDAO.delete(created);
        assertEquals("Changed Name", updated.getTaskName());
        assertEquals("Changed Note", updated.getTaskNote());
    }

    @Test
    void delete() {
        Task task = new Task("Task Name", "Task Note");
        Task created = tasksDAO.create(task);

        tasksDAO.delete(created);

        Task deleted = tasksDAO.getById(created.getId());
        assertNull(deleted);
    }
}