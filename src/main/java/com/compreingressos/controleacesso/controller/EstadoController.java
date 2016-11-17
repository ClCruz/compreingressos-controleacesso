package com.compreingressos.controleacesso.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.compreingressos.controleacesso.Estado;
import com.compreingressos.controleacesso.Usuario;
import com.compreingressos.controleacesso.bean.EstadoFacade;
import com.compreingressos.controleacesso.controller.util.JsfUtil;
import com.compreingressos.controleacesso.controller.util.JsfUtil.PersistAction;

@ManagedBean(name = "estadoController")
@ViewScoped
public class EstadoController implements Serializable {

    @EJB
    private com.compreingressos.controleacesso.bean.EstadoFacade ejbFacade;
    private List<Estado> items = null;
    private Estado selected;
    private Usuario usuario;
    private final Map<String, Object> filtros = new HashMap<>();
    
    public EstadoController() {
    	FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		usuario = (Usuario) session.getAttribute("usuario");
    }

    public Estado getSelected() {
        return selected;
    }

    public void setSelected(Estado selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private EstadoFacade getFacade() {
        return ejbFacade;
    }

    public Estado prepareCreate() {
        selected = new Estado();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("EstadoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("EstadoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("EstadoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Estado> getItems() {
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
                    selected.setUf(selected.getUf().toUpperCase());
                    if(persistAction == PersistAction.CREATE){
                    	if(getFacade().findEstado(selected.getNome(), selected.getUf())) {
                    		getFacade().edit(selected);
                    		JsfUtil.addSuccessMessage(successMessage);
                    	} else {
                    		JsfUtil.addErrorMessage("Já existe um Estado cadastrado com esses dados.");
                    	}
                    } else if(persistAction == PersistAction.UPDATE){
                    	if(getFacade().findEstado(selected.getNome(), selected.getUf())){
                    		getFacade().edit(selected);
                    		JsfUtil.addSuccessMessage(successMessage);
                    	} else {
                    		JsfUtil.addErrorMessage("Já existe um Estado cadastrado com esses dados.");
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

    public Estado getEstado(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Estado> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Estado> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    
public class EstadoLazy extends LazyDataModel<Estado> {
    	
    	private static final long serialVersionUID = 1L;
        private List<Estado> objList = null;

        public EstadoLazy(List<Estado> objList) {
            this.objList = objList;
        }
        
        @Override
        public List<Estado> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        	objList = new ArrayList<>();
            try {
                Context ctx = new javax.naming.InitialContext();
                EstadoFacade objFacade = (EstadoFacade) ctx.lookup("java:global/controleacesso-1.0.0/EstadoFacade!com.compreingressos.controleacesso.bean.EstadoFacade");
                objList = objFacade.findAll(first, pageSize, sortField, sortOrder, filters);
                setRowCount(objFacade.count(first, pageSize, sortField, sortOrder, filters));
                setPageSize(pageSize);
            } catch (NamingException ex) {
                System.out.println(ex);
            }
            return objList;
        }

        @Override
        public Estado getRowData(String rowKey) {
            Integer id = Integer.valueOf(rowKey);
            for (Estado obj : objList) {
                if (id.equals(obj.getCodigo())) {
                    return obj;
                }
            }
            return null;
        }
    }

    @FacesConverter(forClass = Estado.class)
    public static class EstadoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EstadoController controller = (EstadoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "estadoController");
            return controller.getEstado(getKey(value));
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
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Estado) {
                Estado o = (Estado) object;
                return getStringKey(o.getCodigo());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Estado.class.getName()});
                return null;
            }
        }

    }

}
