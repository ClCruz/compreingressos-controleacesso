package com.compreingressos.controleacesso;

import com.compreingressos.controleacesso.Estado;
import com.compreingressos.controleacesso.Falha;
import com.compreingressos.controleacesso.Municipio;
import com.compreingressos.controleacesso.Pais;
import com.compreingressos.controleacesso.Regiao;
import com.compreingressos.controleacesso.TipoUsuario;
import com.compreingressos.controleacesso.UsuarioContratante;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-14T13:59:43")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile CollectionAttribute<Usuario, Regiao> regiaoCollection;
    public static volatile SingularAttribute<Usuario, String> telefone;
    public static volatile SingularAttribute<Usuario, String> observacao;
    public static volatile SingularAttribute<Usuario, Boolean> ativo;
    public static volatile SingularAttribute<Usuario, String> complementoEndereco;
    public static volatile CollectionAttribute<Usuario, UsuarioContratante> usuarioContratanteCollection;
    public static volatile SingularAttribute<Usuario, String> cep;
    public static volatile SingularAttribute<Usuario, String> senha;
    public static volatile SingularAttribute<Usuario, Date> dataHoraAtualizacao;
    public static volatile CollectionAttribute<Usuario, Pais> paisCollection;
    public static volatile SingularAttribute<Usuario, Long> cpf;
    public static volatile SingularAttribute<Usuario, String> sobrenome;
    public static volatile SingularAttribute<Usuario, String> email;
    public static volatile CollectionAttribute<Usuario, Falha> falhaCollection;
    public static volatile SingularAttribute<Usuario, Integer> codigo;
    public static volatile SingularAttribute<Usuario, String> endereco;
    public static volatile SingularAttribute<Usuario, String> bairro;
    public static volatile SingularAttribute<Usuario, Municipio> municipio;
    public static volatile SingularAttribute<Usuario, String> nome;
    public static volatile SingularAttribute<Usuario, String> telefone2;
    public static volatile CollectionAttribute<Usuario, Falha> falhaCollection1;
    public static volatile SingularAttribute<Usuario, String> numeroEndereco;
    public static volatile CollectionAttribute<Usuario, Estado> estadoCollection;
    public static volatile SingularAttribute<Usuario, String> foto;
    public static volatile SingularAttribute<Usuario, TipoUsuario> tipoUsuario;

}