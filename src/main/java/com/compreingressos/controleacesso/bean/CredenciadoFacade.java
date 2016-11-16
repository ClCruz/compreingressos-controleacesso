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

import com.compreingressos.controleacesso.Credenciado;
import com.compreingressos.controleacesso.Credencial;

/**
 *
 * @author Intuiti 04
 */
@Stateless
public class CredenciadoFacade extends AbstractFacade<Credenciado> {

    @PersistenceContext(unitName = "com.compreingressos_controleacesso_war_1.0.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CredenciadoFacade() {
        super(Credenciado.class);
    }
    
    @SuppressWarnings("unchecked")
	public List<Credenciado> findAll(Credencial credencial) {
    	return em.createNamedQuery("Credenciado.findByCredencial").setParameter("codigo", credencial).getResultList();
	}
    
    public Credenciado update(Credenciado entity){
    	Credenciado c = (Credenciado) getEntityManager().merge(entity);
    	/*getEntityManager().flush();*/
    	return c;
    }
}
