package com.compreingressos.controleacesso;

import com.compreingressos.controleacesso.Catraca;
import com.compreingressos.controleacesso.Setor;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-14T13:59:43")
@StaticMetamodel(CatracaSetor.class)
public class CatracaSetor_ { 

    public static volatile SingularAttribute<CatracaSetor, Setor> setor;
    public static volatile SingularAttribute<CatracaSetor, Integer> codigo;
    public static volatile SingularAttribute<CatracaSetor, Date> dataAtualizacao;
    public static volatile SingularAttribute<CatracaSetor, Catraca> catraca;

}