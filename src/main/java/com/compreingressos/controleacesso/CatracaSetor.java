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
import static javax.persistence.GenerationType.IDENTITY;
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
@Table(name = "CatracaSetor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatracaSetor.findAll", query = "SELECT c FROM CatracaSetor c"),
    @NamedQuery(name = "CatracaSetor.findByDataAtualizacao", query = "SELECT c FROM CatracaSetor c WHERE c.dataAtualizacao = :dataAtualizacao"),
    @NamedQuery(name = "CatracaSetor.findByCodigo", query = "SELECT c FROM CatracaSetor c WHERE c.codigo = :codigo")})
public class CatracaSetor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataAtualizacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtualizacao;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @JoinColumn(name = "catraca", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Catraca catraca;
    @JoinColumn(name = "setor", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Setor setor;

    public CatracaSetor() {
    }

    public CatracaSetor(Integer codigo) {
        this.codigo = codigo;
    }

    public CatracaSetor(Integer codigo, Date dataAtualizacao) {
        this.codigo = codigo;
        this.dataAtualizacao = dataAtualizacao;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Catraca getCatraca() {
        return catraca;
    }

    public void setCatraca(Catraca catraca) {
        this.catraca = catraca;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
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
        if (!(object instanceof CatracaSetor)) {
            return false;
        }
        CatracaSetor other = (CatracaSetor) object;
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
