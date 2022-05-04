package management_service.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import management_service.dao.TrackingsDAOImpl;
import management_service.entity.Tracking;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TrackingsServlet", value = "/trackings/*")
public class TrackingsServlet extends HttpServlet {

    private final TrackingsDAOImpl trackingsDAO = new TrackingsDAOImpl();
    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String uri = req.getRequestURI();
        String json;

        if (uri.substring("/trackings/".length()).equals("")) {
            json = GSON.toJson(trackingsDAO.getAll());
        } else {
            long id = Long.parseLong(uri.substring("/trackings/".length()));
            json = GSON.toJson(trackingsDAO.getById(id));
        }

        resp.setStatus(200);
        resp.setHeader("Content-Type", "application/json");
        resp.getOutputStream().println(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String json = ControllersUtil.readInputStream(req.getInputStream());
        Tracking tracking = GSON.fromJson(json, Tracking.class);

        Tracking created = trackingsDAO.create(tracking);

        resp.setStatus(201);
        resp.setHeader("Content-Type", "application/json");
        resp.getOutputStream().println(GSON.toJson(created));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String uri = req.getRequestURI();
        long id;

        if (uri.substring("/trackings/".length()).equals("")) {
            resp.setStatus(422);
            resp.getOutputStream().println("You cannot update Tracking without id!");
        } else {
            id = Long.parseLong(uri.substring("/trackings/".length()));
            Tracking tracking = trackingsDAO.getById(id);

            if (tracking == null) {
                resp.setStatus(422);
                resp.getOutputStream().println("You cannot update Tracking with id " + id + " because it doesn't exists!");
            } else {
                String json = ControllersUtil.readInputStream(req.getInputStream());
                Tracking updated = GSON.fromJson(json, Tracking.class);
                tracking.setTrackingNote(updated.getTrackingNote());
                tracking.setEndTime(updated.getEndTime());
                tracking.setTask(updated.getTask());
                trackingsDAO.update(tracking);

                resp.setStatus(200);
                resp.setHeader("Content-Type", "application/json");
                resp.getOutputStream().println(GSON.toJson(tracking));
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String uri = req.getRequestURI();
        long id;

        if (uri.substring("/trackings/".length()).equals("")) {
            resp.setStatus(422);
            resp.getOutputStream().println("You cannot delete Tracking without id!");
        } else {
            id = Long.parseLong(uri.substring("/trackings/".length()));
            Tracking tracking = trackingsDAO.getById(id);

            if (tracking == null) {
                resp.setStatus(422);
                resp.getOutputStream().println("You cannot delete Tracking with id " + id + " because it doesn't exists!");
            } else {
                trackingsDAO.delete(tracking);
                String json = GSON.toJson(tracking);

                resp.setStatus(200);
                resp.setHeader("Content-Type", "application/json");
                resp.getOutputStream().println(json);
            }
        }
    }

}
