package com.compreingressos.controleacesso;

import com.compreingressos.controleacesso.Municipio;
import com.compreingressos.controleacesso.Regiao;
import com.compreingressos.controleacesso.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-14T13:59:43")
@StaticMetamodel(Estado.class)
public class Estado_ { 

    public static volatile CollectionAttribute<Estado, Municipio> municipioCollection;
    public static volatile SingularAttribute<Estado, String> uf;
    public static volatile SingularAttribute<Estado, Date> dataHoraAtualizacao;
    public static volatile SingularAttribute<Estado, Integer> codigo;
    public static volatile SingularAttribute<Estado, String> nome;
    public static volatile SingularAttribute<Estado, Usuario> usuario;
    public static volatile SingularAttribute<Estado, Regiao> regiao;

}