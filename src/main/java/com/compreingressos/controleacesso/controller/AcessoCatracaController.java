package com.compreingressos.controleacesso.controller;

import java.io.Serializable;
import java.util.ArrayList;
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

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.compreingressos.controleacesso.AcessoCatraca;
import com.compreingressos.controleacesso.bean.AcessoCatracaFacade;
import com.compreingressos.controleacesso.controller.util.JsfUtil;
import com.compreingressos.controleacesso.controller.util.JsfUtil.PersistAction;

@ManagedBean(name = "acessoCatracaController")
@ViewScoped
public class AcessoCatracaController implements Serializable {

    @EJB
    private com.compreingressos.controleacesso.bean.AcessoCatracaFacade ejbFacade;
    private List<AcessoCatraca> items = null;
    private AcessoCatraca selected;
    private final Map<String, Object> filtros = new HashMap<>();

    public AcessoCatracaController() {
    }

    public AcessoCatraca getSelected() {
        return selected;
    }

    public void setSelected(AcessoCatraca selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AcessoCatracaFacade getFacade() {
        return ejbFacade;
    }


    public AcessoCatraca prepareCreate() {
        selected = new AcessoCatraca();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("AcessoCatracaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("AcessoCatracaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("AcessoCatracaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<AcessoCatraca> getItems() {
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
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
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

    public AcessoCatraca getAcessoCatraca(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<AcessoCatraca> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<AcessoCatraca> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public class AcessoCatracaLazy extends LazyDataModel<AcessoCatraca> {
    	private static final long serialVersionUID = 1L;
    	private List<AcessoCatraca> objList = null;
    	
    	public AcessoCatracaLazy(List<AcessoCatraca> objList){
    		this.objList = objList;
    	}
    	
    	@Override
    	public List<AcessoCatraca> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters){
    		objList = new ArrayList<>();
    		try{
    			Context ctx = new javax.naming.InitialContext();
    			AcessoCatracaFacade objFacade = (AcessoCatracaFacade) ctx.lookup("java:global/controleacesso-1.0.0/AcessoCatracaFacade!com.compreingressos.controleacesso.bean.AcessoCatracaFacade");
    			setRowCount(objFacade.count(first, pageSize, sortField, sortOrder, filters));
    			setPageSize(pageSize);
    		} catch (NamingException ex){
    			System.out.println(ex);
    		}
    		return objList;
    	}
    	
    	@Override
    	public AcessoCatraca getRowData (String rowKey) {
    		Integer id = Integer.valueOf(rowKey);
    		for (AcessoCatraca obj : objList) {
    			if (id.equals(obj.getCodigo())) {
    				return obj;
    			}
    		}
    		return null;
    	}
    	
    	@Override
    	public Object getRowKey(AcessoCatraca ob) {
    		return ob.getCodigo();
    	}
    }
    
    @FacesConverter(forClass = AcessoCatraca.class)
    public static class AcessoCatracaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AcessoCatracaController controller = (AcessoCatracaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "acessoCatracaController");
            return controller.getAcessoCatraca(getKey(value));
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
            if (object instanceof AcessoCatraca) {
                AcessoCatraca o = (AcessoCatraca) object;
                return getStringKey(o.getCodigo());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), AcessoCatraca.class.getName()});
                return null;
            }
        }

    }

}
