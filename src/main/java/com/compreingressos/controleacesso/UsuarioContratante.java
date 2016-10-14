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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "UsuarioContratante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioContratante.findAll", query = "SELECT u FROM UsuarioContratante u"),
    @NamedQuery(name = "UsuarioContratante.findByDataHoraAtualizacao", query = "SELECT u FROM UsuarioContratante u WHERE u.dataHoraAtualizacao = :dataHoraAtualizacao"),
    @NamedQuery(name = "UsuarioContratante.findByUsuario", query = "SELECT u FROM UsuarioContratante u WHERE u.usuarioContratantePK.usuario = :usuario"),
    @NamedQuery(name = "UsuarioContratante.findByContratante", query = "SELECT u FROM UsuarioContratante u WHERE u.usuarioContratantePK.contratante = :contratante")})
public class UsuarioContratante implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsuarioContratantePK usuarioContratantePK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataHoraAtualizacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraAtualizacao;
    @JoinColumn(name = "contratante", referencedColumnName = "codigo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Contratante contratante;
    @JoinColumn(name = "usuario", referencedColumnName = "codigo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;

    public UsuarioContratante() {
    }

    public UsuarioContratante(UsuarioContratantePK usuarioContratantePK) {
        this.usuarioContratantePK = usuarioContratantePK;
    }

    public UsuarioContratante(UsuarioContratantePK usuarioContratantePK, Date dataHoraAtualizacao) {
        this.usuarioContratantePK = usuarioContratantePK;
        this.dataHoraAtualizacao = dataHoraAtualizacao;
    }

    public UsuarioContratante(int usuario, int contratante) {
        this.usuarioContratantePK = new UsuarioContratantePK(usuario, contratante);
    }

    public UsuarioContratantePK getUsuarioContratantePK() {
        return usuarioContratantePK;
    }

    public void setUsuarioContratantePK(UsuarioContratantePK usuarioContratantePK) {
        this.usuarioContratantePK = usuarioContratantePK;
    }

    public Date getDataHoraAtualizacao() {
        return dataHoraAtualizacao;
    }

    public void setDataHoraAtualizacao(Date dataHoraAtualizacao) {
        this.dataHoraAtualizacao = dataHoraAtualizacao;
    }

    public Contratante getContratante() {
        return contratante;
    }

    public void setContratante(Contratante contratante) {
        this.contratante = contratante;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioContratantePK != null ? usuarioContratantePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioContratante)) {
            return false;
        }
        UsuarioContratante other = (UsuarioContratante) object;
        if ((this.usuarioContratantePK == null && other.usuarioContratantePK != null) || (this.usuarioContratantePK != null && !this.usuarioContratantePK.equals(other.usuarioContratantePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.compreingressos.controleacesso.UsuarioContratante[ usuarioContratantePK=" + usuarioContratantePK + " ]";
    }
    
}
