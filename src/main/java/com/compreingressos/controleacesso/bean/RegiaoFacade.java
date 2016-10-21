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

import com.compreingressos.controleacesso.Pais;
import com.compreingressos.controleacesso.Regiao;

/**
 *
 * @author Intuiti 04
 */
@Stateless
public class RegiaoFacade extends AbstractFacade<Regiao> {

    @PersistenceContext(unitName = "com.compreingressos_controleacesso_war_1.0.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RegiaoFacade() {
        super(Regiao.class);
    }
    
    public boolean findRegiaoPais(String regiao, Pais pais){
    	List<Regiao> lista = em.createNamedQuery("Regiao.findRegiaoPais").setParameter("descricao", regiao).setParameter("pais", pais).getResultList();
    	return lista.size() > 0 ? false : true;
    }
    
}
