package com.compreingressos.controleacesso.controller;

import com.compreingressos.controleacesso.Contratante;
import com.compreingressos.controleacesso.controller.util.JsfUtil;
import com.compreingressos.controleacesso.controller.util.JsfUtil.PersistAction;
import com.compreingressos.controleacesso.bean.ContratanteFacade;
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
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
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

@Named("contratanteController")
@SessionScoped
public class ContratanteController implements Serializable {

    @EJB
    private com.compreingressos.controleacesso.bean.ContratanteFacade ejbFacade;
    private List<Contratante> items = null;
    private Contratante selected;
    private FileUpload logotipo;

    public ContratanteController() {
    }

    public Contratante getSelected() {
        return selected;
    }

    public void setSelected(Contratante selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ContratanteFacade getFacade() {
        return ejbFacade;
    }

    public Contratante prepareCreate() {
        selected = new Contratante();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ContratanteCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ContratanteUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ContratanteDeleted"));
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
        String fileName = date.getTime() + "-" + selected.getNome() + "-" + evento.getFile().getFileName();
        
        byte[] arq = evento.getFile().getContents();
        saveFileDisk(evento.getFile(), fileName);
        selected.setLogotipo(fileName);
    }
    
    public void saveFileDisk(UploadedFile file, String fileName) throws IOException {
        String filePath = "/controleacesso/contratanteLogotipo/";
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
        return new FileInputStream(new File("/controleacesso/contratanteLogotipo", selected.getLogotipo()));
    }

    public List<Contratante> getItems() {
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

    public Contratante getContratante(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Contratante> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Contratante> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Contratante.class)
    public static class ContratanteControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ContratanteController controller = (ContratanteController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "contratanteController");
            return controller.getContratante(getKey(value));
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
            if (object instanceof Contratante) {
                Contratante o = (Contratante) object;
                return getStringKey(o.getCodigo());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Contratante.class.getName()});
                return null;
            }
        }

    }

}