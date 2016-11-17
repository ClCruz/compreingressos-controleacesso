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

import com.compreingressos.controleacesso.Catraca;
import com.compreingressos.controleacesso.CatracaSetor;
import com.compreingressos.controleacesso.Layout;
import com.compreingressos.controleacesso.Setor;
import com.compreingressos.controleacesso.bean.CatracaFacade;
import com.compreingressos.controleacesso.bean.CatracaSetorFacade;
import com.compreingressos.controleacesso.controller.util.JsfUtil;
import com.compreingressos.controleacesso.controller.util.JsfUtil.PersistAction;

@ManagedBean(name = "catracaController")
@ViewScoped
public class CatracaController implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private com.compreingressos.controleacesso.bean.CatracaSetorFacade ejbCatracaSetor;
	
	@EJB
    private com.compreingressos.controleacesso.bean.CatracaFacade ejbFacade;
    private List<Catraca> items = null;
    private Catraca selected;
    private List<CatracaSetor> listaCatracaSetor;
    private CatracaSetor catracaSetor;
    private final Map<String, Object> filtros = new HashMap<>();

    public CatracaController() {
    catracaSetor = new CatracaSetor();
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

	private CatracaFacade getFacade() {
        return ejbFacade;
    }
	
	private CatracaSetorFacade getCatracaSetorFacade(){
		return ejbCatracaSetor;
	}
	
    public List<CatracaSetor> getListaCatracaSetor() {
		return listaCatracaSetor;
	}

	public void setListaCatracaSetor(List<CatracaSetor> listaCatracaSetor) {
		this.listaCatracaSetor = listaCatracaSetor;
	}
	
	public CatracaSetor getCatracaSetor() {
		return catracaSetor;
	}

	public void setCatracaSetor(CatracaSetor catracaSetor) {
		this.catracaSetor = catracaSetor;
	}

	public void pegaSetor(){
		catracaSetor.setSetor(selected.getIdSetor());
	}
	
	public Catraca prepareCreate() {
        selected = new Catraca();
        catracaSetor = new CatracaSetor();
        listaCatracaSetor = new ArrayList<>();
        initializeEmbeddableKey();
        return selected;
    }
    
	public void getListCatracaSetor(){
		if(selected.getCodigo() != null){
			listaCatracaSetor = getCatracaSetorFacade().findAll(selected);
		}
	}
	
    public List<Layout> listaLayout(){
    	return getFacade().findLayout(selected.getIdLocal());
    }
    
    public List<Setor> listaSetor(){
    	return getFacade().findSetor(selected.getIdLayout());
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
                    for(CatracaSetor cSetor : listaCatracaSetor){
                    	cSetor.setCatraca(selected);
                    	cSetor.setDataAtualizacao(new Date());
                    	getCatracaSetorFacade().edit(cSetor);
                    }
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
    
    public Catraca getCatraca(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Catraca> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }
    
    public void removeCatracaSetor(CatracaSetor catracaSetor){
    	listaCatracaSetor.remove(catracaSetor);
    	getCatracaSetorFacade().remove(catracaSetor);
    }
    
    public List<Catraca> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public void addCatracaSetor(){
    	listaCatracaSetor.add(catracaSetor);
    	catracaSetor = new CatracaSetor();
    }
    
    public class CatracaLazy extends LazyDataModel<Catraca> {
    	private static final long serialVersionUID = 1L;
    	private List<Catraca> objList = null;
    	
    	public CatracaLazy(List<Catraca> objList){
    		this.objList = objList;
    	}
    	
    	@Override
    	public List<Catraca> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters){
    		objList = new ArrayList<>();
    		try{
    			Context ctx = new javax.naming.InitialContext();
    			CatracaFacade objFacade = (CatracaFacade) ctx.lookup("java:global/controleacesso-1.0.0/CatracaFacade!com.compreingressos.controleacesso.bean.CatracaFacade");
    			objList = objFacade.findAll(first, pageSize, sortField, sortOrder, filters);
                setRowCount(objFacade.count(first, pageSize, sortField, sortOrder, filters));
    			setPageSize(pageSize);
    		}catch (NamingException ex){
    			System.out.println(ex);
    		}
    		return objList;
    	}
    	
    	@Override
    	public Catraca getRowData(String rowKey) {
    		Integer id = Integer.valueOf(rowKey);
    		for(Catraca obj : objList) {
    			if(id.equals(obj.getCodigo())) {
    				return obj;
    			}
    		}
    		return null;
    	}
    	
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
