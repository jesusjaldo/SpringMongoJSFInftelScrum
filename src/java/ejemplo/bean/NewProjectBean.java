/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo.bean;

import ejemplo.collection.Projects;
import ejemplo.collection.Status;
import ejemplo.service.ProjectsService;
import ejemplo.service.UsersService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author 
 */
@Component
@Scope("session")
public class NewProjectBean implements Serializable{

  
     @Autowired
    ProjectsService projectsService;
    
    @Autowired
    UsersService usersService;
    
    @Autowired
    LoginBean loginBean;

    protected String titulo;
    protected String descripcion;
    protected Projects proyecto;

    //Jesus
    protected int numStatus;
    protected List<Status> status;
    //

    @PostConstruct
    public void init() {
        proyecto = new Projects();

        //Jesus
        numStatus = 0;
        status = new ArrayList<>();
        //

    }

    public String doCrearProyecto() {
        proyecto.setAdmin(loginBean.user.getEmail());
        this.proyecto.setNombre(titulo);
        this.proyecto.setDescripcion(descripcion);
        Calendar fechaInicio = Calendar.getInstance();
        proyecto.setFecha_ini(fechaInicio.getTime().toString());
//        //JESUS
//        Gson gson = new Gson();
//        String stringJson = gson.toJson(status);
//        byte[] arrayByteJson = stringJson.getBytes();
//
//        String s = new String(arrayByteJson);
//
//        System.out.println("JSON\n " + s);

        proyecto.setEstados(status);
        proyecto.setTareas(new ArrayList<>());
        List<String> users = new ArrayList<>();
        users.add(loginBean.user.getEmail());
        proyecto.setUsuarios(users);
        Projects p = proyecto;
        this.projectsService.createProjects(proyecto);

        titulo = "";
        descripcion = "";
        numStatus = 0;
        status = new ArrayList<>();
        proyecto = new Projects();


        //
        return "myProjects";

    }

    public Projects getProyecto() {
        return proyecto;
    }

    public void setProyecto(Projects proyecto) {
        this.proyecto = proyecto;
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

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public int getNumStatus() {
        return numStatus;
    }

    public void setNumStatus(int numStatus) {
        this.numStatus = numStatus;
    }

    public List<Status> getStatus() {
        return status;
    }

    public void setStatus(List<Status> status) {
        this.status = status;
    }

    public String doObtenerNumEstados() {

        status = new ArrayList<>();
        //int i = Integer.parseInt(username);
        System.out.println("numero " + numStatus);
        Status est;
        for (int j = 0; j < numStatus; j++) {
            est = new Status("", j);

            status.add(est);

        }

        System.out.println("");

        return "newProject";
    }

}
