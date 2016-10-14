package com.compreingressos.controleacesso;

import com.compreingressos.controleacesso.TipoUsuarioPrograma;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-14T13:59:43")
@StaticMetamodel(Programa.class)
public class Programa_ { 

    public static volatile SingularAttribute<Programa, Date> dataHoraAtualizacao;
    public static volatile SingularAttribute<Programa, Integer> codigo;
    public static volatile SingularAttribute<Programa, Boolean> ativo;
    public static volatile SingularAttribute<Programa, Integer> ordemExibicao;
    public static volatile CollectionAttribute<Programa, TipoUsuarioPrograma> tipoUsuarioProgramaCollection;
    public static volatile SingularAttribute<Programa, Integer> programa;
    public static volatile SingularAttribute<Programa, String> url;
    public static volatile SingularAttribute<Programa, String> descricao;

}