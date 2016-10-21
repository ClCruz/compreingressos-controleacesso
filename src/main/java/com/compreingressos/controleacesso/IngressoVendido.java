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
import javax.persistence.OneToOne;
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
@Table(name = "IngressoVendido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IngressoVendido.findAll", query = "SELECT i FROM IngressoVendido i"),
    @NamedQuery(name = "IngressoVendido.findByIngressoVendido", query = "SELECT i FROM IngressoVendido i WHERE i.ingressoVendido = :ingressoVendido"),
    @NamedQuery(name = "IngressoVendido.findByDataHoraAtualizacao", query = "SELECT i FROM IngressoVendido i WHERE i.dataHoraAtualizacao = :dataHoraAtualizacao"),
    @NamedQuery(name = "IngressoVendido.findByQtPassagens", query = "SELECT i FROM IngressoVendido i WHERE i.qtPassagens = :qtPassagens"),
    @NamedQuery(name = "IngressoVendido.findByCodigo", query = "SELECT i FROM IngressoVendido i WHERE i.codigo = :codigo")})
public class IngressoVendido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ingressoVendido")
    private String ingressoVendido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataHoraAtualizacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraAtualizacao;
    @Column(name = "qtPassagens")
    private Short qtPassagens;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Long codigo;
    @OneToOne(mappedBy = "ingressoVendido")
    private IngressoInvalido ingressoInvalido;
    @OneToMany(mappedBy = "ingressoVendido")
    private Collection<AcessoCatraca> acessoCatracaCollection;
    @JoinColumn(name = "apresentacao", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Apresentacao apresentacao;
    @JoinColumn(name = "setor", referencedColumnName = "codigo")
    @ManyToOne
    private Setor setor;

    public IngressoVendido() {
    }

    public IngressoVendido(Long codigo) {
        this.codigo = codigo;
    }

    public IngressoVendido(Long codigo, String ingressoVendido, Date dataHoraAtualizacao) {
        this.codigo = codigo;
        this.ingressoVendido = ingressoVendido;
        this.dataHoraAtualizacao = dataHoraAtualizacao;
    }

    public String getIngressoVendido() {
        return ingressoVendido;
    }

    public void setIngressoVendido(String ingressoVendido) {
        this.ingressoVendido = ingressoVendido;
    }

    public Date getDataHoraAtualizacao() {
        return dataHoraAtualizacao;
    }

    public void setDataHoraAtualizacao(Date dataHoraAtualizacao) {
        this.dataHoraAtualizacao = dataHoraAtualizacao;
    }

    public Short getQtPassagens() {
        return qtPassagens;
    }

    public void setQtPassagens(Short qtPassagens) {
        this.qtPassagens = qtPassagens;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public IngressoInvalido getIngressoInvalido() {
		return ingressoInvalido;
	}
    
    public void setIngressoInvalido(IngressoInvalido ingressoInvalido) {
		this.ingressoInvalido = ingressoInvalido;
	}
    
    @XmlTransient
    public Collection<AcessoCatraca> getAcessoCatracaCollection() {
        return acessoCatracaCollection;
    }

    public void setAcessoCatracaCollection(Collection<AcessoCatraca> acessoCatracaCollection) {
        this.acessoCatracaCollection = acessoCatracaCollection;
    }

    public Apresentacao getApresentacao() {
        return apresentacao;
    }

    public void setApresentacao(Apresentacao apresentacao) {
        this.apresentacao = apresentacao;
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
        if (!(object instanceof IngressoVendido)) {
            return false;
        }
        IngressoVendido other = (IngressoVendido) object;
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
