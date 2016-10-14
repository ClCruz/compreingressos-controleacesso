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
@Table(name = "TipoUsuarioPrograma")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoUsuarioPrograma.findAll", query = "SELECT t FROM TipoUsuarioPrograma t"),
    @NamedQuery(name = "TipoUsuarioPrograma.findByDataHoraAtualizacao", query = "SELECT t FROM TipoUsuarioPrograma t WHERE t.dataHoraAtualizacao = :dataHoraAtualizacao"),
    @NamedQuery(name = "TipoUsuarioPrograma.findByCodigo", query = "SELECT t FROM TipoUsuarioPrograma t WHERE t.codigo = :codigo")})
public class TipoUsuarioPrograma implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "dataHoraAtualizacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraAtualizacao;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @JoinColumn(name = "programa", referencedColumnName = "codigo")
    @ManyToOne
    private Programa programa;
    @JoinColumn(name = "tipoUsuario", referencedColumnName = "codigo")
    @ManyToOne
    private TipoUsuario tipoUsuario;

    public TipoUsuarioPrograma() {
    }

    public TipoUsuarioPrograma(Integer codigo) {
        this.codigo = codigo;
    }

    public TipoUsuarioPrograma(Integer codigo, Date dataHoraAtualizacao) {
        this.codigo = codigo;
        this.dataHoraAtualizacao = dataHoraAtualizacao;
    }

    public Date getDataHoraAtualizacao() {
        return dataHoraAtualizacao;
    }

    public void setDataHoraAtualizacao(Date dataHoraAtualizacao) {
        this.dataHoraAtualizacao = dataHoraAtualizacao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
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
        if (!(object instanceof TipoUsuarioPrograma)) {
            return false;
        }
        TipoUsuarioPrograma other = (TipoUsuarioPrograma) object;
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
