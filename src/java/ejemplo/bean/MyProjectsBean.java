/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo.bean;

import ejemplo.collection.Projects;
import ejemplo.collection.Users;
import ejemplo.service.ProjectsService;
import ejemplo.service.UsersService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.mail.Session;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author aitorpagan
 */
@Component
@Scope("request")
public class MyProjectsBean implements Serializable {

    @Autowired
    ProjectsService projectsService;

    @Autowired
    UsersService usersService;

    @Autowired
    LoginBean loginBean;

    protected String invitacion;
    protected String invitar;
    protected List<String> mails = new ArrayList<>();
    protected Properties properties = new Properties();
    protected Session session;
    protected List<Projects> myProjects;

    /**
     * Creates a new instance of MyProjectsBean
     */
    public MyProjectsBean() {

    }

    @PostConstruct
    public void init() {
 
        myProjects = projectsService.listaProyectos(loginBean.getUser().getEmail());
        List <Users> mail = usersService.findAllUsers();
        for (Users m : mail) {
            mails.add(m.getEmail());
        }
        invitacion = "";
    }

    public List<String> completeMail(String query) {
        List<String> results = new ArrayList<>();
        for (String result : mails) {
            if (result.contains(query)) {
                results.add(result);
            }
        }
        return results;
    }

    public String enviar(Projects project) {
        FacesMessage message = null;
        Users usuariosInvitados = usersService.findByEmail(invitacion);

        if (usuariosInvitados != null) {
            if (project.getUsuarios().contains(usuariosInvitados.getEmail())) {
                project.getUsuarios().add(usuariosInvitados.getEmail());
                SendMailBean hebra = new SendMailBean(project, invitacion);
                hebra.start();
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Invitacion", "El usuario " + invitacion + " ha sido invitado");
            } else {
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Invitacion", "El usuario " + invitacion + " ya esta añadido");
            }
        } else if (invitacion == null) {

            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Invitacion", " Campo vacio!  ");
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Invitacion", "Error, no existe ningún usuario con email " + invitacion);
        }
        FacesContext.getCurrentInstance().addMessage(null, message);

        return "myProjects";
    }

    public String goToProject(Projects projectId) {

        loginBean.selectedProject = projectId;
        return "manageProject";
    }

    public String goToEditProject(Projects projectId) {
        loginBean.selectedProject = projectId;
        return "editProject";
    }

    public String deleteProject(Projects project) {

        projectsService.deleteProjects(project);
        loginBean.getUser().getLista_proyectos().remove(project);
        return "myProjects";
    }

    public ProjectsService getProjectsService() {
        return projectsService;
    }

    public void setProjectsService(ProjectsService projectsService) {
        this.projectsService = projectsService;
    }

    public UsersService getUsersService() {
        return usersService;
    }

    public void setUsersService(UsersService usersService) {
        this.usersService = usersService;
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public String getInvitacion() {
        return invitacion;
    }

    public void setInvitacion(String invitacion) {
        this.invitacion = invitacion;
    }

    public String getInvitar() {
        return invitar;
    }

    public void setInvitar(String invitar) {
        this.invitar = invitar;
    }

    public List<String> getMails() {
        return mails;
    }

    public void setMails(List<String> mails) {
        this.mails = mails;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public List<Projects> getMyProjects() {
        return myProjects;
    }

    public void setMyProjects(List<Projects> myProjects) {
        this.myProjects = myProjects;
    }

}
