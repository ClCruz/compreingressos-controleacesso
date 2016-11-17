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

import com.compreingressos.controleacesso.TipoCredencial;
import com.compreingressos.controleacesso.bean.TipoCredencialFacade;
import com.compreingressos.controleacesso.controller.util.JsfUtil;
import com.compreingressos.controleacesso.controller.util.JsfUtil.PersistAction;

@ManagedBean(name = "tipoCredencialController")
@ViewScoped
public class TipoCredencialController implements Serializable {

    @EJB
    private com.compreingressos.controleacesso.bean.TipoCredencialFacade ejbFacade;
    private List<TipoCredencial> items = null;
    private TipoCredencial selected;
    private final Map<String, Object> filtros = new HashMap<>();

    public TipoCredencialController() {
    }

    public TipoCredencial getSelected() {
        return selected;
    }

    public void setSelected(TipoCredencial selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TipoCredencialFacade getFacade() {
        return ejbFacade;
    }

    public TipoCredencial prepareCreate() {
        selected = new TipoCredencial();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TipoCredencialCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TipoCredencialUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TipoCredencialDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TipoCredencial> getItems() {
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
                    selected.setDataHoraAtualizacao(new Date());
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

    public TipoCredencial getTipoCredencial(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<TipoCredencial> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TipoCredencial> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public class TipoCredencialLazy extends LazyDataModel<TipoCredencial> {
    	
    	private static final long serialVersionUID = 1L;
        private List<TipoCredencial> objList = null;

        public TipoCredencialLazy(List<TipoCredencial> objList) {
            this.objList = objList;
        }
        
        @Override
        public List<TipoCredencial> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        	objList = new ArrayList<>();
            try {
                Context ctx = new javax.naming.InitialContext();
                TipoCredencialFacade objFacade = (TipoCredencialFacade) ctx.lookup("java:global/controleacesso-1.0.0/"
                		+ "TipoCredencialFacade!com.compreingressos.controleacesso.bean.TipoCredencialFacade");
                objList = objFacade.findAll(first, pageSize, sortField, sortOrder, filters);
                setRowCount(objFacade.count(first, pageSize, sortField, sortOrder, filters));
                setPageSize(pageSize);
            } catch (NamingException ex) {
                System.out.println(ex);
            }
            return objList;
        }

        @Override
        public TipoCredencial getRowData(String rowKey) {
            Integer id = Integer.valueOf(rowKey);
            for (TipoCredencial obj : objList) {
                if (id.equals(obj.getCodigo())) {
                    return obj;
                }
            }
            return null;
        }
    }
    
    @FacesConverter(forClass = TipoCredencial.class)
    public static class TipoCredencialControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TipoCredencialController controller = (TipoCredencialController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tipoCredencialController");
            return controller.getTipoCredencial(getKey(value));
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
            if (object instanceof TipoCredencial) {
                TipoCredencial o = (TipoCredencial) object;
                return getStringKey(o.getCodigo());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TipoCredencial.class.getName()});
                return null;
            }
        }

    }

}
