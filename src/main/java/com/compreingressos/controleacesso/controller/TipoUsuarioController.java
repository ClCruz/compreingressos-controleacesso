package com.compreingressos.controleacesso.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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

import com.compreingressos.controleacesso.TipoUsuario;
import com.compreingressos.controleacesso.bean.TipoUsuarioFacade;
import com.compreingressos.controleacesso.controller.util.JsfUtil;
import com.compreingressos.controleacesso.controller.util.JsfUtil.PersistAction;

@ManagedBean(name = "tipoUsuarioController")
@ViewScoped
public class TipoUsuarioController implements Serializable {

    @EJB
    private com.compreingressos.controleacesso.bean.TipoUsuarioFacade ejbFacade;
    private List<TipoUsuario> items = null;
    private TipoUsuario selected;

    public TipoUsuarioController() {
    }

    public TipoUsuario getSelected() {
        return selected;
    }

    public void setSelected(TipoUsuario selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TipoUsuarioFacade getFacade() {
        return ejbFacade;
    }

    public TipoUsuario prepareCreate() {
        selected = new TipoUsuario();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TipoUsuarioCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TipoUsuarioUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TipoUsuarioDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TipoUsuario> getItems() {
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

    public TipoUsuario getTipoUsuario(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<TipoUsuario> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TipoUsuario> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = TipoUsuario.class)
    public static class TipoUsuarioControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TipoUsuarioController controller = (TipoUsuarioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tipoUsuarioController");
            return controller.getTipoUsuario(getKey(value));
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
            if (object instanceof TipoUsuario) {
                TipoUsuario o = (TipoUsuario) object;
                return getStringKey(o.getCodigo());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TipoUsuario.class.getName()});
                return null;
            }
        }

    }

}
