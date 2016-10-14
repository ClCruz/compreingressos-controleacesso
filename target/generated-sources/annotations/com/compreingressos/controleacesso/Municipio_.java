package com.compreingressos.controleacesso;

import com.compreingressos.controleacesso.Credenciado;
import com.compreingressos.controleacesso.Estado;
import com.compreingressos.controleacesso.LocalDeControle;
import com.compreingressos.controleacesso.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-14T13:59:43")
@StaticMetamodel(Municipio.class)
public class Municipio_ { 

    public static volatile SingularAttribute<Municipio, Date> dataHoraAtualizacao;
    public static volatile CollectionAttribute<Municipio, Credenciado> credenciadoCollection;
    public static volatile SingularAttribute<Municipio, Integer> codigo;
    public static volatile SingularAttribute<Municipio, Estado> estado;
    public static volatile CollectionAttribute<Municipio, LocalDeControle> localDeControleCollection;
    public static volatile SingularAttribute<Municipio, String> nome;
    public static volatile CollectionAttribute<Municipio, Usuario> usuarioCollection;
    public static volatile SingularAttribute<Municipio, Integer> codigoMunicipio;

}