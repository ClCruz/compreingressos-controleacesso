/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compreingressos.controleacesso;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Intuiti 04
 */
@Entity
@Table(name = "ModeloCatraca")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ModeloCatraca.findAll", query = "SELECT m FROM ModeloCatraca m"),
    @NamedQuery(name = "ModeloCatraca.findByCodigo", query = "SELECT m FROM ModeloCatraca m WHERE m.codigo = :codigo"),
    @NamedQuery(name = "ModeloCatraca.findByDescricao", query = "SELECT m FROM ModeloCatraca m WHERE m.descricao = :descricao"),
    @NamedQuery(name = "ModeloCatraca.findByAtivo", query = "SELECT m FROM ModeloCatraca m WHERE m.ativo = :ativo")})
public class ModeloCatraca implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ativo")
    private boolean ativo = true;
    @OneToMany(mappedBy = "modeloCatraca")
    private Collection<Catraca> catracaCollection;
    @JoinColumn(name = "fabricante", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Fabricante fabricante;

    public ModeloCatraca() {
    }

    public ModeloCatraca(Integer codigo) {
        this.codigo = codigo;
    }

    public ModeloCatraca(Integer codigo, String descricao, boolean ativo) {
        this.codigo = codigo;
        this.descricao = descricao;
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

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @XmlTransient
    public Collection<Catraca> getCatracaCollection() {
        return catracaCollection;
    }

    public void setCatracaCollection(Collection<Catraca> catracaCollection) {
        this.catracaCollection = catracaCollection;
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
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
        if (!(object instanceof ModeloCatraca)) {
            return false;
        }
        ModeloCatraca other = (ModeloCatraca) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codigo.toString();
    }
    
}
