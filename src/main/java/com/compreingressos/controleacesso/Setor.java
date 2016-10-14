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
@Table(name = "Setor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Setor.findAll", query = "SELECT s FROM Setor s"),
    @NamedQuery(name = "Setor.findByCodigo", query = "SELECT s FROM Setor s WHERE s.codigo = :codigo"),
    @NamedQuery(name = "Setor.findByDescricaoSetor", query = "SELECT s FROM Setor s WHERE s.descricaoSetor = :descricaoSetor"),
    @NamedQuery(name = "Setor.findByDataHoraAtualizacao", query = "SELECT s FROM Setor s WHERE s.dataHoraAtualizacao = :dataHoraAtualizacao"),
    @NamedQuery(name = "Setor.findByAcessibilidadeEspecial", query = "SELECT s FROM Setor s WHERE s.acessibilidadeEspecial = :acessibilidadeEspecial"),
    @NamedQuery(name = "Setor.findByAtivo", query = "SELECT s FROM Setor s WHERE s.ativo = :ativo")})
public class Setor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "descricaoSetor")
    private String descricaoSetor;
    @Basic(optional = false)
    @Column(name = "dataHoraAtualizacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraAtualizacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "acessibilidadeEspecial")
    private boolean acessibilidadeEspecial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ativo")
    private boolean ativo = true;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "setor")
    private Collection<CatracaSetor> catracaSetorCollection;
    @OneToMany(mappedBy = "setor")
    private Collection<AcessoCatraca> acessoCatracaCollection;
    @OneToMany(mappedBy = "setor")
    private Collection<SetorCredencial> setorCredencialCollection;
    @JoinColumn(name = "layout", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Layout layout;
    @OneToMany(mappedBy = "setor")
    private Collection<IngressoVendido> ingressoVendidoCollection;

    public Setor() {
    }

    public Setor(Integer codigo) {
        this.codigo = codigo;
    }

    public Setor(Integer codigo, String descricaoSetor, Date dataHoraAtualizacao, boolean acessibilidadeEspecial, boolean ativo) {
        this.codigo = codigo;
        this.descricaoSetor = descricaoSetor;
        this.dataHoraAtualizacao = dataHoraAtualizacao;
        this.acessibilidadeEspecial = acessibilidadeEspecial;
        this.ativo = ativo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricaoSetor() {
        return descricaoSetor;
    }

    public void setDescricaoSetor(String descricaoSetor) {
        this.descricaoSetor = descricaoSetor;
    }

    public Date getDataHoraAtualizacao() {
        return dataHoraAtualizacao;
    }

    public void setDataHoraAtualizacao(Date dataHoraAtualizacao) {
        this.dataHoraAtualizacao = dataHoraAtualizacao;
    }

    public boolean getAcessibilidadeEspecial() {
        return acessibilidadeEspecial;
    }

    public void setAcessibilidadeEspecial(boolean acessibilidadeEspecial) {
        this.acessibilidadeEspecial = acessibilidadeEspecial;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @XmlTransient
    public Collection<CatracaSetor> getCatracaSetorCollection() {
        return catracaSetorCollection;
    }

    public void setCatracaSetorCollection(Collection<CatracaSetor> catracaSetorCollection) {
        this.catracaSetorCollection = catracaSetorCollection;
    }

    @XmlTransient
    public Collection<AcessoCatraca> getAcessoCatracaCollection() {
        return acessoCatracaCollection;
    }

    public void setAcessoCatracaCollection(Collection<AcessoCatraca> acessoCatracaCollection) {
        this.acessoCatracaCollection = acessoCatracaCollection;
    }

    @XmlTransient
    public Collection<SetorCredencial> getSetorCredencialCollection() {
        return setorCredencialCollection;
    }

    public void setSetorCredencialCollection(Collection<SetorCredencial> setorCredencialCollection) {
        this.setorCredencialCollection = setorCredencialCollection;
    }

    public Layout getLayout() {
        return layout;
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    @XmlTransient
    public Collection<IngressoVendido> getIngressoVendidoCollection() {
        return ingressoVendidoCollection;
    }

    public void setIngressoVendidoCollection(Collection<IngressoVendido> ingressoVendidoCollection) {
        this.ingressoVendidoCollection = ingressoVendidoCollection;
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
        if (!(object instanceof Setor)) {
            return false;
        }
        Setor other = (Setor) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descricaoSetor;
    }
    
}
