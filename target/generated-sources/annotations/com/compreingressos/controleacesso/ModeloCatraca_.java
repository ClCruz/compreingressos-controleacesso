package com.compreingressos.controleacesso;

import com.compreingressos.controleacesso.Catraca;
import com.compreingressos.controleacesso.Fabricante;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-14T13:59:43")
@StaticMetamodel(ModeloCatraca.class)
public class ModeloCatraca_ { 

    public static volatile SingularAttribute<ModeloCatraca, Integer> codigo;
    public static volatile SingularAttribute<ModeloCatraca, Boolean> ativo;
    public static volatile SingularAttribute<ModeloCatraca, Fabricante> fabricante;
    public static volatile CollectionAttribute<ModeloCatraca, Catraca> catracaCollection;
    public static volatile SingularAttribute<ModeloCatraca, String> descricao;

}