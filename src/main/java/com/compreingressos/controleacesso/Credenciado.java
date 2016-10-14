/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compreingressos.controleacesso;

import com.compreingressos.controleacesso.interfaces.Cpf;
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
import javax.persistence.Lob;
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
@Table(name = "Credenciado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Credenciado.findAll", query = "SELECT c FROM Credenciado c"),
    @NamedQuery(name = "Credenciado.findByCodigo", query = "SELECT c FROM Credenciado c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "Credenciado.findByNome", query = "SELECT c FROM Credenciado c WHERE c.nome = :nome"),
    @NamedQuery(name = "Credenciado.findByCpf", query = "SELECT c FROM Credenciado c WHERE c.cpf = :cpf"),
    @NamedQuery(name = "Credenciado.findBySobrenome", query = "SELECT c FROM Credenciado c WHERE c.sobrenome = :sobrenome"),
    @NamedQuery(name = "Credenciado.findByEndereco", query = "SELECT c FROM Credenciado c WHERE c.endereco = :endereco"),
    @NamedQuery(name = "Credenciado.findByCep", query = "SELECT c FROM Credenciado c WHERE c.cep = :cep"),
    @NamedQuery(name = "Credenciado.findByEmail", query = "SELECT c FROM Credenciado c WHERE c.email = :email"),
    @NamedQuery(name = "Credenciado.findByObservacao", query = "SELECT c FROM Credenciado c WHERE c.observacao = :observacao"),
    @NamedQuery(name = "Credenciado.findByNomeImpressao", query = "SELECT c FROM Credenciado c WHERE c.nomeImpressao = :nomeImpressao"),
    @NamedQuery(name = "Credenciado.findByTelefone", query = "SELECT c FROM Credenciado c WHERE c.telefone = :telefone"),
    @NamedQuery(name = "Credenciado.findByTelefone2", query = "SELECT c FROM Credenciado c WHERE c.telefone2 = :telefone2"),
    @NamedQuery(name = "Credenciado.findByDataHoraAtualizacao", query = "SELECT c FROM Credenciado c WHERE c.dataHoraAtualizacao = :dataHoraAtualizacao"),
    @NamedQuery(name = "Credenciado.findByAtivo", query = "SELECT c FROM Credenciado c WHERE c.ativo = :ativo"),
    @NamedQuery(name = "Credenciado.findByNumeroEndereco", query = "SELECT c FROM Credenciado c WHERE c.numeroEndereco = :numeroEndereco"),
    @NamedQuery(name = "Credenciado.findByBairro", query = "SELECT c FROM Credenciado c WHERE c.bairro = :bairro"),
    @NamedQuery(name = "Credenciado.findByComplementoEndereco", query = "SELECT c FROM Credenciado c WHERE c.complementoEndereco = :complementoEndereco")})
public class Credenciado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "cpf")
    @Cpf
    private String cpf;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "sobrenome")
    private String sobrenome;
    @Size(max = 120)
    @Column(name = "endereco")
    private String endereco;
    @Size(max = 8)
    @Column(name = "cep")
    private String cep;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 120)
    @Column(name = "email")
    private String email;
    @Size(max = 120)
    @Column(name = "observacao")
    private String observacao;
    @Size(max = 15)
    @Column(name = "nomeImpressao")
    private String nomeImpressao;
    @Size(max = 15)
    @Column(name = "telefone")
    private String telefone;
    @Size(max = 15)
    @Column(name = "telefone2")
    private String telefone2;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataHoraAtualizacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraAtualizacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ativo")
    private boolean ativo = true;
    @Lob
    @Column(name = "foto")
    private String foto;
    @Size(max = 15)
    @Column(name = "numeroEndereco")
    private String numeroEndereco;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "bairro")
    private String bairro;
    @Size(max = 20)
    @Column(name = "complementoEndereco")
    private String complementoEndereco;
    @OneToMany(mappedBy = "credenciado")
    private Collection<AcessoCatraca> acessoCatracaCollection;
    @JoinColumn(name = "empresa", referencedColumnName = "codigo")
    @ManyToOne
    private Contratante empresa;
    @JoinColumn(name = "municipio", referencedColumnName = "codigo")
    @ManyToOne
    private Municipio municipio;

    public Credenciado() {
    }

    public Credenciado(Integer codigo) {
        this.codigo = codigo;
    }

    public Credenciado(Integer codigo, String nome, String cpf, String sobrenome, Date dataHoraAtualizacao, boolean ativo, String bairro) {
        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.sobrenome = sobrenome;
        this.dataHoraAtualizacao = dataHoraAtualizacao;
        this.ativo = ativo;
        this.bairro = bairro;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep.replace("-", "");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getNomeImpressao() {
        return nomeImpressao;
    }

    public void setNomeImpressao(String nomeImpressao) {
        this.nomeImpressao = nomeImpressao;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNumeroEndereco() {
        return numeroEndereco;
    }

    public void setNumeroEndereco(String numeroEndereco) {
        this.numeroEndereco = numeroEndereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplementoEndereco() {
        return complementoEndereco;
    }

    public void setComplementoEndereco(String complementoEndereco) {
        this.complementoEndereco = complementoEndereco;
    }

    @XmlTransient
    public Collection<AcessoCatraca> getAcessoCatracaCollection() {
        return acessoCatracaCollection;
    }

    public void setAcessoCatracaCollection(Collection<AcessoCatraca> acessoCatracaCollection) {
        this.acessoCatracaCollection = acessoCatracaCollection;
    }

    public Contratante getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Contratante empresa) {
        this.empresa = empresa;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
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
        if (!(object instanceof Credenciado)) {
            return false;
        }
        Credenciado other = (Credenciado) object;
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
