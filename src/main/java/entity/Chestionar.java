package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Chestionar {
    private int id;
    private String intrebare;
    private String v1;
    private String v2;
    private String v3;
    private String v4;
    private String corect;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "INTREBARE")
    public String getIntrebare() {
        return intrebare;
    }

    public void setIntrebare(String intrebare) {
        this.intrebare = intrebare;
    }

    @Basic
    @Column(name = "V1")
    public String getV1() {
        return v1;
    }

    public void setV1(String v1) {
        this.v1 = v1;
    }

    @Basic
    @Column(name = "V2")
    public String getV2() {
        return v2;
    }

    public void setV2(String v2) {
        this.v2 = v2;
    }

    @Basic
    @Column(name = "V3")
    public String getV3() {
        return v3;
    }

    public void setV3(String v3) {
        this.v3 = v3;
    }

    @Basic
    @Column(name = "V4")
    public String getV4() {
        return v4;
    }

    public void setV4(String v4) {
        this.v4 = v4;
    }

    @Basic
    @Column(name = "CORECT")
    public String getCorect() {
        return corect;
    }

    public void setCorect(String corect) {
        this.corect = corect;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chestionar that = (Chestionar) o;
        return id == that.id &&
                Objects.equals(intrebare, that.intrebare) &&
                Objects.equals(v1, that.v1) &&
                Objects.equals(v2, that.v2) &&
                Objects.equals(v3, that.v3) &&
                Objects.equals(v4, that.v4) &&
                Objects.equals(corect, that.corect);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, intrebare, v1, v2, v3, v4, corect);
    }

    @Override
    public String toString() {
        return intrebare + "#" + v1 + "#" + v2 + "#" + v3 + "#" + v4 + "#" + corect;
    }
}
