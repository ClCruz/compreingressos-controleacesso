/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compreingressos.controleacesso;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Intuiti 04
 */
@Entity
@Table(name = "TipoUsuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoUsuario.findAll", query = "SELECT t FROM TipoUsuario t"),
    @NamedQuery(name = "TipoUsuario.findByCodigo", query = "SELECT t FROM TipoUsuario t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TipoUsuario.findByDescricaoTipoUsuario", query = "SELECT t FROM TipoUsuario t WHERE t.descricaoTipoUsuario = :descricaoTipoUsuario"),
    @NamedQuery(name = "TipoUsuario.findByDataHoraAtualizacao", query = "SELECT t FROM TipoUsuario t WHERE t.dataHoraAtualizacao = :dataHoraAtualizacao"),
    @NamedQuery(name = "TipoUsuario.findByInAdmin", query = "SELECT t FROM TipoUsuario t WHERE t.inAdmin = :inAdmin"),
    @NamedQuery(name = "TipoUsuario.findByStatus", query = "SELECT t FROM TipoUsuario t WHERE t.status = :status")})
public class TipoUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "descricaoTipoUsuario")
    private String descricaoTipoUsuario;
    @Basic(optional = false)
    @Column(name = "dataHoraAtualizacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraAtualizacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "inAdmin")
    private boolean inAdmin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private boolean status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoUsuario")
    private Collection<Usuario> usuarioCollection;
    @OneToMany(mappedBy = "tipoUsuario")
    private Collection<TipoUsuarioPrograma> tipoUsuarioProgramaCollection;

    public TipoUsuario() {
    }

    public TipoUsuario(Integer codigo) {
        this.codigo = codigo;
    }

    public TipoUsuario(Integer codigo, String descricaoTipoUsuario, Date dataHoraAtualizacao, boolean inAdmin, boolean status) {
        this.codigo = codigo;
        this.descricaoTipoUsuario = descricaoTipoUsuario;
        this.dataHoraAtualizacao = dataHoraAtualizacao;
        this.inAdmin = inAdmin;
        this.status = status;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricaoTipoUsuario() {
        return descricaoTipoUsuario;
    }

    public void setDescricaoTipoUsuario(String descricaoTipoUsuario) {
        this.descricaoTipoUsuario = descricaoTipoUsuario;
    }

    public Date getDataHoraAtualizacao() {
        return dataHoraAtualizacao;
    }

    public void setDataHoraAtualizacao(Date dataHoraAtualizacao) {
        this.dataHoraAtualizacao = dataHoraAtualizacao;
    }

    public boolean getInAdmin() {
        return inAdmin;
    }

    public void setInAdmin(boolean inAdmin) {
        this.inAdmin = inAdmin;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    @XmlTransient
    public Collection<TipoUsuarioPrograma> getTipoUsuarioProgramaCollection() {
        return tipoUsuarioProgramaCollection;
    }

    public void setTipoUsuarioProgramaCollection(Collection<TipoUsuarioPrograma> tipoUsuarioProgramaCollection) {
        this.tipoUsuarioProgramaCollection = tipoUsuarioProgramaCollection;
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
        if (!(object instanceof TipoUsuario)) {
            return false;
        }
        TipoUsuario other = (TipoUsuario) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descricaoTipoUsuario;
    }
    
}
