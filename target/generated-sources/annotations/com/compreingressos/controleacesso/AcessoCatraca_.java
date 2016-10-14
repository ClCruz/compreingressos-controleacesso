package com.compreingressos.controleacesso;

import com.compreingressos.controleacesso.Catraca;
import com.compreingressos.controleacesso.Credenciado;
import com.compreingressos.controleacesso.IngressoVendido;
import com.compreingressos.controleacesso.Setor;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-14T13:59:43")
@StaticMetamodel(AcessoCatraca.class)
public class AcessoCatraca_ { 

    public static volatile SingularAttribute<AcessoCatraca, IngressoVendido> ingressoVendido;
    public static volatile SingularAttribute<AcessoCatraca, Setor> setor;
    public static volatile SingularAttribute<AcessoCatraca, Integer> codigo;
    public static volatile SingularAttribute<AcessoCatraca, Boolean> acesso;
    public static volatile SingularAttribute<AcessoCatraca, Catraca> catraca;
    public static volatile SingularAttribute<AcessoCatraca, Credenciado> credenciado;
    public static volatile SingularAttribute<AcessoCatraca, Date> dataHoraAcesso;

}