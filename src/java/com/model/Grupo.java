/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import com.controlador.Conexion;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Lenovo 330S
 */
@Named(value = "grupo")
@RequestScoped
public class Grupo implements Serializable{
    private String id;
    private String nombre;
    private List nom;

    @PostConstruct
    public void inicializador() { 
        setNom(new ArrayList<>());
        Conexion c = new Conexion();
        ResultSet rs = c.Consulta("SELECT * FROM dim_grupo");
        try {
            while (rs.next()) {
                getNom().add(rs.getString("nombre"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Provincia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
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
     * @return the nom
     */
    public List getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(List nom) {
        this.nom = nom;
    }

    
    
}
