package com.compreingressos.controleacesso.filters;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.security.SecurityContextAssociation;

import com.compreingressos.controleacesso.Usuario;
import com.compreingressos.controleacesso.bean.UsuarioFacade;

public class Login implements Filter{
	@EJB
	private UsuarioFacade ejbUsuario;
	private Usuario usuario;
	
	public UsuarioFacade getFacede(){
		return ejbUsuario;
	}
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		
		String userName = SecurityContextAssociation.getPrincipal().getName();
		usuario = getFacede().findUsuario(userName);
		HttpServletRequest req = (HttpServletRequest) arg0;
		HttpServletResponse resp = (HttpServletResponse) arg1;
		req.getSession().setAttribute("usuario", usuario);
		arg2.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		usuario = new Usuario();
	}


	// GETTERS E SETTERS
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
