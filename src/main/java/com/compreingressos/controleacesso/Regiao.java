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
@Table(name = "Regiao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Regiao.findAll", query = "SELECT r FROM Regiao r"),
    @NamedQuery(name = "Regiao.findByCodigo", query = "SELECT r FROM Regiao r WHERE r.codigo = :codigo"),
    @NamedQuery(name = "Regiao.findByDescricaoRegiao", query = "SELECT r FROM Regiao r WHERE r.descricaoRegiao = :descricaoRegiao"),
    @NamedQuery(name = "Regiao.findByDataHoraAtualizacao", query = "SELECT r FROM Regiao r WHERE r.dataHoraAtualizacao = :dataHoraAtualizacao")})
public class Regiao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "descricaoRegiao")
    private String descricaoRegiao;
    @Basic(optional = false)
    @Column(name = "dataHoraAtualizacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraAtualizacao;
    @JoinColumn(name = "pais", referencedColumnName = "codigo")
    @ManyToOne
    private Pais pais;
    @JoinColumn(name = "usuario", referencedColumnName = "codigo")
    @ManyToOne
    private Usuario usuario;
    @OneToMany(mappedBy = "regiao")
    private Collection<Estado> estadoCollection;

    public Regiao() {
    }

    public Regiao(Integer codigo) {
        this.codigo = codigo;
    }

    public Regiao(Integer codigo, String descricaoRegiao, Date dataHoraAtualizacao) {
        this.codigo = codigo;
        this.descricaoRegiao = descricaoRegiao;
        this.dataHoraAtualizacao = dataHoraAtualizacao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricaoRegiao() {
        return descricaoRegiao;
    }

    public void setDescricaoRegiao(String descricaoRegiao) {
        this.descricaoRegiao = descricaoRegiao;
    }

    public Date getDataHoraAtualizacao() {
        return dataHoraAtualizacao;
    }

    public void setDataHoraAtualizacao(Date dataHoraAtualizacao) {
        this.dataHoraAtualizacao = dataHoraAtualizacao;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @XmlTransient
    public Collection<Estado> getEstadoCollection() {
        return estadoCollection;
    }

    public void setEstadoCollection(Collection<Estado> estadoCollection) {
        this.estadoCollection = estadoCollection;
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
        if (!(object instanceof Regiao)) {
            return false;
        }
        Regiao other = (Regiao) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descricaoRegiao;
    }
    
}
