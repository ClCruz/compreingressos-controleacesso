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

import com.compreingressos.controleacesso.Apresentacao;
import com.compreingressos.controleacesso.bean.ApresentacaoFacade;
import com.compreingressos.controleacesso.controller.util.JsfUtil;
import com.compreingressos.controleacesso.controller.util.JsfUtil.PersistAction;

@ManagedBean(name = "apresentacaoController")
@ViewScoped
public class ApresentacaoController implements Serializable {

    @EJB
    private com.compreingressos.controleacesso.bean.ApresentacaoFacade ejbFacade;
    private List<Apresentacao> items = null;
    private Apresentacao selected;
    private final Map<String, Object> filtros = new HashMap<>();

    public ApresentacaoController() {
    }

    public Apresentacao getSelected() {
        return selected;
    }

    public void setSelected(Apresentacao selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ApresentacaoFacade getFacade() {
        return ejbFacade;
    }

    //Calendar mindate 
    public Date getDataMinima(){
    	return new Date();
    }
    
    public Apresentacao prepareCreate() {
        selected = new Apresentacao();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ApresentacaoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ApresentacaoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ApresentacaoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Apresentacao> getItems() {
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

    public Apresentacao getApresentacao(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Apresentacao> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Apresentacao> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    
    public class ApresentacaoLazy extends LazyDataModel<Apresentacao> {
    	
    	private static final long serialVersionUID = 1L;
    	private List<Apresentacao> objList = null;
    	
    	public ApresentacaoLazy(List<Apresentacao> objList){
    		this.objList = objList;
    	}
    	
    	@Override
    	public List<Apresentacao> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
    		objList = new ArrayList<>();
    		try {
    			Context ctx = new javax.naming.InitialContext();
    			ApresentacaoFacade objFacade = (ApresentacaoFacade) ctx.lookup("java:global/controleacesso-1.0.0/ApresentacaoFacade!com.compreingresso.controleacesso.bean.ApresentacaoFacade");
    			objList = objFacade.findAll(first, pageSize, sortField, sortOrder, filters);
    			setRowCount(objFacade.count(first, pageSize, sortField, sortOrder,filters));
    			setPageSize(pageSize);
    		} catch (NamingException ex){
    			System.out.println(ex);
    		}
    		return objList;
    	}
    	
    	@Override
    	public Apresentacao getRowData(String rowKey) {
    		Integer id = Integer.valueOf(rowKey);
    		for(Apresentacao obj : objList) {
    			if(id.equals(obj.getCodigo())){
    				return obj;
    			}
    		}
    		return null;
    	}
    	
    	@Override
    	public Object getRowKey(Apresentacao ob){
    		return ob.getCodigo();
    	}
    	
    }

    @FacesConverter(forClass = Apresentacao.class)
    public static class ApresentacaoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ApresentacaoController controller = (ApresentacaoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "apresentacaoController");
            return controller.getApresentacao(getKey(value));
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
            if (object instanceof Apresentacao) {
                Apresentacao o = (Apresentacao) object;
                return getStringKey(o.getCodigo());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Apresentacao.class.getName()});
                return null;
            }
        }

    }

}
