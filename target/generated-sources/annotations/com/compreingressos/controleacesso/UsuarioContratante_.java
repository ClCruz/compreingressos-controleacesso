package com.compreingressos.controleacesso;

import com.compreingressos.controleacesso.Contratante;
import com.compreingressos.controleacesso.Usuario;
import com.compreingressos.controleacesso.UsuarioContratantePK;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-14T13:59:43")
@StaticMetamodel(UsuarioContratante.class)
public class UsuarioContratante_ { 

    public static volatile SingularAttribute<UsuarioContratante, UsuarioContratantePK> usuarioContratantePK;
    public static volatile SingularAttribute<UsuarioContratante, Date> dataHoraAtualizacao;
    public static volatile SingularAttribute<UsuarioContratante, Contratante> contratante;
    public static volatile SingularAttribute<UsuarioContratante, Usuario> usuario;

}