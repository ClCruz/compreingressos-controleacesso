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
@Table(name = "LiberacaoEmergencial")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LiberacaoEmergencial.findAll", query = "SELECT l FROM LiberacaoEmergencial l"),
    @NamedQuery(name = "LiberacaoEmergencial.findByDataHoraEmergencia", query = "SELECT l FROM LiberacaoEmergencial l WHERE l.dataHoraEmergencia = :dataHoraEmergencia"),
    @NamedQuery(name = "LiberacaoEmergencial.findByDataHoraAtualizacao", query = "SELECT l FROM LiberacaoEmergencial l WHERE l.dataHoraAtualizacao = :dataHoraAtualizacao"),
    @NamedQuery(name = "LiberacaoEmergencial.findByCodigo", query = "SELECT l FROM LiberacaoEmergencial l WHERE l.codigo = :codigo")})
public class LiberacaoEmergencial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataHoraEmergencia")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraEmergencia;
    @Basic(optional = false)
    @Column(name = "dataHoraAtualizacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraAtualizacao;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @JoinColumn(name = "apresentacao", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Apresentacao apresentacao;

    public LiberacaoEmergencial() {
    }

    public LiberacaoEmergencial(Integer codigo) {
        this.codigo = codigo;
    }

    public LiberacaoEmergencial(Integer codigo, Date dataHoraEmergencia, Date dataHoraAtualizacao) {
        this.codigo = codigo;
        this.dataHoraEmergencia = dataHoraEmergencia;
        this.dataHoraAtualizacao = dataHoraAtualizacao;
    }

    public Date getDataHoraEmergencia() {
        return dataHoraEmergencia;
    }

    public void setDataHoraEmergencia(Date dataHoraEmergencia) {
        this.dataHoraEmergencia = dataHoraEmergencia;
    }

    public Date getDataHoraAtualizacao() {
        return dataHoraAtualizacao;
    }

    public void setDataHoraAtualizacao(Date dataHoraAtualizacao) {
        this.dataHoraAtualizacao = dataHoraAtualizacao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Apresentacao getApresentacao() {
        return apresentacao;
    }

    public void setApresentacao(Apresentacao apresentacao) {
        this.apresentacao = apresentacao;
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
        if (!(object instanceof LiberacaoEmergencial)) {
            return false;
        }
        LiberacaoEmergencial other = (LiberacaoEmergencial) object;
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
