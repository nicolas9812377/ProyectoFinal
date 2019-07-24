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
@Named(value = "prov")
@RequestScoped
public class Provincia implements Serializable{

    private int id;
    private String provincia;
    private List<String> prov;

    @PostConstruct
    public void inicializador() { 
        setProv(new ArrayList<>());
        Conexion c = new Conexion();
        ResultSet rs = c.Consulta("SELECT * FROM dim_provincia");
        try {
            while (rs.next()) {
                getProv().add(rs.getString("nombre"));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Provincia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the provincia
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * @param provincia the provincia to set
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    /**
     * @return the prov
     */
    public List getProv() {
        return prov;
    }

    /**
     * @param prov the prov to set
     */
    public void setProv(List prov) {
        this.prov = prov;
    }

}
