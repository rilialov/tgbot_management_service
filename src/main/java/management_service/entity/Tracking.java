package management_service.entity;

import users_service.UserDTO;

import java.time.LocalDateTime;

public class Tracking {

    private long id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String trackingNote;

    private Task task;

    private UserDTO user;
}
