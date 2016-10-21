/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compreingressos.controleacesso;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Intuiti 04
 */
@Entity
@Table(name = "CredencialGradeHoraria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CredencialGradeHoraria.findAll", query = "SELECT c FROM CredencialGradeHoraria c"),
    @NamedQuery(name = "CredencialGradeHoraria.findByCodigo", query = "SELECT c FROM CredencialGradeHoraria c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "CredencialGradeHoraria.findByGradeHoraria", query = "SELECT c FROM CredencialGradeHoraria c WHERE c.gradeHoraria = :gradeHoraria")})
public class CredencialGradeHoraria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @JoinColumn(name = "gradeHoraria")
    @ManyToOne(optional = false)
    private GradeHoraria gradeHoraria;
    @JoinColumn(name = "tipoCredencial", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private TipoCredencial tipoCredencial;

    public CredencialGradeHoraria() {
    }

    public CredencialGradeHoraria(Integer codigo) {
        this.codigo = codigo;
    }

    public CredencialGradeHoraria(Integer codigo, GradeHoraria gradeHoraria) {
        this.codigo = codigo;
        this.gradeHoraria = gradeHoraria;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public GradeHoraria getGradeHoraria() {
        return gradeHoraria;
    }

    public void setGradeHoraria(GradeHoraria gradeHoraria) {
        this.gradeHoraria = gradeHoraria;
    }

    public TipoCredencial getTipoCredencial() {
        return tipoCredencial;
    }

    public void setTipoCredencial(TipoCredencial tipoCredencial) {
        this.tipoCredencial = tipoCredencial;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CredencialGradeHoraria)) {
            return false;
        }
        CredencialGradeHoraria other = (CredencialGradeHoraria) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codigo.toString();
    }
    
}
