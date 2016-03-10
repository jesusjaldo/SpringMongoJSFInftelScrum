/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo.bean;

import ejemplo.collection.Projects;
import ejemplo.collection.Status;
import ejemplo.collection.Task;
import ejemplo.service.ProjectsService;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.apache.commons.io.IOUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author RMA
 */
@Component
@Scope("request")
public class TaskBean implements Serializable{
 
    @Autowired
    ProjectsService projectsService;

    @Autowired
    LoginBean loginBean;

    @Autowired
    DashboardView dashboardView;

    protected String titulo;
    protected String descripcion;
    protected String estadoSeleccionado;
    protected Date tiempo;
    protected UploadedFile file;
    protected List<Status> estados;

    /**
     * Creates a new instance of TaskBean
     */
    public TaskBean() {

    }

    @PostConstruct
    public void init() {

        
        Projects selectedProject = loginBean.getSelectedProject();
        estados = selectedProject.getEstados();
//        byte[] estadoPro = selectedProject.getEstados();
//        if(estadoPro != null){
//            try {
//                InputStream input = new ByteArrayInputStream(estadoPro);
//                Gson g = new Gson();
//                BufferedReader b;
//                b = new BufferedReader(new InputStreamReader(input, "UTF-8"));
//                Estados[] fromJson = g.fromJson(b, Estados[].class);
//                estados = fromJson;
//            } catch (UnsupportedEncodingException ex) {
//                Logger.getLogger(TaskBean.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstadoSeleccionado() {
        return estadoSeleccionado;
    }

    public void setEstadoSeleccionado(String estadoSeleccionado) {
        this.estadoSeleccionado = estadoSeleccionado;
    }

    public Date getTiempo() {
        return tiempo;
    }

    public void setTiempo(Date tiempo) {
        this.tiempo = tiempo;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public List<Status> getEstados() {
        return estados;
    }

    public void setEstados(List<Status> estados) {
        this.estados = estados;
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public String login() throws IOException {

        Task t = new Task();
        String fichero;
        Task editTarea = null;

        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message;
        boolean loggedIn = true;

        Timestamp insertarTiempo;
        insertarTiempo = new Timestamp(tiempo.getTime());

        t.setNombre_tarea(titulo);
        t.setDescription(descripcion);
        t.setEstado_tarea(estadoSeleccionado);
        t.setTiempo_estimado(insertarTiempo.toString());
        Calendar cal = Calendar.getInstance();
        t.setFecha_ini(cal.getTime().toString());
        t.setId_tarea(String.valueOf(System.currentTimeMillis()));
        t.setNombre_usuario(loginBean.user.getNombre());
        if (!file.getFileName().equals("")) {

            byte[] bytes = IOUtils.toByteArray(file.getInputstream());
            
            t.setFichero("hay que añadir el archivo");
            t.setNombre_fichero(file.getFileName());
        }
        Projects selectedProject = loginBean.getSelectedProject();
        
        
        List<Task> tareas = selectedProject.getTareas();
        
        if(tareas == null){
            List<Task> tareasInsertar = new ArrayList<>();
            tareasInsertar.add(t);
            selectedProject.setTareas(tareasInsertar);
        }else{
            selectedProject.getTareas().add(t);
        }
        
        
        dashboardView.RefreshDash();

        projectsService.editProjects(selectedProject);
        loginBean.setSelectedProject(selectedProject);
        message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Tarea", "La tarea se ha creado correctamente");
        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("loggedIn", loggedIn);

        return "";

    }
    
    public void editar(Task task){
        List<Task> tareas = loginBean.selectedProject.getTareas();
        for (Task t : tareas) {
            if(t.getId_tarea().equals(task.getId_tarea())){
                tareas.remove(t);
                tareas.add(t);
                projectsService.editProjects(loginBean.selectedProject);
            }
        }
    }

}
