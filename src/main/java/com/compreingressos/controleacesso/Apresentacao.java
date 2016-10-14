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
@Table(name = "Apresentacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Apresentacao.findAll", query = "SELECT a FROM Apresentacao a"),
    @NamedQuery(name = "Apresentacao.findByCodigo", query = "SELECT a FROM Apresentacao a WHERE a.codigo = :codigo"),
    @NamedQuery(name = "Apresentacao.findByNome", query = "SELECT a FROM Apresentacao a WHERE a.nome = :nome"),
    @NamedQuery(name = "Apresentacao.findByDataHoraInicio", query = "SELECT a FROM Apresentacao a WHERE a.dataHoraInicio = :dataHoraInicio"),
    @NamedQuery(name = "Apresentacao.findByDataHoraFinal", query = "SELECT a FROM Apresentacao a WHERE a.dataHoraFinal = :dataHoraFinal"),
    @NamedQuery(name = "Apresentacao.findByDataHoraAtualizacao", query = "SELECT a FROM Apresentacao a WHERE a.dataHoraAtualizacao = :dataHoraAtualizacao"),
    @NamedQuery(name = "Apresentacao.findByAtivo", query = "SELECT a FROM Apresentacao a WHERE a.ativo = :ativo"),
    @NamedQuery(name = "Apresentacao.findByValidaEstorno", query = "SELECT a FROM Apresentacao a WHERE a.validaEstorno = :validaEstorno")})
public class Apresentacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataHoraInicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataHoraFinal")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraFinal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataHoraAtualizacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraAtualizacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ativo")
    private boolean ativo = true;
    @Basic(optional = false)
    @NotNull
    @Column(name = "validaEstorno")
    private boolean validaEstorno;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "apresentacao")
    private Collection<LiberacaoEmergencial> liberacaoEmergencialCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "apresentacao")
    private Collection<IngressoVendido> ingressoVendidoCollection;
    @JoinColumn(name = "evento", referencedColumnName = "codigo")
    @ManyToOne
    private Evento evento;

    public Apresentacao() {
    }

    public Apresentacao(Integer codigo) {
        this.codigo = codigo;
    }

    public Apresentacao(Integer codigo, String nome, Date dataHoraInicio, Date dataHoraFinal, Date dataHoraAtualizacao, boolean ativo, boolean validaEstorno) {
        this.codigo = codigo;
        this.nome = nome;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFinal = dataHoraFinal;
        this.dataHoraAtualizacao = dataHoraAtualizacao;
        this.ativo = ativo;
        this.validaEstorno = validaEstorno;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(Date dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public Date getDataHoraFinal() {
        return dataHoraFinal;
    }

    public void setDataHoraFinal(Date dataHoraFinal) {
        this.dataHoraFinal = dataHoraFinal;
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

    public boolean getValidaEstorno() {
        return validaEstorno;
    }

    public void setValidaEstorno(boolean validaEstorno) {
        this.validaEstorno = validaEstorno;
    }

    @XmlTransient
    public Collection<LiberacaoEmergencial> getLiberacaoEmergencialCollection() {
        return liberacaoEmergencialCollection;
    }

    public void setLiberacaoEmergencialCollection(Collection<LiberacaoEmergencial> liberacaoEmergencialCollection) {
        this.liberacaoEmergencialCollection = liberacaoEmergencialCollection;
    }

    @XmlTransient
    public Collection<IngressoVendido> getIngressoVendidoCollection() {
        return ingressoVendidoCollection;
    }

    public void setIngressoVendidoCollection(Collection<IngressoVendido> ingressoVendidoCollection) {
        this.ingressoVendidoCollection = ingressoVendidoCollection;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
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
        if (!(object instanceof Apresentacao)) {
            return false;
        }
        Apresentacao other = (Apresentacao) object;
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
