/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo.bean;


import ejemplo.collection.Status;
import ejemplo.collection.Task;
import ejemplo.service.ProjectsService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

 
import org.primefaces.event.CloseEvent;
import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
 
@Component
@Scope("session")
public class DashboardView implements Serializable {
       
    @Autowired
    LoginBean loginBean;
    
        @Autowired
    ProjectsService projectsService;
   
    private DashboardModel model;
   
    
    
    
    ArrayList<DefaultDashboardColumn> columns;
    List<Status> status;
     
    @PostConstruct
    public void init() {
        status = loginBean.selectedProject.getEstados();
        this.RefreshDash();
        
    }
    
    public void RefreshDash(){
        model = new DefaultDashboardModel();
        columns = new ArrayList<DefaultDashboardColumn>();
        
        for(int i=0; i<3; i++){
            columns.add(new DefaultDashboardColumn());
            columns.get(i).addWidget("teee");   
        }         
        
        
        for(Task t: loginBean.selectedProject.getTareas()){  //Add task to column          
            columns.get(Integer.parseInt(t.getEstado_tarea())).addWidget("t"+t.getId_tarea());   
        }
             
        for(int x=0; x<3; x++){ 
            model.addColumn(columns.get(x));
        }    
    }

    
  
  
     
    public void handleReorder(DashboardReorderEvent event) {
        
        String idT = event.getWidgetId();
        String id = idT.substring(1);
        
        FacesMessage message = new FacesMessage();
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        message.setSummary("Cambio de estado: " + event.getWidgetId());
        message.setDetail("Item index: " + event.getItemIndex() + ", Column index: " + event.getColumnIndex() + ", Sender index: " + event.getSenderColumnIndex());
        List<Task> lista_tareas = loginBean.selectedProject.getTareas();
        for (Task task : lista_tareas) {
             if(task.getId_tarea().equals(id)){
                task.setEstado_tarea(event.getColumnIndex().toString());
            }
        }
        projectsService.editProjects(loginBean.selectedProject);
        
        addMessage(message);
    }

    public DashboardModel getModel() {
        return model;
    }

    public void setModel(DashboardModel model) {
        this.model = model;
    }

    public List<Status> getStatus() {
        return status;
    }

    public void setStatus(List<Status> status) {
        this.status = status;
    }
     
    
    
    
/*   public void addStatus(TareaScrum t){     
        
       columns.get(Integer.parseInt(t.getEstado())).addWidget("t"+t.getIdTarea().toString());
             
    }*/
    public void handleClose(CloseEvent event) {
        
        System.out.println("cierroooo");
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Panel Closed", "Closed panel id:'" + event.getComponent().getId() + "'");
        //DELETE TASK
        String idT = event.getComponent().getId();
        String id=idT.substring(1);
       // manageProjectBean.deleteTask(id);
        
        //tareaScrumFacade.remove(tareaScrumFacade.find(event.getComponent().getId()));
        addMessage(message);
    }
     
    public void handleToggle(ToggleEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, event.getComponent().getId() + " toggled", "Status:" + event.getVisibility().name());
        //CHANGE TASK STATUS
        
       // manageProjectBean.setTaskStatus(event.getComponent().getId(), event.getVisibility().name());
        addMessage(message);
    }
     
    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
 
//      public void deleteTask(String id){
//        System.out.println("llego "+id );
//        TareaScrum find = tareaScrumFacade.find(Long.valueOf(id));
//        tareaScrumFacade.remove(find);
//        loginBean.selectedProject.getTareaScrumCollection().remove(find);
//        proyectoScrumFacade.edit(loginBean.selectedProject);
//        
//        if(find.getIdFichero()!=null){
//            System.out.println("tiene fichero");
//            ficherosScrumFacade.remove(find.getIdFichero());
//        }
//   
//    } 
    
    
}