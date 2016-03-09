/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webSocket;

import java.util.Objects;
import javax.websocket.Session;

/**
 *
 * @author aitorpagan
 */
public class SocketSession {
    
    private String idproject;
    private Session session;

    public String getIdproject() {
        return idproject;
    }

    public void setIdproject(String idproject) {
        this.idproject = idproject;
    }


    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final SocketSession other = (SocketSession) obj;
        if (!Objects.equals(this.idproject, other.idproject)) {
            return false;
        }
        return true;
    }
    
    
    
}
