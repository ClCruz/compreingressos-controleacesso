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
@Table(name = "TipoCredencial")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoCredencial.findAll", query = "SELECT t FROM TipoCredencial t"),
    @NamedQuery(name = "TipoCredencial.findByCodigo", query = "SELECT t FROM TipoCredencial t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TipoCredencial.findByDescricao", query = "SELECT t FROM TipoCredencial t WHERE t.descricao = :descricao"),
    @NamedQuery(name = "TipoCredencial.findByDataHoraAtualizacao", query = "SELECT t FROM TipoCredencial t WHERE t.dataHoraAtualizacao = :dataHoraAtualizacao"),
    @NamedQuery(name = "TipoCredencial.findByAtivo", query = "SELECT t FROM TipoCredencial t WHERE t.ativo = :ativo")})
public class TipoCredencial implements Serializable {

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
    @Column(name = "dataHoraAtualizacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraAtualizacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ativo")
    private boolean ativo = true;
    @Basic(optional = false)
    @NotNull
    @Column(name = "devolveTag")
    private boolean devolveTag = true;
    @JoinColumn(name = "contratante", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Contratante contratante;
    @OneToMany(mappedBy = "tipoCredencial")
    private Collection<SetorCredencial> setorCredencialCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoCredencial")
    private Collection<CredencialGradeHoraria> credencialGradeHorariaCollection;
    @OneToMany(mappedBy = "tipoCredencial")
    private Collection<Credencial> credencialCollection;

    public TipoCredencial() {
    }

    public TipoCredencial(Integer codigo) {
        this.codigo = codigo;
    }

    public TipoCredencial(Integer codigo, String descricao, Date dataHoraAtualizacao, boolean ativo, boolean devolveTag) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.dataHoraAtualizacao = dataHoraAtualizacao;
        this.ativo = ativo;
        this.devolveTag = devolveTag;
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

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    public boolean getDevolveTag() {
		return devolveTag;
	}

	public void setDevolveTag(boolean devolveTag) {
		this.devolveTag = devolveTag;
	}

	public Contratante getContratante() {
        return contratante;
    }

    public void setContratante(Contratante contratante) {
        this.contratante = contratante;
    }

    @XmlTransient
    public Collection<SetorCredencial> getSetorCredencialCollection() {
        return setorCredencialCollection;
    }

    public void setSetorCredencialCollection(Collection<SetorCredencial> setorCredencialCollection) {
        this.setorCredencialCollection = setorCredencialCollection;
    }

    @XmlTransient
    public Collection<CredencialGradeHoraria> getCredencialGradeHorariaCollection() {
        return credencialGradeHorariaCollection;
    }

    public void setCredencialGradeHorariaCollection(Collection<CredencialGradeHoraria> credencialGradeHorariaCollection) {
        this.credencialGradeHorariaCollection = credencialGradeHorariaCollection;
    }

    @XmlTransient
    public Collection<Credencial> getCredencialCollection() {
        return credencialCollection;
    }

    public void setCredencialCollection(Collection<Credencial> credencialCollection) {
        this.credencialCollection = credencialCollection;
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
        if (!(object instanceof TipoCredencial)) {
            return false;
        }
        TipoCredencial other = (TipoCredencial) object;
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
