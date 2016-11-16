package com.compreingressos.controleacesso.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.servlet.http.HttpSession;

import com.compreingressos.controleacesso.Regiao;
import com.compreingressos.controleacesso.Usuario;
import com.compreingressos.controleacesso.bean.RegiaoFacade;
import com.compreingressos.controleacesso.controller.util.JsfUtil;
import com.compreingressos.controleacesso.controller.util.JsfUtil.PersistAction;

@ManagedBean(name = "regiaoController")
@ViewScoped
public class RegiaoController implements Serializable {

	@EJB
	private com.compreingressos.controleacesso.bean.RegiaoFacade ejbFacade;
	private List<Regiao> items = null;
	private Regiao selected;
	private Usuario usuario;

	public RegiaoController() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext()
				.getSession(false);
		usuario = (Usuario) session.getAttribute("usuario");
	}

	public Regiao getSelected() {
		return selected;
	}

	public void setSelected(Regiao selected) {
		this.selected = selected;
	}

	protected void setEmbeddableKeys() {
	}

	protected void initializeEmbeddableKey() {
	}

	private RegiaoFacade getFacade() {
		return ejbFacade;
	}

	public Regiao prepareCreate() {
		selected = new Regiao();
		initializeEmbeddableKey();
		return selected;
	}

	public void create() {
		persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle")
				.getString("RegiaoCreated"));
		if (!JsfUtil.isValidationFailed()) {
			items = null; // Invalidate list of items to trigger re-query.
		}
	}

	public void update() {
		persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle")
				.getString("RegiaoUpdated"));
	}

	public void destroy() {
		persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle")
				.getString("RegiaoDeleted"));
		if (!JsfUtil.isValidationFailed()) {
			selected = null; // Remove selection
			items = null; // Invalidate list of items to trigger re-query.
		}
	}

	public List<Regiao> getItems() {
		if (items == null) {
			items = getFacade().findAll();
		}
		return items;
	}

	private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                	selected.setUsuario(usuario);
                    selected.setDataHoraAtualizacao(new Date());
                	if(persistAction == PersistAction.CREATE){
                		if(getFacade().findRegiaoPais(selected.getDescricaoRegiao(), selected.getPais()))	{
                    		getFacade().edit(selected);
                    		JsfUtil.addSuccessMessage(successMessage);
                    	} else {
                    		JsfUtil.addErrorMessage("Já existe uma região cadastrada com esses dados.");
                    	}
                	} else if(persistAction == PersistAction.UPDATE) {
                		if(getFacade().findRegiaoPais(selected.getDescricaoRegiao(),selected.getPais())) {
                			getFacade().edit(selected);
                			JsfUtil.addSuccessMessage(successMessage);
                		} else {
                			JsfUtil.addErrorMessage("Já existe uma região cadastrada com esses dados.");
                		}
                		
                	}
                } else {
                    getFacade().remove(selected);
                    JsfUtil.addSuccessMessage(successMessage);
                }
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

	public Regiao getRegiao(java.lang.Integer id) {
		return getFacade().find(id);
	}

	public List<Regiao> getItemsAvailableSelectMany() {
		return getFacade().findAll();
	}

	public List<Regiao> getItemsAvailableSelectOne() {
		return getFacade().findAll();
	}

	@FacesConverter(forClass = Regiao.class)
	public static class RegiaoControllerConverter implements Converter {

		@Override
		public Object getAsObject(FacesContext facesContext,
				UIComponent component, String value) {
			if (value == null || value.length() == 0) {
				return null;
			}
			RegiaoController controller = (RegiaoController) facesContext
					.getApplication()
					.getELResolver()
					.getValue(facesContext.getELContext(), null,
							"regiaoController");
			return controller.getRegiao(getKey(value));
		}

		java.lang.Integer getKey(String value) {
			java.lang.Integer key;
			key = Integer.valueOf(value);
			return key;
		}

		String getStringKey(java.lang.Integer value) {
			StringBuilder sb = new StringBuilder();
			sb.append(value);
			return sb.toString();
		}

		@Override
		public String getAsString(FacesContext facesContext,
				UIComponent component, Object object) {
			if (object == null) {
				return null;
			}
			if (object instanceof Regiao) {
				Regiao o = (Regiao) object;
				return getStringKey(o.getCodigo());
			} else {
				Logger.getLogger(this.getClass().getName()).log(
						Level.SEVERE,
						"object {0} is of type {1}; expected type: {2}",
						new Object[] { object, object.getClass().getName(),
								Regiao.class.getName() });
				return null;
			}
		}

	}

}
