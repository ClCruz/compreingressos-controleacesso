package com.compreingressos.controleacesso;

import com.compreingressos.controleacesso.Evento;
import com.compreingressos.controleacesso.IngressoVendido;
import com.compreingressos.controleacesso.LiberacaoEmergencial;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-14T13:59:43")
@StaticMetamodel(Apresentacao.class)
public class Apresentacao_ { 

    public static volatile SingularAttribute<Apresentacao, Date> dataHoraAtualizacao;
    public static volatile SingularAttribute<Apresentacao, Integer> codigo;
    public static volatile SingularAttribute<Apresentacao, Boolean> ativo;
    public static volatile SingularAttribute<Apresentacao, Evento> evento;
    public static volatile SingularAttribute<Apresentacao, Date> dataHoraInicio;
    public static volatile SingularAttribute<Apresentacao, String> nome;
    public static volatile SingularAttribute<Apresentacao, Date> dataHoraFinal;
    public static volatile SingularAttribute<Apresentacao, Boolean> validaEstorno;
    public static volatile CollectionAttribute<Apresentacao, IngressoVendido> ingressoVendidoCollection;
    public static volatile CollectionAttribute<Apresentacao, LiberacaoEmergencial> liberacaoEmergencialCollection;

}