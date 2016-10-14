package com.compreingressos.controleacesso;

import com.compreingressos.controleacesso.Contratante;
import com.compreingressos.controleacesso.CredencialGradeHoraria;
import com.compreingressos.controleacesso.SetorCredencial;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-14T13:59:43")
@StaticMetamodel(TipoCredencial.class)
public class TipoCredencial_ { 

    public static volatile SingularAttribute<TipoCredencial, Date> dataHoraAtualizacao;
    public static volatile SingularAttribute<TipoCredencial, Integer> codigo;
    public static volatile SingularAttribute<TipoCredencial, Boolean> ativo;
    public static volatile CollectionAttribute<TipoCredencial, SetorCredencial> setorCredencialCollection;
    public static volatile SingularAttribute<TipoCredencial, Contratante> contratante;
    public static volatile CollectionAttribute<TipoCredencial, CredencialGradeHoraria> credencialGradeHorariaCollection;
    public static volatile SingularAttribute<TipoCredencial, String> descricao;

}