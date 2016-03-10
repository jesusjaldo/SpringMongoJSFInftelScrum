/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo.bean;


import ejemplo.collection.Projects;
import ejemplo.collection.Users;
import ejemplo.service.UsersService;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author inftel20
 */
@Component
@Scope("session")
public class LoginBean implements Serializable{

    
    @Autowired
    UsersService usersService;

    protected boolean sesion;
    protected Users user;
    protected String image;
    protected Projects selectedProject;
    
    
    
    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }
    
    @PostConstruct
    public void init(){
        this.sesion = false;
        this.user = new Users();
    }

    public boolean isSesion() {
        return sesion;
    }

    public void setSesion(boolean sesion) {
        this.sesion = sesion;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Projects getSelectedProject() {
        //return proyectoScrumFacade.find(selectedProject.getIdProyecto());
        return selectedProject;
    }

    public void setSelectedProject(Projects selectedProject) {
        this.selectedProject = selectedProject;
    }
    
    
    public String anadir(){
        FacesContext context = FacesContext.getCurrentInstance();
        String nombre = context.getExternalContext().getRequestParameterMap().get("nombre");
        String imagen = context.getExternalContext().getRequestParameterMap().get("imagen");
        String email = context.getExternalContext().getRequestParameterMap().get("email");
        
       user.setNombre(nombre);
       user.setEmail(email);
        
        Users findByEmail = usersService.findByEmail(email);
        if(findByEmail == null){
           usersService.createUser(user);
           Users findByEmail1 = usersService.findByEmail(email);
           user = findByEmail1;
        }else{
            user = findByEmail;
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
        this.user = usersService.findByEmail(this.user.getEmail());
    }
    
}
