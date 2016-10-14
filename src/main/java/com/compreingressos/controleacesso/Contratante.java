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
import javax.persistence.Lob;
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
@Table(name = "Contratante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contratante.findAll", query = "SELECT c FROM Contratante c"),
    @NamedQuery(name = "Contratante.findByCodigo", query = "SELECT c FROM Contratante c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "Contratante.findByNome", query = "SELECT c FROM Contratante c WHERE c.nome = :nome"),
    @NamedQuery(name = "Contratante.findByDataHoraAtualizacao", query = "SELECT c FROM Contratante c WHERE c.dataHoraAtualizacao = :dataHoraAtualizacao"),
    @NamedQuery(name = "Contratante.findByAtivo", query = "SELECT c FROM Contratante c WHERE c.ativo = :ativo")})
public class Contratante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataHoraAtualizacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraAtualizacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ativo")
    private boolean ativo;
    @Lob
    @Column(name = "logotipo")
    private String logotipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contratante")
    private Collection<TipoCredencial> tipoCredencialCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contratante")
    private Collection<Catraca> catracaCollection;
    @OneToMany(mappedBy = "contratante")
    private Collection<LocalDeControle> localDeControleCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contratante")
    private Collection<UsuarioContratante> usuarioContratanteCollection;
    @OneToMany(mappedBy = "empresa")
    private Collection<Credenciado> credenciadoCollection;

    public Contratante() {
    }

    public Contratante(Integer codigo) {
        this.codigo = codigo;
    }

    public Contratante(Integer codigo, String nome, Date dataHoraAtualizacao, boolean ativo) {
        this.codigo = codigo;
        this.nome = nome;
        this.dataHoraAtualizacao = dataHoraAtualizacao;
        this.ativo = ativo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getLogotipo() {
        return logotipo;
    }

    public void setLogotipo(String logotipo) {
        this.logotipo = logotipo;
    }

    @XmlTransient
    public Collection<TipoCredencial> getTipoCredencialCollection() {
        return tipoCredencialCollection;
    }

    public void setTipoCredencialCollection(Collection<TipoCredencial> tipoCredencialCollection) {
        this.tipoCredencialCollection = tipoCredencialCollection;
    }

    @XmlTransient
    public Collection<Catraca> getCatracaCollection() {
        return catracaCollection;
    }

    public void setCatracaCollection(Collection<Catraca> catracaCollection) {
        this.catracaCollection = catracaCollection;
    }

    @XmlTransient
    public Collection<LocalDeControle> getLocalDeControleCollection() {
        return localDeControleCollection;
    }

    public void setLocalDeControleCollection(Collection<LocalDeControle> localDeControleCollection) {
        this.localDeControleCollection = localDeControleCollection;
    }

    @XmlTransient
    public Collection<UsuarioContratante> getUsuarioContratanteCollection() {
        return usuarioContratanteCollection;
    }

    public void setUsuarioContratanteCollection(Collection<UsuarioContratante> usuarioContratanteCollection) {
        this.usuarioContratanteCollection = usuarioContratanteCollection;
    }

    @XmlTransient
    public Collection<Credenciado> getCredenciadoCollection() {
        return credenciadoCollection;
    }

    public void setCredenciadoCollection(Collection<Credenciado> credenciadoCollection) {
        this.credenciadoCollection = credenciadoCollection;
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
        if (!(object instanceof Contratante)) {
            return false;
        }
        Contratante other = (Contratante) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nome;
    }
    
}
