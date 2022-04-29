package management_service.entity;

import users_service.UserDTO;

import java.time.LocalDate;

public class Report {

    private long id;

    private LocalDate date;

    private long fullTime;

    private UserDTO user;
}
