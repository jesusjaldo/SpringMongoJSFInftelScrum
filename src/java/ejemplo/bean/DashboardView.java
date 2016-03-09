/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo.bean;

import ejemplo.collection.Status;
import ejb.TareaScrumFacade;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.TareaScrum;
 
import org.primefaces.event.CloseEvent;
import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;
 
@ManagedBean
@ViewScoped
public class DashboardView implements Serializable {
    
    @EJB
    private TareaScrumFacade tareaScrumFacade;
     
    @ManagedProperty (value = "#{manageProjectBean}")
    private ManageProjectBean manageProjectBean;
    
    @ManagedProperty(value = "#{loginBean}")
    protected LoginBean loginBean;
   
    private DashboardModel model;
    private int nstatus;
    
    ArrayList<DefaultDashboardColumn> columns;
    ArrayList<Status> status;
     
    @PostConstruct
    public void init() {
        
       
        model = new DefaultDashboardModel();
        columns = new ArrayList<DefaultDashboardColumn>();
        
        for(int i=0; i<manageProjectBean.getStatus().size(); i++){
            columns.add(new DefaultDashboardColumn());
        }         
        
        for(TareaScrum t: loginBean.selectedProject.getTareaScrumCollection()){  //Add task to column          
            columns.get(Integer.parseInt(t.getEstado())).addWidget("t"+t.getIdTarea().toString());   
        }
             
        for(int i=0; i<manageProjectBean.getStatus().size(); i++){ //Add columns to model
            model.addColumn(columns.get(i));
        }    
    }

    public ManageProjectBean getManageProjectBean() {
        return manageProjectBean;
    }

    public void setManageProjectBean(ManageProjectBean manageProjectBean) {
        this.manageProjectBean = manageProjectBean;
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public int getNstatus() {
        return nstatus;
    }

    public void setNstatus(int nstatus) {
        this.nstatus = nstatus;
    }

    public ArrayList<Status> getStatus() {
        return status;
    }

    public void setStatus(ArrayList<Status> status) {
        this.status = status;
    }

    public ManageProjectBean getProjectBean() {
        return manageProjectBean;
    }

    public void setProjectBean(ManageProjectBean projectBean) {
        this.manageProjectBean = projectBean;
    }

    public ArrayList<DefaultDashboardColumn> getColumns() {
        return columns;
    }

    public void setColumns(ArrayList<DefaultDashboardColumn> columns) {
        this.columns = columns;
    }
     
    public void handleReorder(DashboardReorderEvent event) {
        
        String idT = event.getWidgetId();
        String id = idT.substring(1);
        
        FacesMessage message = new FacesMessage();
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        message.setSummary("Cambio de estado: " + event.getWidgetId());
        message.setDetail("Item index: " + event.getItemIndex() + ", Column index: " + event.getColumnIndex() + ", Sender index: " + event.getSenderColumnIndex());
        
        manageProjectBean.setTaskStatus(id, event.getColumnIndex().toString());
        addMessage(message);
    }
     
    public void addStatus(){     
        
     model.addColumn(new DefaultDashboardColumn());
        
    }
    public void handleClose(CloseEvent event) {
        
        System.out.println("cierroooo");
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Panel Closed", "Closed panel id:'" + event.getComponent().getId() + "'");
        //DELETE TASK
        String idT = event.getComponent().getId();
        String id=idT.substring(1);
        manageProjectBean.deleteTask(id);
        
        //tareaScrumFacade.remove(tareaScrumFacade.find(event.getComponent().getId()));
        addMessage(message);
    }
     
    public void handleToggle(ToggleEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, event.getComponent().getId() + " toggled", "Status:" + event.getVisibility().name());
        //CHANGE TASK STATUS
        
        manageProjectBean.setTaskStatus(event.getComponent().getId(), event.getVisibility().name());
        addMessage(message);
    }
     
    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public DashboardModel getModel() {
        return model;
    }  
    
}