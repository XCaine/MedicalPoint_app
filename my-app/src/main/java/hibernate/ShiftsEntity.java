package hibernate;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

@Entity
@Table(name = "shifts", schema = "public", catalog = "medical1")
public class ShiftsEntity {
    private int id;
    private Timestamp start;
    private BigInteger length;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "start")
    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    @Basic
    @Column(name = "length")
    public BigInteger getLength() {
        return length;
    }

    public void setLength(BigInteger length) {
        this.length = length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShiftsEntity that = (ShiftsEntity) o;

        if (id != that.id) return false;
        if (start != null ? !start.equals(that.start) : that.start != null) return false;
        if (length != null ? !length.equals(that.length) : that.length != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (start != null ? start.hashCode() : 0);
        result = 31 * result + (length != null ? length.hashCode() : 0);
        return result;
    }
}
