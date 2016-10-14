package com.compreingressos.controleacesso;

import com.compreingressos.controleacesso.Evento;
import com.compreingressos.controleacesso.LocalDeControle;
import com.compreingressos.controleacesso.Setor;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-14T13:59:43")
@StaticMetamodel(Layout.class)
public class Layout_ { 

    public static volatile SingularAttribute<Layout, Date> dataHoraAtualizacao;
    public static volatile SingularAttribute<Layout, Integer> codigo;
    public static volatile SingularAttribute<Layout, Boolean> ativo;
    public static volatile CollectionAttribute<Layout, Setor> setorCollection;
    public static volatile SingularAttribute<Layout, LocalDeControle> localDeControle;
    public static volatile SingularAttribute<Layout, String> imagem;
    public static volatile SingularAttribute<Layout, String> nome;
    public static volatile CollectionAttribute<Layout, Evento> eventoCollection;

}