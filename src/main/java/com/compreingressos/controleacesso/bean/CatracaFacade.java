/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compreingressos.controleacesso.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.compreingressos.controleacesso.Catraca;
import com.compreingressos.controleacesso.Layout;
import com.compreingressos.controleacesso.LocalDeControle;
import com.compreingressos.controleacesso.Setor;

/**
 *
 * @author Intuiti 04
 */
@Stateless
public class CatracaFacade extends AbstractFacade<Catraca> {

    @PersistenceContext(unitName = "com.compreingressos_controleacesso_war_1.0.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CatracaFacade() {
        super(Catraca.class);
    }
    
    public List<Layout> findLayout(LocalDeControle localDeControler){
    	return em.createNamedQuery("LocalDeControle.findLayout", Layout.class).setParameter("localDeControle", localDeControler).getResultList();
    }
    
    public List<Setor> findSetor(Layout layout){
    	return em.createNamedQuery("Layout.findSetor", Setor.class).setParameter("layout", layout).getResultList();
    }
    
    public Catraca update(Catraca entity){
    	return (Catraca) getEntityManager().merge(entity);
    }
}
