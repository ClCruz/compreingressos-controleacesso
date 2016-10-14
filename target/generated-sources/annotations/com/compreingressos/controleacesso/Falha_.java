package com.compreingressos.controleacesso;

import com.compreingressos.controleacesso.Catraca;
import com.compreingressos.controleacesso.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-14T13:59:42")
@StaticMetamodel(Falha.class)
public class Falha_ { 

    public static volatile SingularAttribute<Falha, Integer> codigo;
    public static volatile SingularAttribute<Falha, String> solucao;
    public static volatile SingularAttribute<Falha, Catraca> catraca;
    public static volatile SingularAttribute<Falha, Date> dataHoraSolucao;
    public static volatile SingularAttribute<Falha, Date> dataHoraOcorrencia;
    public static volatile SingularAttribute<Falha, Usuario> usuarioOcorrencia;
    public static volatile SingularAttribute<Falha, String> descricao;
    public static volatile SingularAttribute<Falha, Usuario> usuarioSolucao;

}