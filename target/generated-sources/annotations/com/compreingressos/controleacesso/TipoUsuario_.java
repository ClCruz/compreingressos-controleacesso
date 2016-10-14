package com.compreingressos.controleacesso;

import com.compreingressos.controleacesso.TipoUsuarioPrograma;
import com.compreingressos.controleacesso.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-14T13:59:43")
@StaticMetamodel(TipoUsuario.class)
public class TipoUsuario_ { 

    public static volatile SingularAttribute<TipoUsuario, Date> dataHoraAtualizacao;
    public static volatile SingularAttribute<TipoUsuario, Integer> codigo;
    public static volatile CollectionAttribute<TipoUsuario, TipoUsuarioPrograma> tipoUsuarioProgramaCollection;
    public static volatile SingularAttribute<TipoUsuario, String> descricaoTipoUsuario;
    public static volatile SingularAttribute<TipoUsuario, Boolean> inAdmin;
    public static volatile CollectionAttribute<TipoUsuario, Usuario> usuarioCollection;
    public static volatile SingularAttribute<TipoUsuario, Boolean> status;

}