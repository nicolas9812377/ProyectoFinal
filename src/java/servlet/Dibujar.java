/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.controlador.Conexion;
import com.model.ChartView;
import com.model.Detalle;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lenovo 330S
 */
public class Dibujar extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 
        String provincia = (request.getParameter("provincia").equals(".. Seleccione"))? "":" and dim_provincia.nombre = '"+request.getParameter("provincia")+"' ";
        String fecha = (request.getParameter("fecha").equals(".. Seleccione"))? "":" and dim_fecha.sk_fecha = "+request.getParameter("fecha")+" ";
        String grupo = (request.getParameter("grupo").equals(".. Seleccione"))? "":" and dim_grupo.nombre = '"+request.getParameter("grupo")+"' ";
        String gastos = " sum( "+request.getParameter("gastos")+") as total ";
         ArrayList<Detalle>obj = new ArrayList<>();
        Conexion c = new Conexion();
        String sql = "select dim_ciuu.nombre,dim_ciuu.sk_ciuu, "+ gastos
                +"from detalle_hechos_hot_rest_serv,dim_ciuu,dim_fecha,dim_grupo,dim_provincia \n" +
                  "where detalle_hechos_hot_rest_serv.sk_ciuu = dim_ciuu.sk_ciuu \n" +
                  "and detalle_hechos_hot_rest_serv.sk_fecha = dim_fecha.sk_fecha \n" +
                  "and detalle_hechos_hot_rest_serv.sk_grupo = dim_grupo.sk_grupo \n" +
                  "and detalle_hechos_hot_rest_serv.sk_provincia = dim_provincia.sk_provincia \n" +
                  fecha + provincia + grupo +
                  "group by dim_ciuu.sk_ciuu\n"
                + "order by total desc " +
                  "fetch first 10 rows only";
        System.out.println(sql);
        ResultSet rs = c.Consulta(sql);
        
        try {
            while(rs.next()){
                obj.add(new Detalle(rs.getString(1),rs.getString(2),rs.getDouble(3))); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChartView.class.getName()).log(Level.SEVERE, null, ex);
        }
        ChartView.obj.clear();
        ChartView.obj=obj;
        response.sendRedirect("/ProyectoMio");
    }

    
}
