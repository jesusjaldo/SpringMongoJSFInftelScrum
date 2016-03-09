/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webSocket;

import ejemplo.collection.MessageChat;
import com.google.gson.Gson;
import ejb.ProyectoScrumFacade;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import model.ProyectoScrum;




/**
 *
 * @author aitorpagan
 */
public class ProjectChat {
    
    ProyectoScrumFacade proyectoScrumFacade = lookupProyectoScrumFacadeBean();
    private Gson gson = new Gson();    
    protected String projectId;
    protected ArrayList<MessageChat> mychat = new ArrayList<>();
    
    ProjectChat(){
    }
    
    ProjectChat(String projectId){
        
        
        this.projectId = projectId;
        ProyectoScrum ps = proyectoScrumFacade.getProject(projectId);
        byte[] oldchat = ps.getChat();
        if (oldchat == null){
            mychat.add(new MessageChat("System", "Chat Created", new Date().toString()));
        }else{
            mychat = gson.fromJson(new String(oldchat), mychat.getClass());
        }
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public ProyectoScrumFacade getProyectoScrumFacade() {
        return proyectoScrumFacade;
    }

    public void setProyectoScrumFacade(ProyectoScrumFacade proyectoScrumFacade) {
        this.proyectoScrumFacade = proyectoScrumFacade;
    }

    public ArrayList<MessageChat> getMychat() {
        return mychat;
    }

    public void setMychat(ArrayList<MessageChat> mychat) {
        this.mychat = mychat;
    }
    
    public void addMessageToChat(String message){
        MessageChat mc = gson.fromJson(message, MessageChat.class);
        mychat.add(mc);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.projectId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProjectChat other = (ProjectChat) obj;
        if (!Objects.equals(this.projectId, other.projectId)) {
            return false;
        }
        return true;
    }
    
    public void saveChat(){
        ProyectoScrum ps = proyectoScrumFacade.getProject(projectId);
        String toByteArray = gson.toJson(mychat);
        byte[] newchat = toByteArray.getBytes();
        ps.setChat(newchat);
        proyectoScrumFacade.edit(ps);
    }

    private ProyectoScrumFacade lookupProyectoScrumFacadeBean() {
        try {
            Context c = new InitialContext();
            return (ProyectoScrumFacade) c.lookup("java:global/InftelScrum/InftelScrum-ejb/ProyectoScrumFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
