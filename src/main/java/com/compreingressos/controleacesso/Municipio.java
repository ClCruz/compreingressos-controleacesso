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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "Municipio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Municipio.findAll", query = "SELECT m FROM Municipio m"),
    @NamedQuery(name = "Municipio.findByCodigo", query = "SELECT m FROM Municipio m WHERE m.codigo = :codigo"),
    @NamedQuery(name = "Municipio.findByCodigoMunicipio", query = "SELECT m FROM Municipio m WHERE m.codigoMunicipio = :codigoMunicipio"),
    @NamedQuery(name = "Municipio.findByNome", query = "SELECT m FROM Municipio m WHERE m.nome = :nome"),
    @NamedQuery(name = "Municipio.findByDataHoraAtualizacao", query = "SELECT m FROM Municipio m WHERE m.dataHoraAtualizacao = :dataHoraAtualizacao")})
public class Municipio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigoMunicipio")
    private int codigoMunicipio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "dataHoraAtualizacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraAtualizacao;
    @OneToMany(mappedBy = "municipio")
    private Collection<Usuario> usuarioCollection;
    @OneToMany(mappedBy = "municipio")
    private Collection<LocalDeControle> localDeControleCollection;
    @JoinColumn(name = "estado", referencedColumnName = "codigo")
    @ManyToOne
    private Estado estado;
    @OneToMany(mappedBy = "municipio")
    private Collection<Credenciado> credenciadoCollection;

    public Municipio() {
    }

    public Municipio(Integer codigo) {
        this.codigo = codigo;
    }

    public Municipio(Integer codigo, int codigoMunicipio, String nome, Date dataHoraAtualizacao) {
        this.codigo = codigo;
        this.codigoMunicipio = codigoMunicipio;
        this.nome = nome;
        this.dataHoraAtualizacao = dataHoraAtualizacao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public int getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(int codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
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

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    @XmlTransient
    public Collection<LocalDeControle> getLocalDeControleCollection() {
        return localDeControleCollection;
    }

    public void setLocalDeControleCollection(Collection<LocalDeControle> localDeControleCollection) {
        this.localDeControleCollection = localDeControleCollection;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
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
        if (!(object instanceof Municipio)) {
            return false;
        }
        Municipio other = (Municipio) object;
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
