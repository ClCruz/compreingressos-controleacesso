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
@Table(name = "Programa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Programa.findAll", query = "SELECT p FROM Programa p"),
    @NamedQuery(name = "Programa.findByCodigo", query = "SELECT p FROM Programa p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "Programa.findByDescricao", query = "SELECT p FROM Programa p WHERE p.descricao = :descricao"),
    @NamedQuery(name = "Programa.findByDataHoraAtualizacao", query = "SELECT p FROM Programa p WHERE p.dataHoraAtualizacao = :dataHoraAtualizacao"),
    @NamedQuery(name = "Programa.findByUrl", query = "SELECT p FROM Programa p WHERE p.url = :url"),
    @NamedQuery(name = "Programa.findByOrdemExibicao", query = "SELECT p FROM Programa p WHERE p.ordemExibicao = :ordemExibicao"),
    @NamedQuery(name = "Programa.findByAtivo", query = "SELECT p FROM Programa p WHERE p.ativo = :ativo"),
    @NamedQuery(name = "Programa.findByPrograma", query = "SELECT p FROM Programa p WHERE p.programa = :programa")})
public class Programa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @Column(name = "dataHoraAtualizacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraAtualizacao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "url")
    private String url;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ordemExibicao")
    private int ordemExibicao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ativo")
    private boolean ativo = true;
    @Column(name = "programa")
    private Integer programa;
    @OneToMany(mappedBy = "programa")
    private Collection<TipoUsuarioPrograma> tipoUsuarioProgramaCollection;

    public Programa() {
    }

    public Programa(Integer codigo) {
        this.codigo = codigo;
    }

    public Programa(Integer codigo, String descricao, Date dataHoraAtualizacao, String url, int ordemExibicao, boolean ativo) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.dataHoraAtualizacao = dataHoraAtualizacao;
        this.url = url;
        this.ordemExibicao = ordemExibicao;
        this.ativo = ativo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataHoraAtualizacao() {
        return dataHoraAtualizacao;
    }

    public void setDataHoraAtualizacao(Date dataHoraAtualizacao) {
        this.dataHoraAtualizacao = dataHoraAtualizacao;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getOrdemExibicao() {
        return ordemExibicao;
    }

    public void setOrdemExibicao(int ordemExibicao) {
        this.ordemExibicao = ordemExibicao;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Integer getPrograma() {
        return programa;
    }

    public void setPrograma(Integer programa) {
        this.programa = programa;
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
        if (!(object instanceof Programa)) {
            return false;
        }
        Programa other = (Programa) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descricao;
    }
    
}
