package com.compreingressos.controleacesso;

import com.compreingressos.controleacesso.Contratante;
import com.compreingressos.controleacesso.Evento;
import com.compreingressos.controleacesso.Layout;
import com.compreingressos.controleacesso.Municipio;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-14T13:59:43")
@StaticMetamodel(LocalDeControle.class)
public class LocalDeControle_ { 

    public static volatile SingularAttribute<LocalDeControle, Date> dataHoraAtualizacao;
    public static volatile SingularAttribute<LocalDeControle, String> numeroEndereco;
    public static volatile SingularAttribute<LocalDeControle, Boolean> ativo;
    public static volatile SingularAttribute<LocalDeControle, Integer> codigo;
    public static volatile CollectionAttribute<LocalDeControle, Layout> layoutCollection;
    public static volatile SingularAttribute<LocalDeControle, String> complemento;
    public static volatile SingularAttribute<LocalDeControle, String> endereco;
    public static volatile SingularAttribute<LocalDeControle, String> bairro;
    public static volatile SingularAttribute<LocalDeControle, Municipio> municipio;
    public static volatile SingularAttribute<LocalDeControle, Contratante> contratante;
    public static volatile SingularAttribute<LocalDeControle, String> nome;
    public static volatile CollectionAttribute<LocalDeControle, Evento> eventoCollection;

}