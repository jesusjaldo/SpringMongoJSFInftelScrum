/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo.collection;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
 
@Document(collection="users")
public class Users {
 
    @Id
    private String id;
 
    private String nombre;
    private String email;
    private List<Projects> lista_proyectos;
    
    public Users() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Projects> getLista_proyectos() {
        return lista_proyectos;
    }

    public void setLista_proyectos(List<Projects> lista_proyectos) {
        this.lista_proyectos = lista_proyectos;
    }

 

        
        
}