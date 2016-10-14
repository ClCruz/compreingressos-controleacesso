package com.compreingressos.controleacesso;

import com.compreingressos.controleacesso.ModeloCatraca;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-14T13:59:43")
@StaticMetamodel(Fabricante.class)
public class Fabricante_ { 

    public static volatile SingularAttribute<Fabricante, Integer> codigo;
    public static volatile SingularAttribute<Fabricante, Boolean> ativo;
    public static volatile SingularAttribute<Fabricante, String> nome;
    public static volatile CollectionAttribute<Fabricante, ModeloCatraca> modeloCatracaCollection;

}