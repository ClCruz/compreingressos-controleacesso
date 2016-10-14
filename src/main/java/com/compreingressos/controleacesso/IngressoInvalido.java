/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compreingressos.controleacesso;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Intuiti 04
 */
@Entity
@Table(name = "IngressoInvalido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IngressoInvalido.findAll", query = "SELECT i FROM IngressoInvalido i"),
    @NamedQuery(name = "IngressoInvalido.findByDataHoraAtualizacao", query = "SELECT i FROM IngressoInvalido i WHERE i.dataHoraAtualizacao = :dataHoraAtualizacao"),
    @NamedQuery(name = "IngressoInvalido.findByIngressoVendido", query = "SELECT i FROM IngressoInvalido i WHERE i.ingressoVendido = :ingressoVendido"),
    @NamedQuery(name = "IngressoInvalido.findByMotivo", query = "SELECT i FROM IngressoInvalido i WHERE i.motivo = :motivo")})
public class IngressoInvalido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataHoraAtualizacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraAtualizacao;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "ingressoVendido", referencedColumnName = "codigo")
    @OneToOne(optional=false)
    private IngressoVendido ingressoVendido;
    @Size(max = 60)
    @Column(name = "motivo")
    private String motivo;
 

    public IngressoInvalido() {
    }

    public IngressoInvalido(IngressoVendido ingressoVendido) {
        this.ingressoVendido = ingressoVendido;
    }

    public IngressoInvalido(IngressoVendido ingressoVendido, Date dataHoraAtualizacao) {
        this.ingressoVendido = ingressoVendido;
        this.dataHoraAtualizacao = dataHoraAtualizacao;
    }

    public Date getDataHoraAtualizacao() {
        return dataHoraAtualizacao;
    }

    public void setDataHoraAtualizacao(Date dataHoraAtualizacao) {
        this.dataHoraAtualizacao = dataHoraAtualizacao;
    }

    public IngressoVendido getIngressoVendido() {
        return ingressoVendido;
    }

    public void setIngressoVendido(IngressoVendido ingressoVendido) {
        this.ingressoVendido = ingressoVendido;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ingressoVendido != null ? ingressoVendido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IngressoInvalido)) {
            return false;
        }
        IngressoInvalido other = (IngressoInvalido) object;
        if ((this.ingressoVendido == null && other.ingressoVendido != null) || (this.ingressoVendido != null && !this.ingressoVendido.equals(other.ingressoVendido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ingressoVendido.toString();
    }
    
}
