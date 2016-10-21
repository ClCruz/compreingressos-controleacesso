/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compreingressos.controleacesso;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Intuiti 04
 */
@Entity
@Table(name = "GradeHoraria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GradeHoraria.findAll", query = "SELECT g FROM GradeHoraria g"),
    @NamedQuery(name = "GradeHoraria.findByCodigo", query = "SELECT g FROM GradeHoraria g WHERE g.codigo = :codigo"),
    @NamedQuery(name = "GradeHoraria.findByDescricao", query = "SELECT g FROM GradeHoraria g WHERE g.descricao = :descricao"),
    @NamedQuery(name = "GradeHoraria.findByDomingo", query = "SELECT g FROM GradeHoraria g WHERE g.domingo = :domingo"),
    @NamedQuery(name = "GradeHoraria.findBySegunda", query = "SELECT g FROM GradeHoraria g WHERE g.segunda = :segunda"),
    @NamedQuery(name = "GradeHoraria.findByTerca", query = "SELECT g FROM GradeHoraria g WHERE g.terca = :terca"),
    @NamedQuery(name = "GradeHoraria.findByQiuarta", query = "SELECT g FROM GradeHoraria g WHERE g.qiuarta = :qiuarta"),
    @NamedQuery(name = "GradeHoraria.findByQuinta", query = "SELECT g FROM GradeHoraria g WHERE g.quinta = :quinta"),
    @NamedQuery(name = "GradeHoraria.findBySexta", query = "SELECT g FROM GradeHoraria g WHERE g.sexta = :sexta"),
    @NamedQuery(name = "GradeHoraria.findBySabado", query = "SELECT g FROM GradeHoraria g WHERE g.sabado = :sabado"),
    @NamedQuery(name = "GradeHoraria.findByHoraMinima", query = "SELECT g FROM GradeHoraria g WHERE g.horaMinima = :horaMinima"),
    @NamedQuery(name = "GradeHoraria.findByHoraMaxima", query = "SELECT g FROM GradeHoraria g WHERE g.horaMaxima = :horaMaxima")})
public class GradeHoraria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "domingo")
    private boolean domingo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "segunda")
    private boolean segunda;
    @Basic(optional = false)
    @NotNull
    @Column(name = "terca")
    private boolean terca;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qiuarta")
    private boolean qiuarta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quinta")
    private boolean quinta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sexta")
    private boolean sexta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sabado")
    private boolean sabado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "horaMinima")
    private String horaMinima;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "horaMaxima")
    private String horaMaxima;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gradeHoraria")
    private Collection<CredencialGradeHoraria> credencialGradeHorariaCollection;
    
    
    public GradeHoraria() {
    }

    public GradeHoraria(Integer codigo) {
        this.codigo = codigo;
    }

    public GradeHoraria(Integer codigo, String descricao, boolean domingo, boolean segunda, boolean terca, boolean qiuarta, boolean quinta, boolean sexta, boolean sabado, String horaMinima, String horaMaxima) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.domingo = domingo;
        this.segunda = segunda;
        this.terca = terca;
        this.qiuarta = qiuarta;
        this.quinta = quinta;
        this.sexta = sexta;
        this.sabado = sabado;
        this.horaMinima = horaMinima;
        this.horaMaxima = horaMaxima;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean getDomingo() {
        return domingo;
    }

    public void setDomingo(boolean domingo) {
        this.domingo = domingo;
    }

    public boolean getSegunda() {
        return segunda;
    }

    public void setSegunda(boolean segunda) {
        this.segunda = segunda;
    }

    public boolean getTerca() {
        return terca;
    }

    public void setTerca(boolean terca) {
        this.terca = terca;
    }

    public boolean getQiuarta() {
        return qiuarta;
    }

    public void setQiuarta(boolean qiuarta) {
        this.qiuarta = qiuarta;
    }

    public boolean getQuinta() {
        return quinta;
    }

    public void setQuinta(boolean quinta) {
        this.quinta = quinta;
    }

    public boolean getSexta() {
        return sexta;
    }

    public void setSexta(boolean sexta) {
        this.sexta = sexta;
    }

    public boolean getSabado() {
        return sabado;
    }

    public void setSabado(boolean sabado) {
        this.sabado = sabado;
    }

    public String getHoraMinima() {
        return horaMinima;
    }

    public void setHoraMinima(String horaMinima) {
        this.horaMinima = horaMinima;
    }

    public String getHoraMaxima() {
        return horaMaxima;
    }

    public void setHoraMaxima(String horaMaxima) {
        this.horaMaxima = horaMaxima;
    }

    @XmlTransient
    public Collection<CredencialGradeHoraria> getCredencialGradeHorariaCollection() {
		return credencialGradeHorariaCollection;
	}
    
    public void setCredencialGradeHorariaCollection(
			Collection<CredencialGradeHoraria> credencialGradeHorariaCollection) {
		this.credencialGradeHorariaCollection = credencialGradeHorariaCollection;
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
        if (!(object instanceof GradeHoraria)) {
            return false;
        }
        GradeHoraria other = (GradeHoraria) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descricao;
    }
    
}
