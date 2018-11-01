/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seproject.healthqa.domain.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 59050320
 */
@Entity
@Table(name = "configure")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Configure.findAll", query = "SELECT c FROM Configure c")
    , @NamedQuery(name = "Configure.findByConfigureId", query = "SELECT c FROM Configure c WHERE c.configureId = :configureId")
    , @NamedQuery(name = "Configure.findByConfigureValue", query = "SELECT c FROM Configure c WHERE c.configureValue = :configureValue")
    , @NamedQuery(name = "Configure.findByConfigureCode", query = "SELECT c FROM Configure c WHERE c.configureCode = :configureCode")
    , @NamedQuery(name = "Configure.findByConfigureDetail", query = "SELECT c FROM Configure c WHERE c.configureDetail = :configureDetail")
    , @NamedQuery(name = "Configure.findByCreatedDate", query = "SELECT c FROM Configure c WHERE c.createdDate = :createdDate")
    , @NamedQuery(name = "Configure.findByUpdateDate", query = "SELECT c FROM Configure c WHERE c.updateDate = :updateDate")
    , @NamedQuery(name = "Configure.findByIsDeleted", query = "SELECT c FROM Configure c WHERE c.isDeleted = :isDeleted")})
public class Configure implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CONFIGURE_ID")
    private Integer configureId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CONFIGURE_VALUE")
    private int configureValue;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "CONFIGURE_CODE")
    private String configureCode;
    @Size(max = 1000)
    @Column(name = "CONFIGURE_DETAIL")
    private String configureDetail;
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    @Column(name = "IS_DELETED")
    private Character isDeleted;

    public Configure() {
    }

    public Configure(Integer configureId) {
        this.configureId = configureId;
    }

    public Configure(Integer configureId, int configureValue, String configureCode) {
        this.configureId = configureId;
        this.configureValue = configureValue;
        this.configureCode = configureCode;
    }

    public Integer getConfigureId() {
        return configureId;
    }

    public void setConfigureId(Integer configureId) {
        this.configureId = configureId;
    }

    public int getConfigureValue() {
        return configureValue;
    }

    public void setConfigureValue(int configureValue) {
        this.configureValue = configureValue;
    }

    public String getConfigureCode() {
        return configureCode;
    }

    public void setConfigureCode(String configureCode) {
        this.configureCode = configureCode;
    }

    public String getConfigureDetail() {
        return configureDetail;
    }

    public void setConfigureDetail(String configureDetail) {
        this.configureDetail = configureDetail;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Character getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Character isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (configureId != null ? configureId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Configure)) {
            return false;
        }
        Configure other = (Configure) object;
        if ((this.configureId == null && other.configureId != null) || (this.configureId != null && !this.configureId.equals(other.configureId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seproject.healthqa.domain.entity.Configure[ configureId=" + configureId + " ]";
    }
    
}
