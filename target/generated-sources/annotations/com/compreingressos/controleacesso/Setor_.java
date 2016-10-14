package com.compreingressos.controleacesso;

import com.compreingressos.controleacesso.AcessoCatraca;
import com.compreingressos.controleacesso.CatracaSetor;
import com.compreingressos.controleacesso.IngressoVendido;
import com.compreingressos.controleacesso.Layout;
import com.compreingressos.controleacesso.SetorCredencial;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-14T13:59:43")
@StaticMetamodel(Setor.class)
public class Setor_ { 

    public static volatile SingularAttribute<Setor, Layout> layout;
    public static volatile SingularAttribute<Setor, Date> dataHoraAtualizacao;
    public static volatile SingularAttribute<Setor, Integer> codigo;
    public static volatile SingularAttribute<Setor, Boolean> ativo;
    public static volatile CollectionAttribute<Setor, SetorCredencial> setorCredencialCollection;
    public static volatile CollectionAttribute<Setor, CatracaSetor> catracaSetorCollection;
    public static volatile SingularAttribute<Setor, String> descricaoSetor;
    public static volatile SingularAttribute<Setor, Boolean> acessibilidadeEspecial;
    public static volatile CollectionAttribute<Setor, IngressoVendido> ingressoVendidoCollection;
    public static volatile CollectionAttribute<Setor, AcessoCatraca> acessoCatracaCollection;

}