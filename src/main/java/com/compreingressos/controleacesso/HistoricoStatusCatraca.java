/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compreingressos.controleacesso;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Intuiti 04
 */
@Entity
@Table(name = "HistoricoStatusCatraca")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistoricoStatusCatraca.findAll", query = "SELECT h FROM HistoricoStatusCatraca h"),
    @NamedQuery(name = "HistoricoStatusCatraca.findByCodigo", query = "SELECT h FROM HistoricoStatusCatraca h WHERE h.codigo = :codigo"),
    @NamedQuery(name = "HistoricoStatusCatraca.findByDataHora", query = "SELECT h FROM HistoricoStatusCatraca h WHERE h.dataHora = :dataHora"),
    @NamedQuery(name = "HistoricoStatusCatraca.findByStatus", query = "SELECT h FROM HistoricoStatusCatraca h WHERE h.status = :status")})
public class HistoricoStatusCatraca implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataHora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHora;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private Character status;
    @JoinColumn(name = "catraca", referencedColumnName = "codigo")
    @ManyToOne
    private Catraca catraca;
    @JoinColumn(name = "contratante", referencedColumnName = "codigo")
    @ManyToOne
    private Contratante contratante;

    public HistoricoStatusCatraca() {
    }

    public HistoricoStatusCatraca(Integer codigo) {
        this.codigo = codigo;
    }

    public HistoricoStatusCatraca(Integer codigo, Date dataHora, Character status) {
        this.codigo = codigo;
        this.dataHora = dataHora;
        this.status = status;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }
    
    public Catraca getCatraca() {
		return catraca;
	}

	public void setCatraca(Catraca catraca) {
		this.catraca = catraca;
	}

	public Contratante getContratante() {
		return contratante;
	}

	public void setContratante(Contratante contratante) {
		this.contratante = contratante;
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
        if (!(object instanceof HistoricoStatusCatraca)) {
            return false;
        }
        HistoricoStatusCatraca other = (HistoricoStatusCatraca) object;
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
