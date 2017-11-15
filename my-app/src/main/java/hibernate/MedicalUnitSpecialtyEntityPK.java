package hibernate;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class MedicalUnitSpecialtyEntityPK implements Serializable {
    private int medicalUnitId;
    private int specialtyId;

    @Column(name = "medical_unit_id")
    @Id
    public int getMedicalUnitId() {
        return medicalUnitId;
    }

    public void setMedicalUnitId(int medicalUnitId) {
        this.medicalUnitId = medicalUnitId;
    }

    @Column(name = "specialty_id")
    @Id
    public int getSpecialtyId() {
        return specialtyId;
    }

    public void setSpecialtyId(int specialtyId) {
        this.specialtyId = specialtyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MedicalUnitSpecialtyEntityPK that = (MedicalUnitSpecialtyEntityPK) o;

        if (medicalUnitId != that.medicalUnitId) return false;
        if (specialtyId != that.specialtyId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = medicalUnitId;
        result = 31 * result + specialtyId;
        return result;
    }
}
