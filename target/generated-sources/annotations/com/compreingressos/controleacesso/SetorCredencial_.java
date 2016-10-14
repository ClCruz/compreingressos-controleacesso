package com.compreingressos.controleacesso;

import com.compreingressos.controleacesso.Setor;
import com.compreingressos.controleacesso.TipoCredencial;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-14T13:59:43")
@StaticMetamodel(SetorCredencial.class)
public class SetorCredencial_ { 

    public static volatile SingularAttribute<SetorCredencial, TipoCredencial> tipoCredencial;
    public static volatile SingularAttribute<SetorCredencial, Date> dataHoraAtualizacao;
    public static volatile SingularAttribute<SetorCredencial, Setor> setor;
    public static volatile SingularAttribute<SetorCredencial, Boolean> ativo;
    public static volatile SingularAttribute<SetorCredencial, Integer> codigo;

}