/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compreingressos.controleacesso.bean;

import com.compreingressos.controleacesso.Regiao;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    
}
