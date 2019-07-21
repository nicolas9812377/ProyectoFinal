/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrador
 */
public class sesion extends HttpServlet {

@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       Conexion c = new Conexion();
        ResultSet rs = c.Consulta("select * from usuario where correo_usuario='" + request.getParameter("correo")+"'");
        
        System.out.println("recogiendo correo:.... "+request.getParameter("correo"));
        Usuario u = null;
        try {
            while(rs.next()){
             System.out.println("recogiendo nombre:......"+rs.getString(1));   
            u = new Usuario(rs.getString(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
            }   
                if(u.getCorreo().equals(request.getParameter("correo")) && u.getContraseña().equals(request.getParameter("passwd"))){
                        HttpSession misesion = request.getSession(true);
                        misesion.setAttribute("misesion", u);
                        response.sendRedirect("principal.jsf");
                }else{
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Usuario y Contraseña Incorrecta","Ingrese datos correctos"));
                }
                   
           
                
        } catch (SQLException ex) {
            
        } 
    }
}
