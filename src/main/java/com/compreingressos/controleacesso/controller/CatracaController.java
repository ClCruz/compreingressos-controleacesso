package com.compreingressos.controleacesso.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.compreingressos.controleacesso.Catraca;
import com.compreingressos.controleacesso.CatracaSetor;
import com.compreingressos.controleacesso.Layout;
import com.compreingressos.controleacesso.Setor;
import com.compreingressos.controleacesso.bean.CatracaFacade;
import com.compreingressos.controleacesso.controller.util.JsfUtil;
import com.compreingressos.controleacesso.controller.util.JsfUtil.PersistAction;

@ManagedBean(name = "catracaController")
@ViewScoped
public class CatracaController implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
    private com.compreingressos.controleacesso.bean.CatracaFacade ejbFacade;
    private List<Catraca> items = null;
    private List<CatracaSetor> listaCS = null;
    private List<CatracaSetor> listaEditCS = null;
    private Catraca selected;
    private CatracaSetor selectedCS;
    
    @ManagedProperty(name = "catracaSetorController", value = "#{catracaSetorController}")
    private CatracaSetorController catracaSetorController = new CatracaSetorController();

    public CatracaController() {
    	listaCS = new ArrayList<>();
    }

    @PostConstruct
    public void init(){
    	selectedCS = new CatracaSetor();
    }
    
    public void listaItens(){
    	if(selected.getCodigo() != null){
    		listaEditCS = new ArrayList<>();
    		List<CatracaSetor> listaTemporariaCS = getCatracaSetorController().getFacade().findAll(new Catraca(selected.getCodigo()));
    		if(listaTemporariaCS != null){
    			for(CatracaSetor lista : listaTemporariaCS){
    				listaEditCS.add(new CatracaSetor(lista.getSetor()));
    			}
    		}
    	}
    }
    

    public Catraca getSelected() {
        return selected;
    }

    public void setSelected(Catraca selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    public List<CatracaSetor> getListaCS() {
		return listaCS;
	}

	public void setListaCS(List<CatracaSetor> listaCS) {
		this.listaCS = listaCS;
	}

	public CatracaSetor getSelectedCS() {
		return selectedCS;
	}
	
	public void pegaSetor(){
		selectedCS.setSetor(selected.getIdSetor());
	}
	
	public List<CatracaSetor> getListaEditCS() {
		return listaEditCS;
	}

	public void setListaEditCS(List<CatracaSetor> listaEditCS) {
		this.listaEditCS = listaEditCS;
	}

	public void addListaCS(){
    	listaCS.add(new CatracaSetor(selectedCS.getSetor()));
    }

	private CatracaFacade getFacade() {
        return ejbFacade;
    }

    public Catraca prepareCreate() {
        selected = new Catraca();
        initializeEmbeddableKey();
        return selected;
    }
    
    public List<Layout> listaLayout(){
    	return getFacade().findLayout(selected.getIdLocal());
    }
    
    public List<Setor> listaSetor(){
    	return getFacade().findSetor(selected.getIdLayout());
    }
    
    public CatracaSetorController getCatracaSetorController() {
		return catracaSetorController;
	}

	public void setCatracaSetorController(
			CatracaSetorController catracaSetorController) {
		this.catracaSetorController = catracaSetorController;
	}

	public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CatracaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CatracaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CatracaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Catraca> getItems() {
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
                    selected = getFacade().update(selected);
                    persist();
//                    getFacade().edit(selected);
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
    
    public void persist(){
    	try{
    		if(listaCS != null){
    			for(CatracaSetor cs : listaCS){
    				cs.setCatraca(selected);
    				cs.setDataAtualizacao(new Date());
    				getCatracaSetorController().getFacade().edit(cs);
    			}
    		}
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

    public Catraca getCatraca(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Catraca> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Catraca> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public List<CatracaSetor> getItensSetor(Integer id){
    	return getCatracaSetorController().getFacade().findAll(new Catraca(id));
    }
    
    @FacesConverter(forClass = Catraca.class)
    public static class CatracaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CatracaController controller = (CatracaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "catracaController");
            return controller.getCatraca(getKey(value));
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
            if (object instanceof Catraca) {
                Catraca o = (Catraca) object;
                return getStringKey(o.getCodigo());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Catraca.class.getName()});
                return null;
            }
        }

    }

}
