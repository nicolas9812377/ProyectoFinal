/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import com.controlador.Conexion;
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
@Named(value = "fecha")
@RequestScoped
public class Fecha {
    private String fecha;
    private List f;

    @PostConstruct
    public void inicializador() { 
        setF(new ArrayList<>());
        Conexion c = new Conexion();
        ResultSet rs = c.Consulta("SELECT * FROM dim_fecha");
        try {
            while (rs.next()) {
                getF().add(rs.getString("sk_fecha"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Provincia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the f
     */
    public List getF() {
        return f;
    }

    /**
     * @param f the f to set
     */
    public void setF(List f) {
        this.f = f;
    }
    
    
}
