package com.compreingressos.controleacesso;

import com.compreingressos.controleacesso.AcessoCatraca;
import com.compreingressos.controleacesso.CatracaSetor;
import com.compreingressos.controleacesso.Contratante;
import com.compreingressos.controleacesso.Falha;
import com.compreingressos.controleacesso.ModeloCatraca;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-14T13:59:43")
@StaticMetamodel(Catraca.class)
public class Catraca_ { 

    public static volatile SingularAttribute<Catraca, Date> dataHoraAtualizacao;
    public static volatile SingularAttribute<Catraca, Integer> codigo;
    public static volatile SingularAttribute<Catraca, Boolean> ativo;
    public static volatile CollectionAttribute<Catraca, CatracaSetor> catracaSetorCollection;
    public static volatile SingularAttribute<Catraca, Contratante> contratante;
    public static volatile SingularAttribute<Catraca, String> codigoCatraca;
    public static volatile SingularAttribute<Catraca, String> numeroIP;
    public static volatile SingularAttribute<Catraca, ModeloCatraca> modeloCatraca;
    public static volatile CollectionAttribute<Catraca, AcessoCatraca> acessoCatracaCollection;
    public static volatile CollectionAttribute<Catraca, Falha> falhaCollection;

}