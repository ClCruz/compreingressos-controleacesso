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

import com.compreingressos.controleacesso.UsuarioContratante;
import com.compreingressos.controleacesso.bean.UsuarioContratanteFacade;
import com.compreingressos.controleacesso.controller.util.JsfUtil;
import com.compreingressos.controleacesso.controller.util.JsfUtil.PersistAction;

@ManagedBean(name = "usuarioContratanteController")
@ViewScoped
public class UsuarioContratanteController implements Serializable {

    @EJB
    private com.compreingressos.controleacesso.bean.UsuarioContratanteFacade ejbFacade;
    private List<UsuarioContratante> items = null;
    private UsuarioContratante selected;
    private final Map<String, Object> filtros = new HashMap<>();

    public UsuarioContratanteController() {
    }

    public UsuarioContratante getSelected() {
        return selected;
    }

    public void setSelected(UsuarioContratante selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getUsuarioContratantePK().setUsuario(selected.getUsuario().getCodigo());
        selected.getUsuarioContratantePK().setContratante(selected.getContratante().getCodigo());
    }

    protected void initializeEmbeddableKey() {
        selected.setUsuarioContratantePK(new com.compreingressos.controleacesso.UsuarioContratantePK());
    }

    private UsuarioContratanteFacade getFacade() {
        return ejbFacade;
    }

    public UsuarioContratante prepareCreate() {
        selected = new UsuarioContratante();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UsuarioContratanteCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UsuarioContratanteUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UsuarioContratanteDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<UsuarioContratante> getItems() {
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

    public UsuarioContratante getUsuarioContratante(com.compreingressos.controleacesso.UsuarioContratantePK id) {
        return getFacade().find(id);
    }

    public List<UsuarioContratante> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<UsuarioContratante> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public class UsuarioContratanteLazy extends LazyDataModel<UsuarioContratante> {
    	
    	private static final long serialVersionUID = 1L;
        private List<UsuarioContratante> objList = null;

        public UsuarioContratanteLazy(List<UsuarioContratante> objList) {
            this.objList = objList;
        }
        
        @Override
        public List<UsuarioContratante> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        	objList = new ArrayList<>();
            try {
                Context ctx = new javax.naming.InitialContext();
                UsuarioContratanteFacade objFacade = (UsuarioContratanteFacade) ctx.lookup("java:global/controleacesso-1.0.0/"
                		+ "UsuarioContratanteFacade!com.compreingressos.controleacesso.bean.UsuarioContratanteFacade");
                objList = objFacade.findAll(first, pageSize, sortField, sortOrder, filters);
                setRowCount(objFacade.count(first, pageSize, sortField, sortOrder, filters));
                setPageSize(pageSize);
            } catch (NamingException ex) {
                System.out.println(ex);
            }
            return objList;
        }

        @Override
        public UsuarioContratante getRowData(String rowKey) {
            Integer id = Integer.valueOf(rowKey);
            for (UsuarioContratante obj : objList) {
                if (id.equals(obj.getContratante())) {
                    return obj;
                }
            }
            return null;
        }
    }
    
    @FacesConverter(forClass = UsuarioContratante.class)
    public static class UsuarioContratanteControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsuarioContratanteController controller = (UsuarioContratanteController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usuarioContratanteController");
            return controller.getUsuarioContratante(getKey(value));
        }

        com.compreingressos.controleacesso.UsuarioContratantePK getKey(String value) {
            com.compreingressos.controleacesso.UsuarioContratantePK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new com.compreingressos.controleacesso.UsuarioContratantePK();
            key.setUsuario(Integer.parseInt(values[0]));
            key.setContratante(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(com.compreingressos.controleacesso.UsuarioContratantePK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getUsuario());
            sb.append(SEPARATOR);
            sb.append(value.getContratante());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof UsuarioContratante) {
                UsuarioContratante o = (UsuarioContratante) object;
                return getStringKey(o.getUsuarioContratantePK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), UsuarioContratante.class.getName()});
                return null;
            }
        }

    }

}
