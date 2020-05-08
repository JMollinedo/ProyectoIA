/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoia;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author jmoll
 */
public class Calculadora {
    public static int muestraMax(List<Double> list){return  tamMuestra(list,0.95);}
    public static int muestraMin(List<Double> list){return  tamMuestra(list,0.90);}
    public static List<Pelicula> genMuestra(List<Pelicula> origen, int size ){
        Random random = new Random(System.currentTimeMillis());
        List<Pelicula> peliculas = new ArrayList(origen);
        while (peliculas.size() > size){
            peliculas.remove(random.nextInt(peliculas.size()));
        }
        return peliculas;
    }
    private static int tamMuestra(List<Double> list, double precision){
        double erro = 1 - precision;
        double n = Math.pow(1.96,2);
        n *= Math.pow(desvest(list),2);
        double d = Math.pow(erro,2);
        d *= new Double(list.size()-1);
        d += n;
        n *= new Double(list.size());
        return (int)Math.ceil(n/d);
    }
    public static double media(List<Double> list){
        return list
                .stream()
                .reduce(Double::sum)
                .orElse(new Double(0))
                .doubleValue()
                / new Double(list.size()).doubleValue();
    }
    private static double desvest(List<Double> list){
        double media = media(list);
        return Math.sqrt(
                list
                        .stream()
                        .map(d -> Math.pow(d - media,2))
                        .reduce(Double::sum)
                        .orElse(new Double(0))
                        .doubleValue()
                        /new Double(list.size())
        );
    }
}
