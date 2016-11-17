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

import com.compreingressos.controleacesso.CredencialGradeHoraria;
import com.compreingressos.controleacesso.bean.CredencialGradeHorariaFacade;
import com.compreingressos.controleacesso.controller.util.JsfUtil;
import com.compreingressos.controleacesso.controller.util.JsfUtil.PersistAction;

@ManagedBean(name = "credencialGradeHorariaController")
@ViewScoped
public class CredencialGradeHorariaController implements Serializable {

    @EJB
    private com.compreingressos.controleacesso.bean.CredencialGradeHorariaFacade ejbFacade;
    private List<CredencialGradeHoraria> items = null;
    private CredencialGradeHoraria selected;
    private final Map<String, Object> filtros = new HashMap<>();

    public CredencialGradeHorariaController() {
    }

    public CredencialGradeHoraria getSelected() {
        return selected;
    }

    public void setSelected(CredencialGradeHoraria selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CredencialGradeHorariaFacade getFacade() {
        return ejbFacade;
    }

    public CredencialGradeHoraria prepareCreate() {
        selected = new CredencialGradeHoraria();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CredencialGradeHorariaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CredencialGradeHorariaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CredencialGradeHorariaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<CredencialGradeHoraria> getItems() {
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

    public CredencialGradeHoraria getCredencialGradeHoraria(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<CredencialGradeHoraria> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<CredencialGradeHoraria> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    
public class CredencialGradeHorariaLazy extends LazyDataModel<CredencialGradeHoraria> {
    	
    	private static final long serialVersionUID = 1L;
        private List<CredencialGradeHoraria> objList = null;

        public CredencialGradeHorariaLazy(List<CredencialGradeHoraria> objList) {
            this.objList = objList;
        }
        
        @Override
        public List<CredencialGradeHoraria> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        	objList = new ArrayList<>();
            try {
                Context ctx = new javax.naming.InitialContext();
                CredencialGradeHorariaFacade objFacade = (CredencialGradeHorariaFacade) ctx.lookup("java:global/controleacesso-1.0.0/"
                		+ "CredencialGradeHorariaFacade!com.compreingressos.controleacesso.bean.CredencialGradeHorariaFacade");
                objList = objFacade.findAll(first, pageSize, sortField, sortOrder, filters);
                setRowCount(objFacade.count(first, pageSize, sortField, sortOrder, filters));
                setPageSize(pageSize);
            } catch (NamingException ex) {
                System.out.println(ex);
            }
            return objList;
        }

        @Override
        public CredencialGradeHoraria getRowData(String rowKey) {
            Integer id = Integer.valueOf(rowKey);
            for (CredencialGradeHoraria obj : objList) {
                if (id.equals(obj.getCodigo())) {
                    return obj;
                }
            }
            return null;
        }
    }

    @FacesConverter(forClass = CredencialGradeHoraria.class)
    public static class CredencialGradeHorariaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CredencialGradeHorariaController controller = (CredencialGradeHorariaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "credencialGradeHorariaController");
            return controller.getCredencialGradeHoraria(getKey(value));
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
            if (object instanceof CredencialGradeHoraria) {
                CredencialGradeHoraria o = (CredencialGradeHoraria) object;
                return getStringKey(o.getCodigo());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), CredencialGradeHoraria.class.getName()});
                return null;
            }
        }

    }

}
