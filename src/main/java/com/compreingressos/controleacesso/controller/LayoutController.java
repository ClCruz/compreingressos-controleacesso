package com.compreingressos.controleacesso.controller;

import com.compreingressos.controleacesso.Layout;
import com.compreingressos.controleacesso.controller.util.JsfUtil;
import com.compreingressos.controleacesso.controller.util.JsfUtil.PersistAction;
import com.compreingressos.controleacesso.bean.LayoutFacade;
import java.io.ByteArrayInputStream;
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
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

@Named("layoutController")
@SessionScoped
public class LayoutController implements Serializable {

    @EJB
    private com.compreingressos.controleacesso.bean.LayoutFacade ejbFacade;
    private List<Layout> items = null;
    private Layout selected;
    private FileUpload imagem;

    public FileUpload getImagem() {
        return imagem;
    }

    public void setImagem(FileUpload imagem) {
        this.imagem = imagem;
    }
    
    public LayoutController() {
    }

    public Layout getSelected() {
        return selected;
    }

    public void setSelected(Layout selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private LayoutFacade getFacade() {
        return ejbFacade;
    }

    public Layout prepareCreate() {
        selected = new Layout();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("LayoutCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("LayoutUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("LayoutDeleted"));
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
        selected.setImagem(fileName);
    }
    
    public void saveFileDisk(UploadedFile file, String fileName) throws IOException {
        String filePath = "/controleacesso/layout/";
        InputStream in = file.getInputstream();
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        int content;
        while ((content = in.read()) > -1) {
            out.write(content);
        }
        in.close();
        out.close();
    }

    public InputStream getFoto() throws FileNotFoundException {
        return new FileInputStream(new File("/controleacesso/layout", selected.getImagem()));
    }
    
    public List<Layout> getItems() {
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

    public Layout getLayout(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Layout> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Layout> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Layout.class)
    public static class LayoutControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            LayoutController controller = (LayoutController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "layoutController");
            return controller.getLayout(getKey(value));
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
            if (object instanceof Layout) {
                Layout o = (Layout) object;
                return getStringKey(o.getCodigo());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Layout.class.getName()});
                return null;
            }
        }

    }

}
