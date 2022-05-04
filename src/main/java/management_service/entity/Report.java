package management_service.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "reports")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "full_time", nullable = false)
    private long fullTime;

    @Column(name = "\"user\"")
    private String user;

    public Report() {
    }

    public Report(LocalDate date, String user) {
        this.date = date;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public long getFullTime() {
        return fullTime;
    }

    public void setFullTime(long fullTime) {
        this.fullTime = fullTime;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return id == report.id && fullTime == report.fullTime && date.equals(report.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, fullTime);
    }
}
