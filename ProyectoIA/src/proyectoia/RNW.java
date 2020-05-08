/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author jmoll
 */
public class RNW {
    public static List<Pelicula> ReadFile(String filepath) throws IOException, NumberFormatException {
        return new BufferedReader(
                new FileReader(
                        new File(filepath)))
                .lines()
                .parallel()
                .filter(l -> !l.contains("plot_keywords"))
                .distinct()
                .map(p -> new Pelicula(p))
                .distinct()
                .collect(Collectors.toList());
    }
}
