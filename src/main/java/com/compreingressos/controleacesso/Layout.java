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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "Layout")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Layout.findAll", query = "SELECT l FROM Layout l"),
    @NamedQuery(name = "Layout.findByCodigo", query = "SELECT l FROM Layout l WHERE l.codigo = :codigo"),
    @NamedQuery(name = "Layout.findByDataHoraAtualizacao", query = "SELECT l FROM Layout l WHERE l.dataHoraAtualizacao = :dataHoraAtualizacao"),
    @NamedQuery(name = "Layout.findByNome", query = "SELECT l FROM Layout l WHERE l.nome = :nome"),
    @NamedQuery(name = "Layout.findByAtivo", query = "SELECT l FROM Layout l WHERE l.ativo = :ativo")})
public class Layout implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "dataHoraAtualizacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraAtualizacao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ativo")
    private boolean ativo = true;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "imagem")
    private String imagem;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "layout")
    private Collection<Evento> eventoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "layout")
    private Collection<Setor> setorCollection;
    @JoinColumn(name = "localDeControle", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private LocalDeControle localDeControle;

    public Layout() {
    }

    public Layout(Integer codigo) {
        this.codigo = codigo;
    }

    public Layout(Integer codigo, Date dataHoraAtualizacao, String nome, boolean ativo, String imagem) {
        this.codigo = codigo;
        this.dataHoraAtualizacao = dataHoraAtualizacao;
        this.nome = nome;
        this.ativo = ativo;
        this.imagem = imagem;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Date getDataHoraAtualizacao() {
        return dataHoraAtualizacao;
    }

    public void setDataHoraAtualizacao(Date dataHoraAtualizacao) {
        this.dataHoraAtualizacao = dataHoraAtualizacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
    
    @XmlTransient
    public Collection<Evento> getEventoCollection() {
        return eventoCollection;
    }

    public void setEventoCollection(Collection<Evento> eventoCollection) {
        this.eventoCollection = eventoCollection;
    }

    @XmlTransient
    public Collection<Setor> getSetorCollection() {
        return setorCollection;
    }

    public void setSetorCollection(Collection<Setor> setorCollection) {
        this.setorCollection = setorCollection;
    }

    public LocalDeControle getLocalDeControle() {
        return localDeControle;
    }

    public void setLocalDeControle(LocalDeControle localDeControle) {
        this.localDeControle = localDeControle;
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
        if (!(object instanceof Layout)) {
            return false;
        }
        Layout other = (Layout) object;
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
