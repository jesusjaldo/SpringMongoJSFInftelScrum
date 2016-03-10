///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package ejemplo.bean;
//
//import ManagedBeans.LoginBean;
//import ManagedBeans.Status;
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//import ejb.FicherosScrumFacade;
//import ejb.ProyectoScrumFacade;
//import ejb.TareaScrumFacade;
//import java.lang.ProcessBuilder.Redirect.Type;
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import javax.annotation.PostConstruct;
//import javax.ejb.EJB;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ManagedProperty;
//import javax.faces.bean.SessionScoped;
//import model.ProyectoScrum;
//import model.TareaScrum;
//
///**
// *
// * @author inftel19
// */
//
//@ManagedBean (name = "manageProjectBean")
//@SessionScoped
//public class ManageProjectBean {
//    @EJB
//    private FicherosScrumFacade ficherosScrumFacade;
//
//    @EJB
//    private TareaScrumFacade tareaScrumFacade;
//
//    @EJB
//    private ProyectoScrumFacade proyectoScrumFacade;
//    
//    
//    
//    @ManagedProperty(value = "#{loginBean}")
//    protected LoginBean loginBean;
//    
//  
//    
//    private String name_project;
//
//    private Collection<TareaScrum> task_list;
//    
//    private ArrayList<Status> status = new ArrayList<>();
//    
//    private String  tareas;
//    
//    private String newStatus;
//    
//    @PostConstruct
//    public void init() {
//              
//        Gson gson = new Gson();
//       
//        byte [] json = loginBean.selectedProject.getEstados();
//        
//        String str = new String(json, StandardCharsets.UTF_8);
//
//        
//        java.lang.reflect.Type token = new TypeToken<Collection<Status>>() {}.getType();
//        status = gson.fromJson(str, status.getClass());
//        
//        
//        this.tareas = str;
//        
//        
//        name_project = loginBean.selectedProject.getNombre();
//        
////        TareaScrum tarea = new TareaScrum();
////        tarea.setNombre("tarea1");
//        //loginBean.selectedProject.getTareaScrumCollection().add(tarea); //Añado una tarea porque no tiene ninguna
//        task_list = loginBean.selectedProject.getTareaScrumCollection();
//      
//        
//        
//       
//       
//                            
//    }
//
//    public String getNewStatus() {
//        return newStatus;
//    }
//
//    public void setNewStatus(String newStatus) {
//        this.newStatus = newStatus;
//    }
//
//    public String getTareas() {
//        return tareas;
//    }
//
//    public void setTareas(String tareas) {
//        this.tareas = tareas;
//    }
//
//    public TareaScrumFacade getTareaScrumFacade() {
//        return tareaScrumFacade;
//    }
//
//    public void setTareaScrumFacade(TareaScrumFacade tareaScrumFacade) {
//        this.tareaScrumFacade = tareaScrumFacade;
//    }
//
//    public ProyectoScrumFacade getProyectoScrumFacade() {
//        return proyectoScrumFacade;
//    }
//
//    public void setProyectoScrumFacade(ProyectoScrumFacade proyectoScrumFacade) {
//        this.proyectoScrumFacade = proyectoScrumFacade;
//    }
//
//    public LoginBean getLoginBean() {
//        return loginBean;
//    }
//
//    public void setLoginBean(LoginBean loginBean) {
//        this.loginBean = loginBean;
//    }
//
//    public String getName_project() {
//        return name_project;
//    }
//
//    public void setName_project(String name_project) {
//        this.name_project = name_project;
//    }
//
//    public Collection<TareaScrum> getTask_list() {
//        return task_list;
//    }
//
//    public void setTask_list(Collection<TareaScrum> task_list) {
//        this.task_list = task_list;
//    }
//
//    public ArrayList<Status> getStatus() {
//        
//        Gson gson = new Gson();
//        byte [] json = loginBean.selectedProject.getEstados();
//        
//        String str = new String(json, StandardCharsets.UTF_8);
//
//        
//        java.lang.reflect.Type token = new TypeToken<Collection<Status>>() {}.getType();
//        status = gson.fromJson(str, status.getClass());
//        return status;
//    }
//
//    public void setStatus(ArrayList<Status> status) {
//        this.status = status;
//    }
//
//    public void addStatus(){
//        
//        new Status( newStatus, status.size());
//        //loginBean.selectedProject.getEstados().
//    }
//    
//    
//  
//   
//
// 
//
//  
//
//}
