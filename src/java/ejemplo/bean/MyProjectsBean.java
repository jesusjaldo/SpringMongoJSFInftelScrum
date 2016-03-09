/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo.bean;

import ejb.ProyectoScrumFacade;
import ejb.UsuarioScrumFacade;
import ejb.UsuyproScrumFacade;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.ProyectoScrum;
import model.UsuarioScrum;
import model.UsuyproScrum;
import org.primefaces.context.RequestContext;

/**
 *
 * @author aitorpagan
 */
@ManagedBean
@RequestScoped
public class MyProjectsBean {

    @EJB
    private UsuyproScrumFacade usuyproScrumFacade1;

    @EJB
    private ProyectoScrumFacade proyectoScrumFacade;
    @EJB
    private UsuyproScrumFacade usuyproScrumFacade;
    @EJB
    private UsuarioScrumFacade usuarioScrumFacade;

    @ManagedProperty(value = "#{loginBean}")
    protected LoginBean loginBean;

    Collection<UsuyproScrum> usuyproScrumCollection;

    protected String invitacion;
    protected String invitar;
    protected List<String> mails;
    protected Properties properties = new Properties();
    protected Session session;
    

    /**
     * Creates a new instance of MyProjectsBean
     */
    public MyProjectsBean() {

    }

    @PostConstruct
    public void init() {

        usuyproScrumCollection = loginBean.getUser().getUsuyproScrumCollection();
        mails = usuarioScrumFacade.findEmails();
        invitacion = "";
    }

    /*Getters & Setters*/
    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public Collection<UsuyproScrum> getUsuyproScrumCollection() {
        return usuyproScrumCollection;
    }

    public void setUsuyproScrumCollection(Collection<UsuyproScrum> usuyproScrumCollection) {
        this.usuyproScrumCollection = usuyproScrumCollection;
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



    public List<String> completeMail(String query) {
        List<String> results = new ArrayList<>();
        for (String result : mails) {
            if (result.contains(query)) {
                results.add(result);
            }
        }
        return results;
    }

    public String enviar(ProyectoScrum project) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        List<UsuarioScrum> usuariosInvitados = usuarioScrumFacade.findByEmail(invitacion);
        if (!usuariosInvitados.isEmpty()) {
            List<UsuyproScrum> miembros = usuyproScrumFacade1.getProjectFromUSerAndProject(project, usuariosInvitados.get(0));
            UsuyproScrum usuypro = new UsuyproScrum();
            if (miembros.isEmpty()) {
                usuypro.setIdProyecto(project);
                usuypro.setIdUsuario(usuariosInvitados.get(0));
                usuyproScrumFacade.create(usuypro);
                SendMailBean hebra = new SendMailBean(project, invitacion);
                hebra.start();
               
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Invitacion", "El usuario " + invitacion + " ha sido invitado");

            } else {
               
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Invitacion", "El usuario " + invitacion + " ya esta añadido");

            }
        } else if(invitacion==null) {
           
         message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Invitacion", " Campo vacio!  ");
        }else{
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Invitacion", "Error, no existe ningún usuario con email "+ invitacion);
        }
        FacesContext.getCurrentInstance().addMessage(null, message);

        return "myProjects";
    }

    public String goToProject(ProyectoScrum projectId) {

        System.out.println("Proyecto: " + projectId.getIdProyecto());
        loginBean.selectedProject = projectId;
        BigDecimal idProyecto = loginBean.getSelectedProject().getIdProyecto();
        loginBean.setSelectedProject(proyectoScrumFacade.find(idProyecto));
        return "manageProject";
    }

    public String goToEditProject(ProyectoScrum projectId) {

        System.out.println("Proyecto: " + projectId.getIdProyecto());
        loginBean.selectedProject = projectId;
        return "editProject";
    }

    public String deleteProject(UsuyproScrum project) {
        usuyproScrumFacade.remove(project);

        proyectoScrumFacade.remove(project.getIdProyecto());
        loginBean.getUser().getUsuyproScrumCollection().remove(project);
        loginBean.getUser().getProyectoScrumCollection().remove(project.getIdProyecto());
        return "myProjects";
    }

}
