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
@Table(name = "Catraca")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Catraca.findAll", query = "SELECT c FROM Catraca c"),
    @NamedQuery(name = "Catraca.findByCodigo", query = "SELECT c FROM Catraca c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "Catraca.findByCodigoCatraca", query = "SELECT c FROM Catraca c WHERE c.codigoCatraca = :codigoCatraca"),
    @NamedQuery(name = "Catraca.findByDataHoraAtualizacao", query = "SELECT c FROM Catraca c WHERE c.dataHoraAtualizacao = :dataHoraAtualizacao"),
    @NamedQuery(name = "Catraca.findByAtivo", query = "SELECT c FROM Catraca c WHERE c.ativo = :ativo"),
    @NamedQuery(name = "Catraca.findByNumeroIP", query = "SELECT c FROM Catraca c WHERE c.numeroIP = :numeroIP")})
public class Catraca implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "codigoCatraca")
    private String codigoCatraca;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataHoraAtualizacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraAtualizacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ativo")
    private boolean ativo = true;
    @Size(max = 15)
    @Column(name = "numeroIP")
    private String numeroIP;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "catraca")
    private Collection<CatracaSetor> catracaSetorCollection;
    @OneToMany(mappedBy = "catraca")
    private Collection<AcessoCatraca> acessoCatracaCollection;
    @JoinColumn(name = "contratante", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Contratante contratante;
    @JoinColumn(name = "modeloCatraca", referencedColumnName = "codigo")
    @ManyToOne
    private ModeloCatraca modeloCatraca;
    @OneToMany(mappedBy = "catraca")
    private Collection<Falha> falhaCollection;

    public Catraca() {
    }

    public Catraca(Integer codigo) {
        this.codigo = codigo;
    }

    public Catraca(Integer codigo, String codigoCatraca, Date dataHoraAtualizacao, boolean ativo) {
        this.codigo = codigo;
        this.codigoCatraca = codigoCatraca;
        this.dataHoraAtualizacao = dataHoraAtualizacao;
        this.ativo = ativo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getCodigoCatraca() {
        return codigoCatraca;
    }

    public void setCodigoCatraca(String codigoCatraca) {
        this.codigoCatraca = codigoCatraca;
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

    public String getNumeroIP() {
        return numeroIP;
    }

    public void setNumeroIP(String numeroIP) {
        this.numeroIP = numeroIP;
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

    public Contratante getContratante() {
        return contratante;
    }

    public void setContratante(Contratante contratante) {
        this.contratante = contratante;
    }

    public ModeloCatraca getModeloCatraca() {
        return modeloCatraca;
    }

    public void setModeloCatraca(ModeloCatraca modeloCatraca) {
        this.modeloCatraca = modeloCatraca;
    }

    @XmlTransient
    public Collection<Falha> getFalhaCollection() {
        return falhaCollection;
    }

    public void setFalhaCollection(Collection<Falha> falhaCollection) {
        this.falhaCollection = falhaCollection;
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
        if (!(object instanceof Catraca)) {
            return false;
        }
        Catraca other = (Catraca) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codigoCatraca.toString();
    }
    
}
