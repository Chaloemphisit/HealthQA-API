package com.seproject.healthqa.entitys;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author chaloemphisit
 */
@Entity
@Table(name = "qtype")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Qtype.findAll", query = "SELECT q FROM Qtype q")
    , @NamedQuery(name = "Qtype.findByIdQType", query = "SELECT q FROM Qtype q WHERE q.idQType = :idQType")
    , @NamedQuery(name = "Qtype.findByNameQType", query = "SELECT q FROM Qtype q WHERE q.nameQType = :nameQType")})
public class Qtype implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IdQType")
    private Integer idQType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "NameQType")
    private String nameQType;

    public Qtype() {
    }

    public Qtype(Integer idQType) {
        this.idQType = idQType;
    }

    public Qtype(Integer idQType, String nameQType) {
        this.idQType = idQType;
        this.nameQType = nameQType;
    }

    public Integer getIdQType() {
        return idQType;
    }

    public void setIdQType(Integer idQType) {
        this.idQType = idQType;
    }

    public String getNameQType() {
        return nameQType;
    }

    public void setNameQType(String nameQType) {
        this.nameQType = nameQType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idQType != null ? idQType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Qtype)) {
            return false;
        }
        Qtype other = (Qtype) object;
        if ((this.idQType == null && other.idQType != null) || (this.idQType != null && !this.idQType.equals(other.idQType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seproject.healthqa.entitys.Qtype[ idQType=" + idQType + " ]";
    }

}
