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

import com.compreingressos.controleacesso.IngressoInvalido;
import com.compreingressos.controleacesso.bean.IngressoInvalidoFacade;
import com.compreingressos.controleacesso.controller.util.JsfUtil;
import com.compreingressos.controleacesso.controller.util.JsfUtil.PersistAction;

@ManagedBean(name = "ingressoInvalidoController")
@ViewScoped
public class IngressoInvalidoController implements Serializable {

    @EJB
    private com.compreingressos.controleacesso.bean.IngressoInvalidoFacade ejbFacade;
    private List<IngressoInvalido> items = null;
    private IngressoInvalido selected;
    private final Map<String, Object> filtros = new HashMap<>();

    public IngressoInvalidoController() {
    }

    public IngressoInvalido getSelected() {
        return selected;
    }

    public void setSelected(IngressoInvalido selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private IngressoInvalidoFacade getFacade() {
        return ejbFacade;
    }

    public IngressoInvalido prepareCreate() {
        selected = new IngressoInvalido();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("IngressoInvalidoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("IngressoInvalidoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("IngressoInvalidoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<IngressoInvalido> getItems() {
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

    public IngressoInvalido getIngressoInvalido(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<IngressoInvalido> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<IngressoInvalido> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    
public class IngressoInvalidoLazy extends LazyDataModel<IngressoInvalido> {
    	
    	private static final long serialVersionUID = 1L;
        private List<IngressoInvalido> objList = null;

        public IngressoInvalidoLazy(List<IngressoInvalido> objList) {
            this.objList = objList;
        }
        
        @Override
        public List<IngressoInvalido> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        	objList = new ArrayList<>();
            try {
                Context ctx = new javax.naming.InitialContext();
                IngressoInvalidoFacade objFacade = (IngressoInvalidoFacade) ctx.lookup("java:global/controleacesso-1.0.0/"
                		+ "IngressoInvalidoFacade!com.compreingressos.controleacesso.bean.IngressoInvalidoFacade");
                objList = objFacade.findAll(first, pageSize, sortField, sortOrder, filters);
                setRowCount(objFacade.count(first, pageSize, sortField, sortOrder, filters));
                setPageSize(pageSize);
            } catch (NamingException ex) {
                System.out.println(ex);
            }
            return objList;
        }

        @Override
        public IngressoInvalido getRowData(String rowKey) {
            Integer id = Integer.valueOf(rowKey);
            for (IngressoInvalido obj : objList) {
                if (id.equals(obj.getIngressoVendido())) {
                    return obj;
                }
            }
            return null;
        }
    }

    @FacesConverter(forClass = IngressoInvalido.class)
    public static class IngressoInvalidoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            IngressoInvalidoController controller = (IngressoInvalidoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ingressoInvalidoController");
            return controller.getIngressoInvalido(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof IngressoInvalido) {
                IngressoInvalido o = (IngressoInvalido) object;
                return getStringKey(o.getIngressoVendido().getCodigo());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), IngressoInvalido.class.getName()});
                return null;
            }
        }

    }

}
