/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo.collection;

/**
 *
 * @author inftel19
 */

public class Task {
    
    String id_tarea;
    String nombre_tarea;
    String description;
    String estado_tarea;
    String tiempo_estimado;
    String nombre_usuario;
    String fichero;
    String nombre_fichero;
    String fecha_ini;
    String fecha_fin;

    public Task() {
    }

    public String getId_tarea() {
        return id_tarea;
    }

    public void setId_tarea(String id_tarea) {
        this.id_tarea = id_tarea;
    }

    public String getNombre_tarea() {
        return nombre_tarea;
    }

    public void setNombre_tarea(String nombre_tarea) {
        this.nombre_tarea = nombre_tarea;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEstado_tarea() {
        return estado_tarea;
    }

    public void setEstado_tarea(String estado_tarea) {
        this.estado_tarea = estado_tarea;
    }

    public String getTiempo_estimado() {
        return tiempo_estimado;
    }

    public void setTiempo_estimado(String tiempo_estimado) {
        this.tiempo_estimado = tiempo_estimado;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getFichero() {
        return fichero;
    }

    public void setFichero(String fichero) {
        this.fichero = fichero;
    }

    public String getNombre_fichero() {
        return nombre_fichero;
    }

    public void setNombre_fichero(String nombre_fichero) {
        this.nombre_fichero = nombre_fichero;
    }

    public String getFecha_ini() {
        return fecha_ini;
    }

    public void setFecha_ini(String fecha_ini) {
        this.fecha_ini = fecha_ini;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }
    
    
}
