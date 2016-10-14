package com.compreingressos.controleacesso;

import com.compreingressos.controleacesso.Apresentacao;
import com.compreingressos.controleacesso.Layout;
import com.compreingressos.controleacesso.LocalDeControle;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-14T13:59:43")
@StaticMetamodel(Evento.class)
public class Evento_ { 

    public static volatile SingularAttribute<Evento, Layout> layout;
    public static volatile SingularAttribute<Evento, Integer> codigo;
    public static volatile SingularAttribute<Evento, Boolean> ativo;
    public static volatile SingularAttribute<Evento, Date> dataAtualizacao;
    public static volatile CollectionAttribute<Evento, Apresentacao> apresentacaoCollection;
    public static volatile SingularAttribute<Evento, String> logotipo;
    public static volatile SingularAttribute<Evento, LocalDeControle> localDeControle;
    public static volatile SingularAttribute<Evento, Date> dataInicio;
    public static volatile SingularAttribute<Evento, String> descricaoNome;
    public static volatile SingularAttribute<Evento, Date> dataFinal;

}