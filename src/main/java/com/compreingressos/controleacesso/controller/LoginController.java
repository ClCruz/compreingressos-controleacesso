package com.compreingressos.controleacesso.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.compreingressos.controleacesso.Usuario;


@ManagedBean(name = "loginController")
@SessionScoped	
public class LoginController {

	private Usuario usuario;
	
	public LoginController() {
	}
	 
	@PostConstruct
	public void init(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		usuario = (Usuario) session.getAttribute("usuario");
	}
	
	public String logout(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		session.setAttribute("usuario", null);
		session.invalidate();
		return "/pages/index?faces-redirect=true";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
