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

import com.compreingressos.controleacesso.Fabricante;
import com.compreingressos.controleacesso.bean.FabricanteFacade;
import com.compreingressos.controleacesso.controller.util.JsfUtil;
import com.compreingressos.controleacesso.controller.util.JsfUtil.PersistAction;

@ManagedBean(name = "fabricanteController")
@ViewScoped
public class FabricanteController implements Serializable {

    @EJB
    private com.compreingressos.controleacesso.bean.FabricanteFacade ejbFacade;
    private List<Fabricante> items = null;
    private Fabricante selected;
    private final Map<String, Object> filtros = new HashMap<>();

    public FabricanteController() {
    }

    public Fabricante getSelected() {
        return selected;
    }

    public void setSelected(Fabricante selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private FabricanteFacade getFacade() {
        return ejbFacade;
    }

    public Fabricante prepareCreate() {
        selected = new Fabricante();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("FabricanteCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("FabricanteUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("FabricanteDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Fabricante> getItems() {
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

    public Fabricante getFabricante(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Fabricante> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Fabricante> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

public class FabricanteLazy extends LazyDataModel<Fabricante> {
    	
    	private static final long serialVersionUID = 1L;
        private List<Fabricante> objList = null;

        public FabricanteLazy(List<Fabricante> objList) {
            this.objList = objList;
        }
        
        @Override
        public List<Fabricante> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        	objList = new ArrayList<>();
            try {
                Context ctx = new javax.naming.InitialContext();
                FabricanteFacade objFacade = (FabricanteFacade) ctx.lookup("java:global/controleacesso-1.0.0/"
                		+ "FabricanteFacade!com.compreingressos.controleacesso.bean.FabricanteFacade");
                objList = objFacade.findAll(first, pageSize, sortField, sortOrder, filters);
                setRowCount(objFacade.count(first, pageSize, sortField, sortOrder, filters));
                setPageSize(pageSize);
            } catch (NamingException ex) {
                System.out.println(ex);
            }
            return objList;
        }

        @Override
        public Fabricante getRowData(String rowKey) {
            Integer id = Integer.valueOf(rowKey);
            for (Fabricante obj : objList) {
                if (id.equals(obj.getCodigo())) {
                    return obj;
                }
            }
            return null;
        }
    }
    
    @FacesConverter(forClass = Fabricante.class)
    public static class FabricanteControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FabricanteController controller = (FabricanteController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "fabricanteController");
            return controller.getFabricante(getKey(value));
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
            if (object instanceof Fabricante) {
                Fabricante o = (Fabricante) object;
                return getStringKey(o.getCodigo());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Fabricante.class.getName()});
                return null;
            }
        }

    }

}
