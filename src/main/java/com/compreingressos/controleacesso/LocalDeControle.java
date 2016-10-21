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
@Table(name = "LocalDeControle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LocalDeControle.findAll", query = "SELECT l FROM LocalDeControle l"),
    @NamedQuery(name = "LocalDeControle.findByNome", query = "SELECT l FROM LocalDeControle l WHERE l.nome = :nome"),
    @NamedQuery(name = "LocalDeControle.findByAtivo", query = "SELECT l FROM LocalDeControle l WHERE l.ativo = :ativo"),
    @NamedQuery(name = "LocalDeControle.findByDataHoraAtualizacao", query = "SELECT l FROM LocalDeControle l WHERE l.dataHoraAtualizacao = :dataHoraAtualizacao"),
    @NamedQuery(name = "LocalDeControle.findByEndereco", query = "SELECT l FROM LocalDeControle l WHERE l.endereco = :endereco"),
    @NamedQuery(name = "LocalDeControle.findByNumeroEndereco", query = "SELECT l FROM LocalDeControle l WHERE l.numeroEndereco = :numeroEndereco"),
    @NamedQuery(name = "LocalDeControle.findByBairro", query = "SELECT l FROM LocalDeControle l WHERE l.bairro = :bairro"),
    @NamedQuery(name = "LocalDeControle.findByCodigo", query = "SELECT l FROM LocalDeControle l WHERE l.codigo = :codigo"),
    @NamedQuery(name = "LocalDeControle.findByComplemento", query = "SELECT l FROM LocalDeControle l WHERE l.complemento = :complemento")})
public class LocalDeControle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ativo")
    private boolean ativo;
    @Basic(optional = false)
    @Column(name = "dataHoraAtualizacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraAtualizacao;
    @Size(max = 60)
    @NotNull
    @Column(name = "endereco")
    private String endereco;
    @Size(max = 10)
    @NotNull
    @Column(name = "numeroEndereco")
    private String numeroEndereco;
    @Size(max = 30)
    @NotNull
    @Column(name = "bairro")
    private String bairro;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "complemento")
    private String complemento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "localDeControle")
    private Collection<Evento> eventoCollection;
    @JoinColumn(name = "contratante", referencedColumnName = "codigo")
    @ManyToOne
    private Contratante contratante;
    @JoinColumn(name = "municipio", referencedColumnName = "codigo")
    @NotNull
    @ManyToOne
    private Municipio municipio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "localDeControle")
    private Collection<Layout> layoutCollection;

    public LocalDeControle() {
    }

    public LocalDeControle(Integer codigo) {
        this.codigo = codigo;
    }

    public LocalDeControle(Integer codigo, String nome, boolean ativo, Date dataHoraAtualizacao, String complemento) {
        this.codigo = codigo;
        this.nome = nome;
        this.ativo = ativo;
        this.dataHoraAtualizacao = dataHoraAtualizacao;
        this.complemento = complemento;
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

    public Date getDataHoraAtualizacao() {
        return dataHoraAtualizacao;
    }

    public void setDataHoraAtualizacao(Date dataHoraAtualizacao) {
        this.dataHoraAtualizacao = dataHoraAtualizacao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumeroEndereco() {
        return numeroEndereco;
    }

    public void setNumeroEndereco(String numeroEndereco) {
        this.numeroEndereco = numeroEndereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    @XmlTransient
    public Collection<Evento> getEventoCollection() {
        return eventoCollection;
    }

    public void setEventoCollection(Collection<Evento> eventoCollection) {
        this.eventoCollection = eventoCollection;
    }

    public Contratante getContratante() {
        return contratante;
    }

    public void setContratante(Contratante contratante) {
        this.contratante = contratante;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    @XmlTransient
    public Collection<Layout> getLayoutCollection() {
        return layoutCollection;
    }

    public void setLayoutCollection(Collection<Layout> layoutCollection) {
        this.layoutCollection = layoutCollection;
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
        if (!(object instanceof LocalDeControle)) {
            return false;
        }
        LocalDeControle other = (LocalDeControle) object;
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
