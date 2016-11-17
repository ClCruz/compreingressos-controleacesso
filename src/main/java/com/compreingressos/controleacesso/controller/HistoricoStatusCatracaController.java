package com.compreingressos.controleacesso.controller;

import com.compreingressos.controleacesso.HistoricoStatusCatraca;
import com.compreingressos.controleacesso.controller.util.JsfUtil;
import com.compreingressos.controleacesso.controller.util.JsfUtil.PersistAction;
import com.compreingressos.controleacesso.bean.HistoricoStatusCatracaFacade;

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
import javax.inject.Named;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

@Named("historicoStatusCatracaController")
@SessionScoped
public class HistoricoStatusCatracaController implements Serializable {

    @EJB
    private com.compreingressos.controleacesso.bean.HistoricoStatusCatracaFacade ejbFacade;
    private List<HistoricoStatusCatraca> items = null;
    private HistoricoStatusCatraca selected;
    private final Map<String, Object> filtros = new HashMap<>();

    public HistoricoStatusCatracaController() {
    }

    public HistoricoStatusCatraca getSelected() {
        return selected;
    }

    public void setSelected(HistoricoStatusCatraca selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private HistoricoStatusCatracaFacade getFacade() {
        return ejbFacade;
    }

    public HistoricoStatusCatraca prepareCreate() {
        selected = new HistoricoStatusCatraca();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("HistoricoStatusCatracaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("HistoricoStatusCatracaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("HistoricoStatusCatracaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<HistoricoStatusCatraca> getItems() {
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

    public HistoricoStatusCatraca getHistoricoStatusCatraca(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<HistoricoStatusCatraca> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<HistoricoStatusCatraca> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

public class HistoricoStatusCatracaLazy extends LazyDataModel<HistoricoStatusCatraca> {
    	
    	private static final long serialVersionUID = 1L;
        private List<HistoricoStatusCatraca> objList = null;

        public HistoricoStatusCatracaLazy(List<HistoricoStatusCatraca> objList) {
            this.objList = objList;
        }
        
        @Override
        public List<HistoricoStatusCatraca> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        	objList = new ArrayList<>();
            try {
                Context ctx = new javax.naming.InitialContext();
                HistoricoStatusCatracaFacade objFacade = (HistoricoStatusCatracaFacade) ctx.lookup("java:global/compreingressos-portal-1.0.0/"
                		+ "HistoricoStatusCatracaFacade!com.compreingressos.controleacesso.bean.HistoricoStatusCatracaFacade");
                objList = objFacade.findAll(first, pageSize, sortField, sortOrder, filters);
                setRowCount(objFacade.count(first, pageSize, sortField, sortOrder, filters));
                setPageSize(pageSize);
            } catch (NamingException ex) {
                System.out.println(ex);
            }
            return objList;
        }

        @Override
        public HistoricoStatusCatraca getRowData(String rowKey) {
            Integer id = Integer.valueOf(rowKey);
            for (HistoricoStatusCatraca obj : objList) {
                if (id.equals(obj.getCodigo())) {
                    return obj;
                }
            }
            return null;
        }
    }
    
    @FacesConverter(forClass = HistoricoStatusCatraca.class)
    public static class HistoricoStatusCatracaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            HistoricoStatusCatracaController controller = (HistoricoStatusCatracaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "historicoStatusCatracaController");
            return controller.getHistoricoStatusCatraca(getKey(value));
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
            if (object instanceof HistoricoStatusCatraca) {
                HistoricoStatusCatraca o = (HistoricoStatusCatraca) object;
                return getStringKey(o.getCodigo());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), HistoricoStatusCatraca.class.getName()});
                return null;
            }
        }

    }

}
