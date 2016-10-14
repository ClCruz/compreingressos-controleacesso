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
@Table(name = "SetorCredencial")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SetorCredencial.findAll", query = "SELECT s FROM SetorCredencial s"),
    @NamedQuery(name = "SetorCredencial.findByDataHoraAtualizacao", query = "SELECT s FROM SetorCredencial s WHERE s.dataHoraAtualizacao = :dataHoraAtualizacao"),
    @NamedQuery(name = "SetorCredencial.findByAtivo", query = "SELECT s FROM SetorCredencial s WHERE s.ativo = :ativo"),
    @NamedQuery(name = "SetorCredencial.findByCodigo", query = "SELECT s FROM SetorCredencial s WHERE s.codigo = :codigo")})
public class SetorCredencial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "dataHoraAtualizacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraAtualizacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ativo")
    private boolean ativo = true;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @JoinColumn(name = "setor", referencedColumnName = "codigo")
    @ManyToOne
    private Setor setor;
    @JoinColumn(name = "tipoCredencial", referencedColumnName = "codigo")
    @ManyToOne
    private TipoCredencial tipoCredencial;

    public SetorCredencial() {
    }

    public SetorCredencial(Integer codigo) {
        this.codigo = codigo;
    }

    public SetorCredencial(Integer codigo, Date dataHoraAtualizacao, boolean ativo) {
        this.codigo = codigo;
        this.dataHoraAtualizacao = dataHoraAtualizacao;
        this.ativo = ativo;
    }

    public Date getDataHoraAtualizacao() {
        return dataHoraAtualizacao;
    }

    public void setDataHoraAtualizacao(Date dataHoraAtualizacao) {
        this.dataHoraAtualizacao = dataHoraAtualizacao;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
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
        if (!(object instanceof SetorCredencial)) {
            return false;
        }
        SetorCredencial other = (SetorCredencial) object;
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
