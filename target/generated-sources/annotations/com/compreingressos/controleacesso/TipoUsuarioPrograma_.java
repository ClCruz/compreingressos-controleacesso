package com.compreingressos.controleacesso;

import com.compreingressos.controleacesso.Programa;
import com.compreingressos.controleacesso.TipoUsuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-14T13:59:43")
@StaticMetamodel(TipoUsuarioPrograma.class)
public class TipoUsuarioPrograma_ { 

    public static volatile SingularAttribute<TipoUsuarioPrograma, Date> dataHoraAtualizacao;
    public static volatile SingularAttribute<TipoUsuarioPrograma, Integer> codigo;
    public static volatile SingularAttribute<TipoUsuarioPrograma, Programa> programa;
    public static volatile SingularAttribute<TipoUsuarioPrograma, TipoUsuario> tipoUsuario;

}