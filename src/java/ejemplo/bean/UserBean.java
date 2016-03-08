/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo.bean;

import ejemplo.collection.Users;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ejemplo.service.UsersService;
import java.io.Serializable;
import org.springframework.context.annotation.Scope;

@Component
@Scope("session")
public class UserBean implements Serializable {

    private List<Users> listaUsuarios;
    private Users usuario;
    private boolean crear;
 
    @Autowired
    private UsersService usersService;

    public UserBean() {
        crear = true;
    }

    public List<Users> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Users> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public Users getUsuario() {
        return usuario;
    }

    public void setUsuario(Users usuario) {
        this.usuario = usuario;
    }
    
    public boolean esCrearUsuario () {
        return this.crear;
    }

    @PostConstruct
    public void init() {
        this.listaUsuarios = this.usersService.findAllUsers();
        this.usuario = new Users();

     //this.users=usersService.findAll();
    }

    public String crearUsuario () {
        if (this.crear) {
            this.usersService.createUser(usuario);
        } else {
            this.usersService.editUser(usuario);
            this.crear = true;
        }        
        init();
        return "index";
    }
    
    public String borrarUsuario (Users user) {
        this.usersService.deleteUser(user);
        this.listaUsuarios.remove(user);
        return "index";
    }

    public String editarUsuario (Users user) {
        this.usuario = user;
        this.crear = false;
        return "crearUsuario";
    }


}