package com.model;


import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrador
 */
@Named(value = "US")
@RequestScoped 
public class Usuario {
    private String cedula;
    private int rol=2;
    private String nombre;
    private String apellido;
    private String correo;
    private String contraseña;

    /**
     * @return the cedula
     */
   
    public void setRol(int rol) {
        this.rol = rol;
    }

    public int getRol() {
        return rol;
    }

    public String getCedula() {
        return cedula;
    }

    /**
     * @param cedula the cedula to set
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the contraseña
     */
    public String getContraseña() {
        return contraseña;
    }

    /**
     * @param contraseña the contraseña to set
     */
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Usuario() {
    }
    
    public Usuario(String cedula, int rol, String nombre, String apellido, String correo, String contraseña) {
        this.cedula = cedula;
        this.rol = rol;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contraseña = contraseña;
    }
    
    public String registro(){
        Conexion c = new Conexion();
        String re = c.Ejecutar("insert into usuario values ('"+cedula+"',"+rol+",'"+nombre+"','"+apellido+"','"+correo+"','"+contraseña+"')");
        if(re.equals("Datos insertados")){
            return "index.jsf";
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"No se registro Correctamente","Ingrese datos correctos"));
        }
        return "";
    }
    
}
