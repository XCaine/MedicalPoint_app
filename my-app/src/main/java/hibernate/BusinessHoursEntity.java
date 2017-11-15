package hibernate;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "business_hours", schema = "public", catalog = "medical1")
public class BusinessHoursEntity {
    private int id;
    private Time open;
    private Time close;
    private Timestamp lastUptade;
    private int dayOfWeek;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "open")
    public Time getOpen() {
        return open;
    }

    public void setOpen(Time open) {
        this.open = open;
    }

    @Basic
    @Column(name = "close")
    public Time getClose() {
        return close;
    }

    public void setClose(Time close) {
        this.close = close;
    }

    @Basic
    @Column(name = "last_uptade")
    public Timestamp getLastUptade() {
        return lastUptade;
    }

    public void setLastUptade(Timestamp lastUptade) {
        this.lastUptade = lastUptade;
    }

    @Basic
    @Column(name = "day_of_week")
    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BusinessHoursEntity that = (BusinessHoursEntity) o;

        if (id != that.id) return false;
        if (dayOfWeek != that.dayOfWeek) return false;
        if (open != null ? !open.equals(that.open) : that.open != null) return false;
        if (close != null ? !close.equals(that.close) : that.close != null) return false;
        if (lastUptade != null ? !lastUptade.equals(that.lastUptade) : that.lastUptade != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (open != null ? open.hashCode() : 0);
        result = 31 * result + (close != null ? close.hashCode() : 0);
        result = 31 * result + (lastUptade != null ? lastUptade.hashCode() : 0);
        result = 31 * result + dayOfWeek;
        return result;
    }
}
