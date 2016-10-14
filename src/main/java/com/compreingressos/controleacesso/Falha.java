/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compreingressos.controleacesso;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Intuiti 04
 */
@Entity
@Table(name = "Falha")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Falha.findAll", query = "SELECT f FROM Falha f"),
    @NamedQuery(name = "Falha.findByCodigo", query = "SELECT f FROM Falha f WHERE f.codigo = :codigo"),
    @NamedQuery(name = "Falha.findByDescricao", query = "SELECT f FROM Falha f WHERE f.descricao = :descricao"),
    @NamedQuery(name = "Falha.findBySolucao", query = "SELECT f FROM Falha f WHERE f.solucao = :solucao"),
    @NamedQuery(name = "Falha.findByDataHoraOcorrencia", query = "SELECT f FROM Falha f WHERE f.dataHoraOcorrencia = :dataHoraOcorrencia"),
    @NamedQuery(name = "Falha.findByDataHoraSolucao", query = "SELECT f FROM Falha f WHERE f.dataHoraSolucao = :dataHoraSolucao")})
public class Falha implements Serializable {

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
    @Size(max = 120)
    @Column(name = "solucao")
    private String solucao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataHoraOcorrencia")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraOcorrencia;
    @Column(name = "dataHoraSolucao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraSolucao;
    @JoinColumn(name = "catraca", referencedColumnName = "codigo")
    @ManyToOne
    private Catraca catraca;
    @JoinColumn(name = "usuarioOcorrencia", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Usuario usuarioOcorrencia;
    @JoinColumn(name = "usuarioSolucao", referencedColumnName = "codigo")
    @ManyToOne
    private Usuario usuarioSolucao;

    public Falha() {
    }

    public Falha(Integer codigo) {
        this.codigo = codigo;
    }

    public Falha(Integer codigo, String descricao, Date dataHoraOcorrencia) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.dataHoraOcorrencia = dataHoraOcorrencia;
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

    public String getSolucao() {
        return solucao;
    }

    public void setSolucao(String solucao) {
        this.solucao = solucao;
    }

    public Date getDataHoraOcorrencia() {
        return dataHoraOcorrencia;
    }

    public void setDataHoraOcorrencia(Date dataHoraOcorrencia) {
        this.dataHoraOcorrencia = dataHoraOcorrencia;
    }

    public Date getDataHoraSolucao() {
        return dataHoraSolucao;
    }

    public void setDataHoraSolucao(Date dataHoraSolucao) {
        this.dataHoraSolucao = dataHoraSolucao;
    }

    public Catraca getCatraca() {
        return catraca;
    }

    public void setCatraca(Catraca catraca) {
        this.catraca = catraca;
    }

    public Usuario getUsuarioOcorrencia() {
        return usuarioOcorrencia;
    }

    public void setUsuarioOcorrencia(Usuario usuarioOcorrencia) {
        this.usuarioOcorrencia = usuarioOcorrencia;
    }

    public Usuario getUsuarioSolucao() {
        return usuarioSolucao;
    }

    public void setUsuarioSolucao(Usuario usuarioSolucao) {
        this.usuarioSolucao = usuarioSolucao;
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
        if (!(object instanceof Falha)) {
            return false;
        }
        Falha other = (Falha) object;
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
