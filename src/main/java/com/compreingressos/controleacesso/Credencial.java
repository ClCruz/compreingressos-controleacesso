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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Intuiti 04
 */
@Entity
@Table(name = "Credencial")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Credencial.findAll", query = "SELECT c FROM Credencial c"),
    @NamedQuery(name = "Credencial.findByCodigo", query = "SELECT c FROM Credencial c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "Credencial.findByNumeroTag", query = "SELECT c FROM Credencial c WHERE c.numeroTag = :numeroTag"),
    @NamedQuery(name = "Credencial.findByValidade", query = "SELECT c FROM Credencial c WHERE c.validade = :validade")})
public class Credencial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "numeroTag")
    private String numeroTag;
    @Column(name = "validade")
    @Temporal(TemporalType.TIMESTAMP)
    private Date validade;
    @JoinColumn(name = "credenciado", referencedColumnName = "codigo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Credenciado credenciado;
    @JoinColumn(name = "tipoCredencial", referencedColumnName = "codigo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoCredencial tipoCredencial = new TipoCredencial();
    @Transient
    private Credenciado idCredenciado;
    
    public Credencial() {
    }

    public Credencial(Integer codigo) {
        this.codigo = codigo;
    }

    public Credencial(Integer codigo, String numeroTag) {
        this.codigo = codigo;
        this.numeroTag = numeroTag;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNumeroTag() {
        return numeroTag;
    }

    public void setNumeroTag(String numeroTag) {
        this.numeroTag = numeroTag;
    }

    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }

    public Credenciado getCredenciado() {
		return credenciado;
	}

	public void setCredenciado(Credenciado credenciado) {
		this.credenciado = credenciado;
	}

	public TipoCredencial getTipoCredencial() {
		return tipoCredencial;
	}

	public void setTipoCredencial(TipoCredencial tipoCredencial) {
		this.tipoCredencial = tipoCredencial;
	}

	public Credenciado getIdCredenciado() {
		return idCredenciado;
	}

	public void setIdCredenciado(Credenciado idCredenciado) {
		this.idCredenciado = idCredenciado;
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
        if (!(object instanceof Credencial)) {
            return false;
        }
        Credencial other = (Credencial) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.compreingressos.controleacesso.Credencial[ codigo=" + codigo + " ]";
    }
    
}
