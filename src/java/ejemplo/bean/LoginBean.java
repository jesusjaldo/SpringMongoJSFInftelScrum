/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo.bean;

import ejb.ProyectoScrumFacade;
import ejb.UsuarioScrumFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import model.ProyectoScrum;
import model.UsuarioScrum;

/**
 *
 * @author inftel20
 */
@ManagedBean
@SessionScoped
public class LoginBean {
    @EJB
    private ProyectoScrumFacade proyectoScrumFacade;
    @EJB
    private UsuarioScrumFacade usuarioScrumFacade;
    
    

    protected boolean sesion;
    protected UsuarioScrum user;
    protected String image;
    protected ProyectoScrum selectedProject;
    
    
    
    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }
    
    @PostConstruct
    public void init(){
        this.sesion = false;
        this.user = new UsuarioScrum();
    }

    public boolean isSesion() {
        return sesion;
    }

    public void setSesion(boolean sesion) {
        this.sesion = sesion;
    }

    public UsuarioScrum getUser() {
        return user;
    }

    public void setUser(UsuarioScrum user) {
        this.user = user;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ProyectoScrum getSelectedProject() {
        return proyectoScrumFacade.find(selectedProject.getIdProyecto());
    }

    public void setSelectedProject(ProyectoScrum selectedProject) {
        this.selectedProject = selectedProject;
    }
    
    
    public String anadir(){
        FacesContext context = FacesContext.getCurrentInstance();
        String nombre = context.getExternalContext().getRequestParameterMap().get("nombre");
        String imagen = context.getExternalContext().getRequestParameterMap().get("imagen");
        String email = context.getExternalContext().getRequestParameterMap().get("email");
        //String token = context.getExternalContext().getRequestParameterMap().get("token");
        String ac_token = context.getExternalContext().getRequestParameterMap().get("ac_token");
        
       user.setNombre(nombre);
       user.setEmail(email);
       //user.setToken(token);
       user.setRefreshToken(ac_token);
        
        List<UsuarioScrum> findByEmail = usuarioScrumFacade.findByEmail(email);
        if(findByEmail.isEmpty()){
           usuarioScrumFacade.create(user);
           List<UsuarioScrum> findByEmail1 = usuarioScrumFacade.findByEmail(email);
           user = findByEmail1.get(0);
        }else{
            user = findByEmail.get(0);
        }

        image = imagen;
        
        sesion = true;
        
        return ("myProjects");
    }
    
    public String salir(){
        sesion = false;
        ExternalContext fc = FacesContext.getCurrentInstance().getExternalContext();
        fc.invalidateSession();
        return "index";
    }
    
    public void refresh(){
        this.user = usuarioScrumFacade.findByEmail(this.user.getEmail()).get(0);
    }
    
}
