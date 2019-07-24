/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.*;

@ManagedBean
public class ChartView implements Serializable {

    private LineChartModel animatedModel1;
    private BarChartModel animatedModel2;
    private PieChartModel pieModel1;
    public static ArrayList<Detalle>obj = new ArrayList<>();
    
    @PostConstruct
    public void init() {
        //obj.add(new Detalle("hola",1));
        createAnimatedModels();
        createPieModel1();
    }



   
    

    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public PieChartModel getPieModel1() {
        return pieModel1;
    }

    public LineChartModel getAnimatedModel1() {
        return animatedModel1;
    }

    public BarChartModel getAnimatedModel2() {
        return animatedModel2;
    }

    private void createPieModel1() {
        pieModel1 = new PieChartModel();
        for (Detalle obj1 : obj) {
            pieModel1.set(obj1.getNombre(),obj1.getValor());
        }

        pieModel1.setTitle("Reporte en Pastel");
        pieModel1.setLegendPosition("w");
        pieModel1.setShadow(false);
    }

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();

        ChartSeries boys = new ChartSeries();
        boys.setLabel("Ciuu");        
        for (Detalle obj1 : obj) {
            boys.set(obj1.getNombre(),obj1.getValor());
        }
        

        model.addSeries(boys);

        return model;
    }

    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();

        LineChartSeries series1 = new LineChartSeries();
        //series1.setLabel("Ciuu");

       //for (Detalle obj1 : obj) {
        //    series1.set(obj1.getNombre(),obj1.getValor());
        //}

        model.addSeries(series1);
        return model;
    }

    private void createAnimatedModels() {
        animatedModel1 = initLinearModel();
        animatedModel1.setTitle("Reporte Lineal");
        animatedModel1.setAnimate(true);
        animatedModel1.setLegendPosition("se");
        Axis yAxis = animatedModel1.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10);

        animatedModel2 = initBarModel();
        animatedModel2.setTitle("Reporte en Barras");
        animatedModel2.setAnimate(true);
        animatedModel2.setLegendPosition("ne");
        yAxis = animatedModel2.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(220000000);
    }
    
}
