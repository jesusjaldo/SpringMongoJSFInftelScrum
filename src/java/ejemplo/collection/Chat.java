package ejemplo.collection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;

/**
 *
 * @author aitorpagan
 */
public class Chat {
    protected String id;
    
    protected String projectId;
    public ArrayList<MessageChat> mychat;
    
    public Chat(){
        mychat = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public ArrayList<MessageChat> getMychat() {
        return mychat;
    }

    public void setMychat(ArrayList<MessageChat> mychat) {
        this.mychat = mychat;
    }


    
   
    
    
    
}
