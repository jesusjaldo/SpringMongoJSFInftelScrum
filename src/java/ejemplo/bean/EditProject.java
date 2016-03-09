/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo.bean;


import ejemplo.collection.Status;
import com.google.gson.Gson;
import ejb.ProyectoScrumFacade;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import model.ProyectoScrum;

/**
 *
 * @author inftel20
 */
@ManagedBean
@RequestScoped
public class EditProject {
    @EJB
    private ProyectoScrumFacade proyectoScrumFacade;

    /**
     * Creates a new instance of EditProject
     */
    
    @ManagedProperty(value = "#{loginBean}")
    protected LoginBean loginBean;
        
    ProyectoScrum ps;
    protected List<Status> status;
    
    protected Gson gson;
        
    public EditProject() {
    }
    
    @PostConstruct
    public void init() {
        //pasar a string el id del proyecto
        ps = proyectoScrumFacade.getProject(String.valueOf(loginBean.selectedProject.getIdProyecto()));
        byte[] estados = ps.getEstados();
        gson = new Gson();
        status = new ArrayList<>();
        status = gson.fromJson(new String(estados), status.getClass());
        
        
        
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public ProyectoScrum getPs() {
        return ps;
    }

    public void setPs(ProyectoScrum ps) {
        this.ps = ps;
    }

    public List<Status> getStatus() {
        return status;
    }

    public void setStatus(List<Status> status) {
        this.status = status;
    }

    public Gson getGson() {
        return gson;
    }

    public void setGson(Gson gson) {
        this.gson = gson;
    }
    
    
    
    public String doEditProject(){
        
        gson = new Gson();
        String stringJson = gson.toJson(status);
        byte[] arrayByteJson = stringJson.getBytes();

        String s = new String(arrayByteJson);

        System.out.println("JSON\n " + s);

        this.ps.setEstados(arrayByteJson);
        //hasta aqui
        
        this.proyectoScrumFacade.edit(ps);
        loginBean.refresh();
        //loginBean.user.getProyectoScrumCollection().remove(ps);
//        loginBean.user.getProyectoScrumCollection().add(p);
//
//        List<ProyectoScrum> findProyect = proyectoScrumFacade.findProyect(loginBean.user.getIdUsuario(), titulo, descripcion);
//        if (!findProyect.isEmpty()) {
//            UsuyproScrum uyp = new UsuyproScrum();
//            uyp.setIdUsuario(loginBean.user);
//            uyp.setIdProyecto(findProyect.get(0));
//            usuyproScrumFacade.create(uyp);
//            loginBean.user.getUsuyproScrumCollection().add(uyp);
//        }

        //JESUS
//        titulo = "";
//        descripcion = "";
//        numStatus = 0;
//        status = new ArrayList<>();
//        proyecto = new ProyectoScrum();


        //
        return "myProjects";

    }
}
