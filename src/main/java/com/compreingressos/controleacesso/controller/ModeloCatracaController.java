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

import com.compreingressos.controleacesso.ModeloCatraca;
import com.compreingressos.controleacesso.bean.ModeloCatracaFacade;
import com.compreingressos.controleacesso.controller.util.JsfUtil;
import com.compreingressos.controleacesso.controller.util.JsfUtil.PersistAction;

@ManagedBean(name = "modeloCatracaController")
@ViewScoped
public class ModeloCatracaController implements Serializable {

    @EJB
    private com.compreingressos.controleacesso.bean.ModeloCatracaFacade ejbFacade;
    private List<ModeloCatraca> items = null;
    private ModeloCatraca selected;
    private final Map<String, Object> filtros = new HashMap<>();

    public ModeloCatracaController() {
    }

    public ModeloCatraca getSelected() {
        return selected;
    }

    public void setSelected(ModeloCatraca selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ModeloCatracaFacade getFacade() {
        return ejbFacade;
    }

    public ModeloCatraca prepareCreate() {
        selected = new ModeloCatraca();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ModeloCatracaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ModeloCatracaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ModeloCatracaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<ModeloCatraca> getItems() {
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

    public ModeloCatraca getModeloCatraca(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<ModeloCatraca> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<ModeloCatraca> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public class ModeloCatracaLazy extends LazyDataModel<ModeloCatraca> {
    	
    	private static final long serialVersionUID = 1L;
        private List<ModeloCatraca> objList = null;

        public ModeloCatracaLazy(List<ModeloCatraca> objList) {
            this.objList = objList;
        }
        
        @Override
        public List<ModeloCatraca> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        	objList = new ArrayList<>();
            try {
                Context ctx = new javax.naming.InitialContext();
                ModeloCatracaFacade objFacade = (ModeloCatracaFacade) ctx.lookup("java:global/controleacesso-1.0.0/"
                		+ "ModeloCatracaFacade!com.compreingressos.controleacesso.bean.ModeloCatracaFacade");
                objList = objFacade.findAll(first, pageSize, sortField, sortOrder, filters);
                setRowCount(objFacade.count(first, pageSize, sortField, sortOrder, filters));
                setPageSize(pageSize);
            } catch (NamingException ex) {
                System.out.println(ex);
            }
            return objList;
        }

        @Override
        public ModeloCatraca getRowData(String rowKey) {
            Integer id = Integer.valueOf(rowKey);
            for (ModeloCatraca obj : objList) {
                if (id.equals(obj.getCodigo())) {
                    return obj;
                }
            }
            return null;
        }
    }
    
    @FacesConverter(forClass = ModeloCatraca.class)
    public static class ModeloCatracaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ModeloCatracaController controller = (ModeloCatracaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "modeloCatracaController");
            return controller.getModeloCatraca(getKey(value));
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
            if (object instanceof ModeloCatraca) {
                ModeloCatraca o = (ModeloCatraca) object;
                return getStringKey(o.getCodigo());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ModeloCatraca.class.getName()});
                return null;
            }
        }

    }

}
