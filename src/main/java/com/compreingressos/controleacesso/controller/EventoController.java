package com.compreingressos.controleacesso.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.component.fileupload.FileUpload;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.compreingressos.controleacesso.Evento;
import com.compreingressos.controleacesso.bean.EventoFacade;
import com.compreingressos.controleacesso.controller.util.JsfUtil;
import com.compreingressos.controleacesso.controller.util.JsfUtil.PersistAction;

@ManagedBean(name = "eventoController")
@ViewScoped
public class EventoController implements Serializable {

    @EJB
    private com.compreingressos.controleacesso.bean.EventoFacade ejbFacade;
    private List<Evento> items = null;
    private Evento selected;
    private FileUpload logotipo;

    public FileUpload getLogotipo() {
        return logotipo;
    }

    public void setLogotipo(FileUpload logotipo) {
        this.logotipo = logotipo;
    }
    
    public EventoController() {
    }

    public Evento getSelected() {
        return selected;
    }

    public void setSelected(Evento selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private EventoFacade getFacade() {
        return ejbFacade;
    }
    
    //Calendar mindate
    public Date getDataMinima() {
        return new Date();
    }

    public Evento prepareCreate() {
        selected = new Evento();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("EventoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("EventoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("EventoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void upload(FileUploadEvent evento) throws IOException{
        Date date = new Date();
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
        
        FacesContext aFacesContext = FacesContext.getCurrentInstance();
        ServletContext context = (ServletContext) aFacesContext.getExternalContext().getContext();
        String fileName = date.getTime() + "-" + selected.getDescricaoNome() + "-" + evento.getFile().getFileName();
        
        byte[] arq = evento.getFile().getContents();
        saveFileDisk(evento.getFile(), fileName);
        selected.setLogotipo(fileName);
    }
    
    public void saveFileDisk(UploadedFile file, String fileName) throws IOException {
        String filePath = "/controleacesso/eventoLogotipo/";
        InputStream in = file.getInputstream();
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        int content;
        while ((content = in.read()) > -1) {
            out.write(content);
        }
        in.close();
        out.close();
    }

    public InputStream getLogo() throws FileNotFoundException {
        return new FileInputStream(new File("/controleacesso/eventoLogotipo", selected.getLogotipo()));
    }
    
    public List<Evento> getItems() {
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

    public Evento getEvento(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Evento> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Evento> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Evento.class)
    public static class EventoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EventoController controller = (EventoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "eventoController");
            return controller.getEvento(getKey(value));
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
            if (object instanceof Evento) {
                Evento o = (Evento) object;
                return getStringKey(o.getCodigo());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Evento.class.getName()});
                return null;
            }
        }

    }

}
