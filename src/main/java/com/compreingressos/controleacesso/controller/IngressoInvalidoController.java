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
