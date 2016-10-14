package com.compreingressos.controleacesso;

import com.compreingressos.controleacesso.AcessoCatraca;
import com.compreingressos.controleacesso.Contratante;
import com.compreingressos.controleacesso.Municipio;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-14T13:59:43")
@StaticMetamodel(Credenciado.class)
public class Credenciado_ { 

    public static volatile SingularAttribute<Credenciado, Integer> codigo;
    public static volatile SingularAttribute<Credenciado, String> observacao;
    public static volatile SingularAttribute<Credenciado, String> telefone;
    public static volatile SingularAttribute<Credenciado, Boolean> ativo;
    public static volatile SingularAttribute<Credenciado, String> endereco;
    public static volatile SingularAttribute<Credenciado, String> bairro;
    public static volatile SingularAttribute<Credenciado, Municipio> municipio;
    public static volatile SingularAttribute<Credenciado, String> complementoEndereco;
    public static volatile SingularAttribute<Credenciado, String> nome;
    public static volatile SingularAttribute<Credenciado, String> nomeImpressao;
    public static volatile SingularAttribute<Credenciado, String> telefone2;
    public static volatile SingularAttribute<Credenciado, String> cep;
    public static volatile SingularAttribute<Credenciado, Date> dataHoraAtualizacao;
    public static volatile SingularAttribute<Credenciado, String> numeroEndereco;
    public static volatile SingularAttribute<Credenciado, String> foto;
    public static volatile SingularAttribute<Credenciado, String> cpf;
    public static volatile SingularAttribute<Credenciado, String> sobrenome;
    public static volatile SingularAttribute<Credenciado, Contratante> empresa;
    public static volatile SingularAttribute<Credenciado, String> email;
    public static volatile CollectionAttribute<Credenciado, AcessoCatraca> acessoCatracaCollection;

}