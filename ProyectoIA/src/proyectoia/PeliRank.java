/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jmoll
 */
public class PeliRank {
    private Pelicula pelicula;
    private Map<Integer,List<Double>> probabilidades;

    public PeliRank(Pelicula pelicula){
        this.pelicula = pelicula;
        this.probabilidades = new HashMap();
        probabilidades.put(Pelicula.NO,new ArrayList());
        probabilidades.put(Pelicula.YES,new ArrayList());
    }

    public double probabilidad(int valoracion){
        int fl = Pelicula.YES;
        if(valoracion == Pelicula.YES) fl = Pelicula.NO;
        double num = Math.exp(sumLog(probabilidades.get(valoracion)));
        double den = Math.exp(sumLog(probabilidades.get(fl))) + num;
        return  num/den;
    }

    public double sumLog(List<Double> list){
        double c = new Double(1)/new Double(Long.MAX_VALUE);
        return list
                .stream()
                .map(i -> i==0?c:i)
                .map(i -> Math.log(i))
                .reduce(Double::sum)
                .orElse(Math.log(c)*16);
    }
    public void addCaracter(int valorizacion, double prob){
        probabilidades.get(valorizacion).add(prob);
    }

    public void addCaracter(Nudo nudo){
        addCaracter(Pelicula.NO,nudo.getDistroCondicional(Pelicula.NO));
        addCaracter(Pelicula.YES,nudo.getDistroCondicional(Pelicula.YES));
    }

    public Pelicula getPelicula() {
        return pelicula.copy();
    }
}
