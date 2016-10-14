package com.compreingressos.controleacesso;

import com.compreingressos.controleacesso.Catraca;
import com.compreingressos.controleacesso.Credenciado;
import com.compreingressos.controleacesso.LocalDeControle;
import com.compreingressos.controleacesso.TipoCredencial;
import com.compreingressos.controleacesso.UsuarioContratante;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-14T13:59:43")
@StaticMetamodel(Contratante.class)
public class Contratante_ { 

    public static volatile SingularAttribute<Contratante, Date> dataHoraAtualizacao;
    public static volatile CollectionAttribute<Contratante, Credenciado> credenciadoCollection;
    public static volatile SingularAttribute<Contratante, Integer> codigo;
    public static volatile SingularAttribute<Contratante, Boolean> ativo;
    public static volatile SingularAttribute<Contratante, String> logotipo;
    public static volatile CollectionAttribute<Contratante, TipoCredencial> tipoCredencialCollection;
    public static volatile CollectionAttribute<Contratante, LocalDeControle> localDeControleCollection;
    public static volatile SingularAttribute<Contratante, String> nome;
    public static volatile CollectionAttribute<Contratante, UsuarioContratante> usuarioContratanteCollection;
    public static volatile CollectionAttribute<Contratante, Catraca> catracaCollection;

}