/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo.collection;

/**
 *
 * @author Asus
 */
public class Status {
    private int posicion;
    private String nombre;

    public Status(){
        
    }
    public Status(String nombre,int pos) {
        this.posicion = pos;
        this.nombre = nombre;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int pos) {
        this.posicion = pos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

  
    
}
