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

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;


import com.compreingressos.controleacesso.CatracaSetor;
import com.compreingressos.controleacesso.bean.CatracaSetorFacade;
import com.compreingressos.controleacesso.controller.util.JsfUtil;
import com.compreingressos.controleacesso.controller.util.JsfUtil.PersistAction;

@ManagedBean(name = "catracaSetorController")
@ViewScoped
public class CatracaSetorController implements Serializable {

    @EJB
    private com.compreingressos.controleacesso.bean.CatracaSetorFacade ejbFacade;
    private List<CatracaSetor> items = null;
    private CatracaSetor selected;
    private final Map<String, Object> filtros = new HashMap<>();

    public CatracaSetorController() {
    }

    public CatracaSetor getSelected() {
        return selected;
    }

    public void setSelected(CatracaSetor selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    public CatracaSetorFacade getFacade() {
        return ejbFacade;
    }

    public CatracaSetor prepareCreate() {
        selected = new CatracaSetor();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CatracaSetorCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CatracaSetorUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CatracaSetorDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<CatracaSetor> getItems() {
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
                    selected.setDataAtualizacao(new Date());
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

    public CatracaSetor getCatracaSetor(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<CatracaSetor> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<CatracaSetor> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    
    public class CatracaSetorLazy extends LazyDataModel<CatracaSetor> {
    	
    	private static final long serialVersionUID = 1L;
    	private List<CatracaSetor> objList = null;
    	
    	public CatracaSetorLazy(List<CatracaSetor> objList) {
    		this.objList = objList;
    	}
    	
    	@Override
    	public List<CatracaSetor> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
    		objList = new ArrayList<>();
    		try {
    			Context ctx = new javax.naming.InitialContext();
    			CatracaSetorFacade objFacade = (CatracaSetorFacade) ctx.lookup("javax:global/controleacesso-1.0.0/CatracaSetorFacade!com.compreingresson.controleacesso.bean.CatracaSetorFacade");
    			objList = objFacade.findAll(first, pageSize, sortField, sortOrder, filters);
    			setRowCount(objFacade.count(first, pageSize, sortField, sortOrder, filters));
    			setPageSize(pageSize);
    		} catch (NamingException ex) {
    			System.out.println(ex);
    		}
    		return objList;
    	}
    	
    	@Override
        public CatracaSetor getRowData(String rowKey) {
            Integer id = Integer.valueOf(rowKey);
            for (CatracaSetor obj : objList) {
                if (id.equals(obj.getCodigo())) {
                    return obj;
                }
            }
            return null;
        }

        @Override
        public Object getRowKey(CatracaSetor ob) {
            return ob.getCodigo();
        }
    	
    }
    

    @FacesConverter(forClass = CatracaSetor.class)
    public static class CatracaSetorControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CatracaSetorController controller = (CatracaSetorController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "catracaSetorController");
            return controller.getCatracaSetor(getKey(value));
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
            if (object instanceof CatracaSetor) {
                CatracaSetor o = (CatracaSetor) object;
                return getStringKey(o.getCodigo());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), CatracaSetor.class.getName()});
                return null;
            }
        }

    }

}
