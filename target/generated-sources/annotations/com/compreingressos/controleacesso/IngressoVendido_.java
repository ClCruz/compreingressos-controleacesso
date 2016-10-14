package com.compreingressos.controleacesso;

import com.compreingressos.controleacesso.AcessoCatraca;
import com.compreingressos.controleacesso.Apresentacao;
import com.compreingressos.controleacesso.IngressoInvalido;
import com.compreingressos.controleacesso.Setor;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-14T13:59:43")
@StaticMetamodel(IngressoVendido.class)
public class IngressoVendido_ { 

    public static volatile SingularAttribute<IngressoVendido, String> ingressoVendido;
    public static volatile SingularAttribute<IngressoVendido, Short> qtPassagens;
    public static volatile SingularAttribute<IngressoVendido, Date> dataHoraAtualizacao;
    public static volatile SingularAttribute<IngressoVendido, Setor> setor;
    public static volatile SingularAttribute<IngressoVendido, Long> codigo;
    public static volatile CollectionAttribute<IngressoVendido, IngressoInvalido> ingressoInvalido;
    public static volatile SingularAttribute<IngressoVendido, Apresentacao> apresentacao;
    public static volatile CollectionAttribute<IngressoVendido, AcessoCatraca> acessoCatracaCollection;

}