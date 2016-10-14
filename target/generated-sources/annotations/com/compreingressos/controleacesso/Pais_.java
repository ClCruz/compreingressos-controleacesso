package com.compreingressos.controleacesso;

import com.compreingressos.controleacesso.Regiao;
import com.compreingressos.controleacesso.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-14T13:59:43")
@StaticMetamodel(Pais.class)
public class Pais_ { 

    public static volatile SingularAttribute<Pais, Date> dataHoraAtualizacao;
    public static volatile CollectionAttribute<Pais, Regiao> regiaoCollection;
    public static volatile SingularAttribute<Pais, Integer> codigo;
    public static volatile SingularAttribute<Pais, String> descricaoPais;
    public static volatile SingularAttribute<Pais, Usuario> usuario;

}