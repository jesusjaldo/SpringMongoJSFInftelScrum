/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webSocket;

import com.google.gson.Gson;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import ejemplo.collection.Chat;
import ejemplo.collection.MessageChat;
import java.io.Serializable;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aitorpagan
 */
public class ProjectChat implements Serializable {
    
    MongoClient mongo;
    DB db;
    DBCollection coleccion;

    private Gson gson = new Gson();
//    protected String projectId;
//    protected List<MessageChat> mychat = new ArrayList<>();
    private String projectId;
    public Chat mychat;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public ProjectChat() {

    }

    public ProjectChat(String projectId) throws UnknownHostException {
        this.mychat = new Chat();
        
        mongo = new MongoClient(new ServerAddress("127.0.0.1", 27017));
        db = mongo.getDB("test");
        this.projectId = projectId;
        coleccion = db.getCollection("chat");
        DBCursor find = coleccion.find(new BasicDBObject("projectId", projectId));
        if(find.hasNext()){
        DBObject myobje = find.next();
        this.mychat.setId(myobje.get("_id").toString());
        this.mychat.setProjectId(myobje.get("projectId").toString());
        String chat = myobje.get("mychat").toString();
        this.mychat.setMychat(gson.fromJson(chat, mychat.getMychat().getClass()));
        
        }else{
            ArrayList<MessageChat> mes = new ArrayList<>();
            mes.add(new MessageChat("System", "Se ha creado el proyecto", new Date().toGMTString()));
            mychat.setId(projectId);
            mychat.setProjectId(projectId);
            mychat.setMychat(mes);
            BasicDBList shat = gson.fromJson(gson.toJson(mes), BasicDBList.class);
            BasicDBObject basicDBObject = new BasicDBObject("_id", this.mychat.getId()).append("projectId", this.mychat.getProjectId()).append("mychat", shat);
            coleccion.insert(basicDBObject);
        }

        
        mongo.close();

    }

    public Chat getMychat() {
        return mychat;
    }

    public void setMychat(Chat mychat) {
        this.mychat = mychat;
    }

    public void addMessageToChat(String message) {
        MessageChat mc = gson.fromJson(message, MessageChat.class);
        mychat.getMychat().add(mc);
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

    public void saveChat() {        
        try {
            mongo = new MongoClient(new ServerAddress("127.0.0.1", 27017));
        } catch (UnknownHostException ex) {
            Logger.getLogger(ProjectChat.class.getName()).log(Level.SEVERE, null, ex);
        }
        db = mongo.getDB("test");
        
        coleccion = db.getCollection("chat");
        BasicDBList dblist = gson.fromJson(gson.toJson(this.mychat.getMychat()), BasicDBList.class);
        BasicDBObject myobje = new BasicDBObject("_id", this.mychat.getId()).append("projectId", this.mychat.getProjectId()).append("mychat", dblist);
        coleccion.save(myobje);
//        String toByteArray = gson.toJson(mychat);
//        byte[] newchat = toByteArray.getBytes();
//        ps.setChat(newchat);
//        proyectoScrumFacade.edit(ps);
        mongo.close();
    }

}
