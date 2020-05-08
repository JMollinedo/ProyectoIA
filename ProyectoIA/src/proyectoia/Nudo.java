/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoia;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author jmoll
 */
public class Nudo {
    private String value;
    private Map<Integer,Long> cuenta;
    private Map<Integer,Double> distroCondicional;

    public Nudo(String value){
        this.value = value;
        cuenta = new HashMap();
        cuenta.put(Pelicula.NO,new Long(0));
        cuenta.put(Pelicula.YES,new Long(0));
        distroCondicional = new HashMap();
        distroCondicional.put(Pelicula.NO,new Double(0));
        distroCondicional.put(Pelicula.YES,new Double(0));
    }

    public String getValue(){
        return value;
    }

    public void setCuentaNo(Long cuenta) { this.cuenta.put(Pelicula.NO,cuenta); }
    public void setCuentaYes(Long cuenta) { this.cuenta.put(Pelicula.YES,cuenta); }

    public void setDistroCondicional(List<Nudo> nudos){
        setDistroCondicional(nudos,Pelicula.NO);
        setDistroCondicional(nudos,Pelicula.YES);
    }
    private void setDistroCondicional(List<Nudo> nudos, int valoracion){
        double t = nudos
                .stream()
                .map(n -> n.getCuenta(valoracion))
                .reduce(Long::sum)
                .orElse(new Long(0));
        distroCondicional.put(valoracion, new Double(getCuenta(valoracion))/t);
    }
    public long getCuenta(int valoracion){
        if(cuenta.keySet().contains(valoracion)) return cuenta.get(valoracion);
        return -1;
    }
    public long getSum(){ return cuenta.values().stream().reduce(Long::sum).orElse(new Long(0));}
    public double getDistroCondicional(int valoracion){
        if(distroCondicional.keySet().contains(valoracion)) return distroCondicional.get(valoracion);
        return -1;
    }

    @Override
    public String toString(){
        return value
                + "," + getCuenta(Pelicula.NO)
                + "," + getCuenta(Pelicula.YES)
                + "," + getSum()
                + "," + getDistroCondicional(Pelicula.NO)
                + "," + getDistroCondicional(Pelicula.YES);
    }

    public static String listToString(List<Nudo> nudos){
        return nudos.stream().map(n -> n.toString() + "\n").collect(Collectors.joining());
    }
}
