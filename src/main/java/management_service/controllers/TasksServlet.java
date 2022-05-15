package management_service.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import management_service.dao.TasksDAOImpl;
import management_service.entity.Task;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TasksServlet", value = "/tasks/*")
public class TasksServlet extends HttpServlet {

    private final TasksDAOImpl tasksDAO = new TasksDAOImpl();
    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String uri = req.getRequestURI().substring(req.getContextPath().length());
        String json;

        if (uri.substring("/tasks/".length()).equals("")) {
            json = GSON.toJson(tasksDAO.getAll());
        } else {
            long id = Long.parseLong(uri.substring("/tasks/".length()));
            json = GSON.toJson(tasksDAO.getById(id));
        }

        resp.setStatus(200);
        resp.setHeader("Content-Type", "application/json");
        resp.getOutputStream().println(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String json = ControllersUtil.readInputStream(req.getInputStream());
        Task task = GSON.fromJson(json, Task.class);

        Task created = tasksDAO.create(task);

        resp.setStatus(201);
        resp.setHeader("Content-Type", "application/json");
        resp.getOutputStream().println(GSON.toJson(created));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String uri = req.getRequestURI().substring(req.getContextPath().length());
        long id;

        if (uri.substring("/tasks/".length()).equals("")) {
            resp.setStatus(422);
            resp.getOutputStream().println("You cannot update Task without id!");
        } else {
            id = Long.parseLong(uri.substring("/tasks/".length()));
            Task task = tasksDAO.getById(id);

            if (task == null) {
                resp.setStatus(422);
                resp.getOutputStream().println("You cannot update Task with id " + id + " because it doesn't exists!");
            } else {
                String json = ControllersUtil.readInputStream(req.getInputStream());
                Task updated = GSON.fromJson(json, Task.class);
                task.setTaskName(updated.getTaskName());
                task.setTaskNote(updated.getTaskNote());
                tasksDAO.update(task);

                resp.setStatus(200);
                resp.setHeader("Content-Type", "application/json");
                resp.getOutputStream().println(GSON.toJson(task));
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String uri = req.getRequestURI().substring(req.getContextPath().length());
        long id;

        if (uri.substring("/tasks/".length()).equals("")) {
            resp.setStatus(422);
            resp.getOutputStream().println("You cannot delete Task without id!");
        } else {
            id = Long.parseLong(uri.substring("/tasks/".length()));
            Task task = tasksDAO.getById(id);

            if (task == null) {
                resp.setStatus(422);
                resp.getOutputStream().println("You cannot delete Task with id " + id + " because it doesn't exists!");
            } else {
                tasksDAO.delete(task);
                String json = GSON.toJson(task);

                resp.setStatus(200);
                resp.setHeader("Content-Type", "application/json");
                resp.getOutputStream().println(json);
            }
        }
    }

}
