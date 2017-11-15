package hibernate;

import javax.persistence.*;

@Entity
@Table(name = "medical_unit_specialty", schema = "public", catalog = "medical1")
@IdClass(MedicalUnitSpecialtyEntityPK.class)
public class MedicalUnitSpecialtyEntity {
    private int medicalUnitId;
    private int specialtyId;

    @Id
    @Column(name = "medical_unit_id")
    public int getMedicalUnitId() {
        return medicalUnitId;
    }

    public void setMedicalUnitId(int medicalUnitId) {
        this.medicalUnitId = medicalUnitId;
    }

    @Id
    @Column(name = "specialty_id")
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

        MedicalUnitSpecialtyEntity that = (MedicalUnitSpecialtyEntity) o;

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
