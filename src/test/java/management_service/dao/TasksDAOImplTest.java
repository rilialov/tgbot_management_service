package management_service.dao;

import management_service.entity.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class TasksDAOImplTest {

    private TasksDAOImpl tasksDAO;

    @BeforeEach
    void init() {
        tasksDAO = new TasksDAOImpl();
    }

    @Test
    void getById() {
        Task task = tasksDAO.getById(1L);

        Assertions.assertNotNull(task);
    }

    @Test
    void getAll() {
        List<Task> tasks = tasksDAO.getAll();

        Assertions.assertNotNull(tasks);
        Assertions.assertTrue(tasks.size() > 0);
    }

    @Test
    void create() {
        Task task = new Task("Task Name", "Task Note");
        Task created = tasksDAO.create(task);

        tasksDAO.delete(created);
        Assertions.assertNotNull(created);
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
        Assertions.assertEquals("Changed Name", updated.getTaskName());
        Assertions.assertEquals("Changed Note", updated.getTaskNote());
    }

    @Test
    void delete() {
        Task task = new Task("Task Name", "Task Note");
        Task created = tasksDAO.create(task);

        tasksDAO.delete(created);

        Task deleted = tasksDAO.getById(created.getId());
        Assertions.assertNull(deleted);
    }
}