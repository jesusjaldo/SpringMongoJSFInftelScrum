/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo.bean;

import ejemplo.collection.Status;
import com.google.gson.Gson;
import ejb.ProyectoScrumFacade;
import ejb.UsuarioScrumFacade;
import ejb.UsuyproScrumFacade;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import model.ProyectoScrum;
import model.UsuyproScrum;

/**
 *
 * @author 
 */
@ManagedBean
@SessionScoped
public class NewProjectBean {

    @EJB
    private UsuyproScrumFacade usuyproScrumFacade;

    @ManagedProperty(value = "#{loginBean}")
    protected LoginBean loginBean;

    @EJB
    private ProyectoScrumFacade proyectoScrumFacade;

    protected String titulo;
    protected String descripcion;
    protected ProyectoScrum proyecto;

    //Jesus
    protected int numStatus;
    protected List<Status> status;
    //

    @PostConstruct
    public void init() {
        proyecto = new ProyectoScrum();

        //Jesus
        numStatus = 0;
        status = new ArrayList<>();
        //

    }

    public String doCrearProyecto() {
        this.proyecto.setIdAdmin(loginBean.user);
        this.proyecto.setNombre(titulo);
        this.proyecto.setDescripcion(descripcion);
        Calendar fechaInicio = Calendar.getInstance();
        this.proyecto.setFechaInicio(fechaInicio.getTime());
        //JESUS
        Gson gson = new Gson();
        String stringJson = gson.toJson(status);
        byte[] arrayByteJson = stringJson.getBytes();

        String s = new String(arrayByteJson);

        System.out.println("JSON\n " + s);

        this.proyecto.setEstados(arrayByteJson);
        //hasta aqui
        ProyectoScrum p = proyecto;
        this.proyectoScrumFacade.create(proyecto);
        loginBean.user.getProyectoScrumCollection().add(p);

        List<ProyectoScrum> findProyect = proyectoScrumFacade.findProyect(loginBean.user.getIdUsuario(), titulo, descripcion);
        if (!findProyect.isEmpty()) {
            UsuyproScrum uyp = new UsuyproScrum();
            uyp.setIdUsuario(loginBean.user);
            uyp.setIdProyecto(findProyect.get(0));
            usuyproScrumFacade.create(uyp);
            loginBean.user.getUsuyproScrumCollection().add(uyp);
        }

        //JESUS
        titulo = "";
        descripcion = "";
        numStatus = 0;
        status = new ArrayList<>();
        proyecto = new ProyectoScrum();


        //
        return "myProjects";

    }

    public ProyectoScrum getProyecto() {
        return proyecto;
    }

    public void setProyecto(ProyectoScrum proyecto) {
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
