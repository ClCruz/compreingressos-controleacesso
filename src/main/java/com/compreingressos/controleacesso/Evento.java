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
@Table(name = "Evento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evento.findAll", query = "SELECT e FROM Evento e"),
    @NamedQuery(name = "Evento.findByCodigo", query = "SELECT e FROM Evento e WHERE e.codigo = :codigo"),
    @NamedQuery(name = "Evento.findByDescricaoNome", query = "SELECT e FROM Evento e WHERE e.descricaoNome = :descricaoNome"),
    @NamedQuery(name = "Evento.findByDataInicio", query = "SELECT e FROM Evento e WHERE e.dataInicio = :dataInicio"),
    @NamedQuery(name = "Evento.findByDataFinal", query = "SELECT e FROM Evento e WHERE e.dataFinal = :dataFinal"),
    @NamedQuery(name = "Evento.findByDataAtualizacao", query = "SELECT e FROM Evento e WHERE e.dataAtualizacao = :dataAtualizacao"),
    @NamedQuery(name = "Evento.findByAtivo", query = "SELECT e FROM Evento e WHERE e.ativo = :ativo")})
public class Evento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "descricaoNome")
    private String descricaoNome;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataInicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataFinal")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataFinal;
    @Basic(optional = false)
    @Column(name = "dataAtualizacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtualizacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ativo")
    private boolean ativo = true;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "logotipo")
    private String logotipo;
    @JoinColumn(name = "layout", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Layout layout;
    @JoinColumn(name = "localDeControle", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private LocalDeControle localDeControle;
    @OneToMany(mappedBy = "evento")
    private Collection<Apresentacao> apresentacaoCollection;

    public Evento() {
    }

    public Evento(Integer codigo) {
        this.codigo = codigo;
    }

    public Evento(Integer codigo, String descricaoNome, Date dataInicio, Date dataFinal, Date dataAtualizacao, boolean ativo, String logotipo) {
        this.codigo = codigo;
        this.descricaoNome = descricaoNome;
        this.dataInicio = dataInicio;
        this.dataFinal = dataFinal;
        this.dataAtualizacao = dataAtualizacao;
        this.ativo = ativo;
        this.logotipo = logotipo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricaoNome() {
        return descricaoNome;
    }

    public void setDescricaoNome(String descricaoNome) {
        this.descricaoNome = descricaoNome;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
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

    public Layout getLayout() {
        return layout;
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    public LocalDeControle getLocalDeControle() {
        return localDeControle;
    }

    public void setLocalDeControle(LocalDeControle localDeControle) {
        this.localDeControle = localDeControle;
    }

    @XmlTransient
    public Collection<Apresentacao> getApresentacaoCollection() {
        return apresentacaoCollection;
    }

    public void setApresentacaoCollection(Collection<Apresentacao> apresentacaoCollection) {
        this.apresentacaoCollection = apresentacaoCollection;
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
        if (!(object instanceof Evento)) {
            return false;
        }
        Evento other = (Evento) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descricaoNome;
    }
    
}
