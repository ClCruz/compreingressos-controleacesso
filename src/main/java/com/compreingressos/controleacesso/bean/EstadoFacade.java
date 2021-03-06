/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compreingressos.controleacesso.bean;

import java.util.List;

import com.compreingressos.controleacesso.Estado;
import com.compreingressos.controleacesso.Pais;
import com.compreingressos.controleacesso.Regiao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Intuiti 04
 */
@Stateless
public class EstadoFacade extends AbstractFacade<Estado> {

    @PersistenceContext(unitName = "com.compreingressos_controleacesso_war_1.0.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadoFacade() {
        super(Estado.class);
    }

	public boolean findEstado(String nome, String uf) {
		List<Estado> lista = em.createNamedQuery("Estado.findEstado").setParameter("nome", nome).setParameter("uf", uf).getResultList();
    	return lista.size() > 0 ? false : true;
	}
    
}
