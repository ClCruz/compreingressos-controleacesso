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
import javax.faces.bean.ManagedProperty;
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

import com.compreingressos.controleacesso.Credenciado;
import com.compreingressos.controleacesso.Credencial;
import com.compreingressos.controleacesso.TipoCredencial;
import com.compreingressos.controleacesso.bean.CredencialFacade;
import com.compreingressos.controleacesso.controller.util.JsfUtil;
import com.compreingressos.controleacesso.controller.util.JsfUtil.PersistAction;

@ManagedBean(name = "credencialController")
@ViewScoped
public class CredencialController implements Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    private com.compreingressos.controleacesso.bean.CredencialFacade ejbFacade;
    private List<Credencial> items = null;
    private Credencial selected;
    private Credencial credencial;
    private Credenciado credenciado;
    private FileUpload foto;
    
    @ManagedProperty(name = "credenciadoController", value = "#{credenciadoController}")
    private CredenciadoController credenciadoController = new CredenciadoController();
    
    public CredencialController() {
        credenciado = new Credenciado();
        credencial = new Credencial();
    }
        
    public void initialize(){
        prepareCreate();
    }
    
    public FileUpload getFoto() {
        return foto;
    }

    public void setFoto(FileUpload foto) {
        this.foto = foto;
    }
    
    public Credencial getSelected() {
       return selected;
    }
    
    public void setSelected(Credencial selected) {
        this.selected = selected;
    }
    
    public Credencial getCredencial() {
        return credencial;
    }

    public void setCredencial(Credencial credencial) {
        this.credencial = credencial;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }
    
    public Credenciado getCredenciado() {
        return credenciado;
    }
    
    CredencialFacade getFacade() {
        return ejbFacade;
    }
    
    //Calendar mindate
    public Date getDataMinima(){
        return new Date();
    }
    
    public Credencial prepareCreate() {
        credencial = new Credencial();
        selected.setTipoCredencial(new TipoCredencial());
        initializeEmbeddableKey();
        return selected;
    }

    public CredenciadoController getCredenciadoController() {
        return credenciadoController;
    }
    
    public void setCredenciadoController(
            CredenciadoController credenciadoController) {
            this.credenciadoController = credenciadoController;
    }
    
    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CredencialCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CredencialUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CredencialDeleted"));
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
        String fileName = date.getTime() + "-" + credenciado.getNome() + "-" +evento.getFile().getFileName();
        
        byte[] arq = evento.getFile().getContents();
        saveFileDisk(evento.getFile(), fileName);
        credenciado.setFoto(fileName);
    }

    public void saveFileDisk(UploadedFile file, String fileName) throws IOException {
        String filePath = "/controleacesso/credenciadoFoto/";
        InputStream in = file.getInputstream();
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        int content;
        while ((content = in.read()) > -1) {
            out.write(content);
        }
        in.close();
        out.close();
    }
    
   public InputStream getImagem() throws FileNotFoundException {
        return new FileInputStream(new File("/controleacesso/credenciadoFoto", credenciado.getFoto()));
    }
    
    
    public List<Credencial> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (credencial != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    if(persistAction == PersistAction.CREATE){
                        Credenciado cred = credencial.getCredenciado();
                        cred.setDataHoraAtualizacao(new Date());
                        cred  = credenciadoController.getFacade().update(cred);
                        selected.setCredenciado(cred);
                        getFacade().edit(selected);
                    } else if(persistAction == PersistAction.UPDATE){
                        getFacade().edit(credencial);
                    }
                } else {
                    getFacade().remove(credencial);
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
    
    public void persist() {
        try{
            getFacade().edit(credencial);
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
    
    public Credencial getCredencial(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Credencial> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Credencial> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    
    @FacesConverter(forClass = Credencial.class)
    public static class CredencialControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CredencialController controller = (CredencialController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "credencialController");
            return controller.getCredencial(getKey(value));
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
            if (object instanceof Credencial) {
                Credencial o = (Credencial) object;
                return getStringKey(o.getCodigo());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Credencial.class.getName()});
                return null;
            }
        }

    }

}
