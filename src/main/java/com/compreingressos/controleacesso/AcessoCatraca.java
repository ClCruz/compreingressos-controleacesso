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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Intuiti 04
 */
@Entity
@Table(name = "AcessoCatraca")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AcessoCatraca.findAll", query = "SELECT a FROM AcessoCatraca a"),
    @NamedQuery(name = "AcessoCatraca.findByDataHoraAcesso", query = "SELECT a FROM AcessoCatraca a WHERE a.dataHoraAcesso = :dataHoraAcesso"),
    @NamedQuery(name = "AcessoCatraca.findByAcesso", query = "SELECT a FROM AcessoCatraca a WHERE a.acesso = :acesso"),
    @NamedQuery(name = "AcessoCatraca.findByCodigo", query = "SELECT a FROM AcessoCatraca a WHERE a.codigo = :codigo")})
public class AcessoCatraca implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataHoraAcesso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraAcesso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "acesso")
    private boolean acesso;
    @Id
    @GeneratedValue(strategy = IDENTITY) 
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @JoinColumn(name = "catraca", referencedColumnName = "codigo")
    @ManyToOne
    private Catraca catraca;
    @JoinColumn(name = "credenciado", referencedColumnName = "codigo")
    @ManyToOne
    private Credenciado credenciado;
    @JoinColumn(name = "ingressoVendido", referencedColumnName = "codigo")
    @ManyToOne
    private IngressoVendido ingressoVendido;
    @JoinColumn(name = "setor", referencedColumnName = "codigo")
    @ManyToOne
    private Setor setor;

    public AcessoCatraca() {
    }

    public AcessoCatraca(Integer codigo) {
        this.codigo = codigo;
    }

    public AcessoCatraca(Integer codigo, Date dataHoraAcesso, boolean acesso) {
        this.codigo = codigo;
        this.dataHoraAcesso = dataHoraAcesso;
        this.acesso = acesso;
    }

    public Date getDataHoraAcesso() {
        return dataHoraAcesso;
    }

    public void setDataHoraAcesso(Date dataHoraAcesso) {
        this.dataHoraAcesso = dataHoraAcesso;
    }

    public boolean getAcesso() {
        return acesso;
    }

    public void setAcesso(boolean acesso) {
        this.acesso = acesso;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Catraca getCatraca() {
        return catraca;
    }

    public void setCatraca(Catraca catraca) {
        this.catraca = catraca;
    }

    public Credenciado getCredenciado() {
        return credenciado;
    }

    public void setCredenciado(Credenciado credenciado) {
        this.credenciado = credenciado;
    }

    public IngressoVendido getIngressoVendido() {
        return ingressoVendido;
    }

    public void setIngressoVendido(IngressoVendido ingressoVendido) {
        this.ingressoVendido = ingressoVendido;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
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
        if (!(object instanceof AcessoCatraca)) {
            return false;
        }
        AcessoCatraca other = (AcessoCatraca) object;
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
