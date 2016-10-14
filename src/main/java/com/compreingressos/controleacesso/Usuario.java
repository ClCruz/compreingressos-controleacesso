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
import javax.persistence.CascadeType;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Intuiti 04
 */
@Entity
@Table(name = "Usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByCodigo", query = "SELECT u FROM Usuario u WHERE u.codigo = :codigo"),
    @NamedQuery(name = "Usuario.findByNome", query = "SELECT u FROM Usuario u WHERE u.nome = :nome"),
    @NamedQuery(name = "Usuario.findBySobrenome", query = "SELECT u FROM Usuario u WHERE u.sobrenome = :sobrenome"),
    @NamedQuery(name = "Usuario.findByCpf", query = "SELECT u FROM Usuario u WHERE u.cpf = :cpf"),
    @NamedQuery(name = "Usuario.findByEndereco", query = "SELECT u FROM Usuario u WHERE u.endereco = :endereco"),
    @NamedQuery(name = "Usuario.findByCep", query = "SELECT u FROM Usuario u WHERE u.cep = :cep"),
    @NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email = :email"),
    @NamedQuery(name = "Usuario.findBySenha", query = "SELECT u FROM Usuario u WHERE u.senha = :senha"),
    @NamedQuery(name = "Usuario.findByTelefone", query = "SELECT u FROM Usuario u WHERE u.telefone = :telefone"),
    @NamedQuery(name = "Usuario.findByTelefone2", query = "SELECT u FROM Usuario u WHERE u.telefone2 = :telefone2"),
    @NamedQuery(name = "Usuario.findByObservacao", query = "SELECT u FROM Usuario u WHERE u.observacao = :observacao"),
    @NamedQuery(name = "Usuario.findByDataHoraAtualizacao", query = "SELECT u FROM Usuario u WHERE u.dataHoraAtualizacao = :dataHoraAtualizacao"),
    @NamedQuery(name = "Usuario.findByAtivo", query = "SELECT u FROM Usuario u WHERE u.ativo = :ativo"),
    @NamedQuery(name = "Usuario.findByNumeroEndereco", query = "SELECT u FROM Usuario u WHERE u.numeroEndereco = :numeroEndereco"),
    @NamedQuery(name = "Usuario.findByComplementoEndereco", query = "SELECT u FROM Usuario u WHERE u.complementoEndereco = :complementoEndereco"),
    @NamedQuery(name = "Usuario.findByBairro", query = "SELECT u FROM Usuario u WHERE u.bairro = :bairro")})
public class Usuario implements Serializable {

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
    @Size(min = 1, max = 20)
    @Column(name = "sobrenome")
    private String sobrenome;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cpf")
    private long cpf;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "endereco")
    private String endereco;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "cep")
    private String cep;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "senha")
    private String senha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "telefone")
    private String telefone;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "telefone2")
    private String telefone2;
    @Size(max = 120)
    @Column(name = "observacao")
    private String observacao;
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
    @Size(min = 1, max = 20)
    @Column(name = "numeroEndereco")
    private String numeroEndereco;
    @Size(max = 20)
    @Column(name = "complementoEndereco")
    private String complementoEndereco;
    @Size(max = 30)
    @Column(name = "bairro")
    private String bairro;
    @NotNull
    @Lob
    @Column(name = "foto")
    private String foto;
    @JoinColumn(name = "municipio", referencedColumnName = "codigo")
    @ManyToOne
    private Municipio municipio;
    @JoinColumn(name = "tipoUsuario", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private TipoUsuario tipoUsuario;
    @OneToMany(mappedBy = "usuario")
    private Collection<Regiao> regiaoCollection;
    @OneToMany(mappedBy = "usuario")
    private Collection<Estado> estadoCollection;
    @OneToMany(mappedBy = "usuario")
    private Collection<Pais> paisCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioOcorrencia")
    private Collection<Falha> falhaCollection;
    @OneToMany(mappedBy = "usuarioSolucao")
    private Collection<Falha> falhaCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Collection<UsuarioContratante> usuarioContratanteCollection;

    public Usuario() {
    }

    public Usuario(Integer codigo) {
        this.codigo = codigo;
    }

    public Usuario(Integer codigo, String nome, String sobrenome, long cpf, String endereco, String cep, String email, String senha, String telefone, String telefone2, Date dataHoraAtualizacao, boolean ativo, String numeroEndereco, String foto) {
        this.codigo = codigo;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.cep = cep;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.telefone2 = telefone2;
        this.dataHoraAtualizacao = dataHoraAtualizacao;
        this.ativo = ativo;
        this.numeroEndereco = numeroEndereco;
        this.foto = foto;
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

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
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

    public String getNumeroEndereco() {
        return numeroEndereco;
    }

    public void setNumeroEndereco(String numeroEndereco) {
        this.numeroEndereco = numeroEndereco;
    }

    public String getComplementoEndereco() {
        return complementoEndereco;
    }

    public void setComplementoEndereco(String complementoEndereco) {
        this.complementoEndereco = complementoEndereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    
    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @XmlTransient
    public Collection<Regiao> getRegiaoCollection() {
        return regiaoCollection;
    }

    public void setRegiaoCollection(Collection<Regiao> regiaoCollection) {
        this.regiaoCollection = regiaoCollection;
    }

    @XmlTransient
    public Collection<Estado> getEstadoCollection() {
        return estadoCollection;
    }

    public void setEstadoCollection(Collection<Estado> estadoCollection) {
        this.estadoCollection = estadoCollection;
    }

    @XmlTransient
    public Collection<Pais> getPaisCollection() {
        return paisCollection;
    }

    public void setPaisCollection(Collection<Pais> paisCollection) {
        this.paisCollection = paisCollection;
    }

    @XmlTransient
    public Collection<Falha> getFalhaCollection() {
        return falhaCollection;
    }

    public void setFalhaCollection(Collection<Falha> falhaCollection) {
        this.falhaCollection = falhaCollection;
    }

    @XmlTransient
    public Collection<Falha> getFalhaCollection1() {
        return falhaCollection1;
    }

    public void setFalhaCollection1(Collection<Falha> falhaCollection1) {
        this.falhaCollection1 = falhaCollection1;
    }

    @XmlTransient
    public Collection<UsuarioContratante> getUsuarioContratanteCollection() {
        return usuarioContratanteCollection;
    }

    public void setUsuarioContratanteCollection(Collection<UsuarioContratante> usuarioContratanteCollection) {
        this.usuarioContratanteCollection = usuarioContratanteCollection;
    }

    @Transient
    public String caminho;

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nome;
    }

    public Usuario find(Long valueOf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
