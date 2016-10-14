package com.compreingressos.controleacesso;

import com.compreingressos.controleacesso.IngressoVendido;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-14T13:59:43")
@StaticMetamodel(IngressoInvalido.class)
public class IngressoInvalido_ { 

    public static volatile SingularAttribute<IngressoInvalido, IngressoVendido> ingressoVendido;
    public static volatile SingularAttribute<IngressoInvalido, Date> dataHoraAtualizacao;
    public static volatile SingularAttribute<IngressoInvalido, String> motivo;

}