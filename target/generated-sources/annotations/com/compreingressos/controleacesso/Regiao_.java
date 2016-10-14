package com.compreingressos.controleacesso;

import com.compreingressos.controleacesso.Estado;
import com.compreingressos.controleacesso.Pais;
import com.compreingressos.controleacesso.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-14T13:59:43")
@StaticMetamodel(Regiao.class)
public class Regiao_ { 

    public static volatile SingularAttribute<Regiao, Date> dataHoraAtualizacao;
    public static volatile SingularAttribute<Regiao, Integer> codigo;
    public static volatile CollectionAttribute<Regiao, Estado> estadoCollection;
    public static volatile SingularAttribute<Regiao, String> descricaoRegiao;
    public static volatile SingularAttribute<Regiao, Usuario> usuario;
    public static volatile SingularAttribute<Regiao, Pais> pais;

}