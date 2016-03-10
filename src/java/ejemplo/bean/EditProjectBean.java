/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo.bean;

import ejemplo.collection.Projects;
import ejemplo.collection.Status;
import ejemplo.service.ProjectsService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author inftel20
 */
@Component
@Scope("request")
public class EditProjectBean implements Serializable {

    @Autowired
    ProjectsService projectsService;

    @Autowired
    LoginBean loginBean;

    Projects ps;
    protected List<Status> status;

    public EditProjectBean() {
    }

    @PostConstruct
    public void init() {
        //pasar a string el id del proyecto
        ps = loginBean.selectedProject;
        status = ps.getEstados();

    }

    public ProjectsService getProjectsService() {
        return projectsService;
    }

    public void setProjectsService(ProjectsService projectsService) {
        this.projectsService = projectsService;
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public Projects getPs() {
        return ps;
    }

    public void setPs(Projects ps) {
        this.ps = ps;
    }

    public List<Status> getStatus() {
        return status;
    }

    public void setStatus(List<Status> status) {
        this.status = status;
    }

   

    public String doEditProject() {
        this.ps.setEstados(status);
        this.projectsService.editProjects(ps);
        return "myProjects";

    }
}
