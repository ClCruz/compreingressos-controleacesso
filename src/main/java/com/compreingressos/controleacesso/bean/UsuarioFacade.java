/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compreingressos.controleacesso.bean;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.compreingressos.controleacesso.Usuario;
/**
 *
 * @author Intuiti 04
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "com.compreingressos_controleacesso_war_1.0.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade(){
        super(Usuario.class);
    }
    
    public Usuario findUsuario(String userName) {
    	return em.createNamedQuery("Usuario.findByEmail", Usuario.class).setParameter("email", userName).getSingleResult();
    }
    
}
