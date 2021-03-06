/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compreingressos.controleacesso.bean;

import java.util.List;

import com.compreingressos.controleacesso.Catraca;
import com.compreingressos.controleacesso.CatracaSetor;
import com.compreingressos.controleacesso.Estado;
import com.compreingressos.controleacesso.Setor;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Intuiti 04
 */
@Stateless
public class CatracaSetorFacade extends AbstractFacade<CatracaSetor> {

    @PersistenceContext(unitName = "com.compreingressos_controleacesso_war_1.0.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CatracaSetorFacade() {
        super(CatracaSetor.class);
    }

	@SuppressWarnings("unchecked")
	public List<CatracaSetor> findAll(Catraca catraca) {
		return em.createNamedQuery("CatracaSetor.findByCatraca").setParameter("codigo", catraca).getResultList();
	}
	
	public CatracaSetor update(CatracaSetor entity){
		return (CatracaSetor)getEntityManager().merge(entity);
	}
    
}
