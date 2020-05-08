/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoia;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 *
 * @author jmoll
 */
public class Naive {
    private Queue<Pelicula> muestra;
    private List<Pelicula> poblacion;
    private int minSize;
    private int maxSize;
    private List<Pelicula> yesInput;

    public int getMinSize() { return minSize; }
    public int getMaxSize() { return maxSize; }
    public List<Pelicula> getMuestra() { return muestra.stream().collect(Collectors.toList()); }
    public void addMuestra(Pelicula pelicula){
        muestra.add(pelicula);
        if(muestra.size() > maxSize) muestra.remove();
    }
    public void addYesInput(Pelicula pelicula){
        yesInput.add(pelicula);
    }
    public boolean inYesInput(Pelicula p){
        return yesInput
                .stream()
                .anyMatch(i -> 
                        i.getMovie_title().equals(p.getMovie_title())
                                && Optional
                                        .ofNullable(i.getTitle_year())
                                        .orElse("")
                                        .equals(Optional
                                                .ofNullable(p.getTitle_year())
                                                .orElse("")
                                    )
                    );
                
    }
    public List<Pelicula> getYesInput(){
        return new ArrayList(yesInput);
    }
    
    private List<Pelicula> quitarDup(List<Pelicula> peliculas){
        List<Pelicula> peli = new ArrayList();
        for(Pelicula p : peliculas){
            if(!peli
                    .stream()
                    .anyMatch(i -> 
                            i.getMovie_title().equals(p.getMovie_title())
                            && Optional
                                    .ofNullable(i.getTitle_year())
                                    .orElse("")
                                    .equals(Optional
                                            .ofNullable(p.getTitle_year())
                                            .orElse("")
                                    )
                    ))
                peli.add(p.copy());
        }
        return peli;
    }

    public Naive(List<Pelicula> peliculas){
        poblacion = quitarDup(peliculas);
        List temp = poblacion
                .stream()
                .map(p -> p.getScore())
                .collect(Collectors.toList());
        minSize = Calculadora.muestraMin(temp);
        maxSize = Calculadora.muestraMax(temp);
        List<Pelicula> aux = Calculadora.genMuestra(poblacion,minSize);
        temp = aux
                .stream()
                .map(p -> p.getScore())
                .collect(Collectors.toList());
        double media = Calculadora.media(temp);
        aux.forEach(p -> p.calcValorazacion(media));
        muestra = new LinkedList(aux);
        yesInput = new ArrayList();
    }

    public List<Pelicula> top50(){
        return getTop(poblacion,50);
    }

    public List<Pelicula> buscar (String campo, String valor){
        switch (campo){
            case "genres":
            case "plot_keywords":
            case "actors": return buscar1(campo,valor);
            case "color":
            case "language":
            case "country":
            case "content_rating":
            case "decade":
            case "score":
            case "director": return buscar2(campo,valor);
            default: return new ArrayList();
        }
    }

    private List<Pelicula> buscar2(String campo, String valor){
        List l = poblacion
                .stream()
                .filter(p -> p.fromName(campo).contains(valor))
                .collect(Collectors.toList());
        return getTop(l,Integer.MAX_VALUE);
    }
    private List<Pelicula> buscar1(String campo, String valor){
        List l = poblacion
                .stream()
                .filter(p -> Pelicula.inList(p.listFromName(campo),valor))
                .collect(Collectors.toList());
        return getTop(l,Integer.MAX_VALUE);
    }

    private List<Pelicula> getTop(List<Pelicula> base, int limt){
        List<Nudo> color = colorDist();
        List<Nudo> langauge = languageDist();
        List<Nudo> country = countryDist();
        List<Nudo> contentRating = contentRatingDist();
        List<Nudo> decade = decadeDist();
        List<Nudo> director = directorDist();
        List<Nudo> genres = genresDist();
        List<Nudo> keywords = keywordsDist();
        List<Nudo> actors = actorsDist();

        List<PeliRank> list = new ArrayList();
        for (Pelicula p:
             base ) {
            PeliRank pr = new PeliRank(p.copy());
            pr.addCaracter(getNudo(color, Optional.ofNullable(p.getColor()).orElse("")));
            pr.addCaracter(getNudo(langauge, Optional.ofNullable(p.getLanguage()).orElse("")));
            pr.addCaracter(getNudo(country, Optional.ofNullable(p.getCountry()).orElse("")));
            pr.addCaracter(getNudo(contentRating, Optional.ofNullable(p.getContent_rating()).orElse("")));
            pr.addCaracter(getNudo(decade, Optional.ofNullable(p.getDecada()).orElse("")));
            pr.addCaracter(getNudo(director, Optional.ofNullable(p.getDirector()).orElse("")));
            List<String> aux1 = p.getGenres();
            for(String genero: aux1){
                pr.addCaracter(getNudo(genres,Optional.ofNullable(genero).orElse("")));
            }
            aux1 = p.getPlot_keywords();
            for(String keyw: aux1){
                pr.addCaracter(getNudo(keywords,Optional.ofNullable(keyw).orElse("")));
            };
            aux1 = p.getActors();
            for(String actor: aux1){
                pr.addCaracter(getNudo(actors,Optional.ofNullable(actor).orElse("")));
            };
            list.add(pr);
        }
        return list
                .stream()
                /*.peek(it -> 
                        System.out.println(
                                it.getPelicula().getMovie_title()
                                        + "\t" + it.getPelicula().getTitle_year()
                                        + "\t" + it.probabilidad(Pelicula.NO)
                        )
                )*/
                .sorted(Comparator.comparingDouble(a -> a.probabilidad(Pelicula.NO)))
                .limit(limt)
                .parallel()
                //.peek(it -> System.out.println(it.probabilidad(Pelicula.NO)))
                .map(p-> p.getPelicula())
                .collect(Collectors.toList());
    }
    
    private static Nudo getNudo(List<Nudo> nudos, String value){
        return nudos
                .stream()
                .filter(n -> n.getValue().equals(value))
                .findFirst()
                .orElse(null);
    }

    private List<Nudo> colorDist(){ return distConjunta("color"); }
    private List<Nudo> languageDist(){ return distConjunta("language"); }
    private List<Nudo> countryDist(){ return distConjunta("country"); }
    private List<Nudo> contentRatingDist(){return distConjunta("content_rating");}
    private List<Nudo> decadeDist(){return distConjunta("decade");}
    private List<Nudo> directorDist(){return distConjunta("director");}
    private List<Nudo> genresDist(){return distConjuntaList("genres");}
    private List<Nudo> keywordsDist(){return distConjuntaList("plot_keywords");}
    private List<Nudo> actorsDist(){return distConjuntaList("actors");}

    private List<Nudo> distConjunta(String campo){
        //P(rating|R)
        List<String> pop =  poblacion
                .stream()
                .parallel()
                .map(i -> Optional
                        .ofNullable(i.fromName(campo))
                        .orElse(""))
                .distinct()
                .collect(Collectors.toList());
        List<String> no =  muestra
                .stream()
                .parallel()
                .filter(p -> p.getValorizacion() == Pelicula.NO)
                .map(i -> Optional
                        .ofNullable(i.fromName(campo))
                        .orElse(""))
                .collect(Collectors.toList());
        List<String> yes =  muestra
                .stream()
                .parallel()
                .filter(p -> p.getValorizacion() == Pelicula.YES)
                .map(i -> Optional
                        .ofNullable(i.fromName(campo))
                        .orElse(""))
                .collect(Collectors.toList());
        List<Nudo> nudos = new ArrayList();
        for (String o:
                pop) {
            Nudo nudo = new Nudo(o);
            nudo.setCuentaNo(no.stream().filter(a -> a.equals(o)).count());
            nudo.setCuentaYes(yes.stream().filter(a -> a.equals(o)).count());
            nudos.add(nudo);
        }
        nudos.forEach(n -> n.setDistroCondicional(nudos));
        return nudos;
    }

    private List<String> unify(List<List<String>> lists){
        List unificada = new ArrayList();
        lists.forEach(unificada::addAll);
        return unificada;
    }
    private List<Nudo> distConjuntaList(String campo){
        List<String> pop = unify(
                poblacion
                        .stream()
                        .parallel()
                        .map(i -> i.listFromName(campo))
                        .collect(Collectors.toList()))
                .stream()
                .distinct()
                .collect(Collectors.toList());
        List<String> no = unify(
                muestra
                        .stream()
                        .parallel()
                        .filter(p -> p.getValorizacion() == Pelicula.NO)
                        .map(i -> i.listFromName(campo))
                        .collect(Collectors.toList()));
        List<String> yes = unify(
                muestra
                        .stream()
                        .filter(p -> p.getValorizacion() == Pelicula.YES)
                        .map(i -> i.listFromName(campo))
                        .collect(Collectors.toList()));
        List<Nudo> nudos = new ArrayList();
        for (String o:
                pop) {
            Nudo nudo = new Nudo(o);
            nudo.setCuentaNo(no.stream().filter(a -> a.equals(o)).count());
            nudo.setCuentaYes(yes.stream().filter(a -> a.equals(o)).count());
            nudos.add(nudo);
        }
        nudos.forEach(n -> n.setDistroCondicional(nudos));
        return nudos;
    }
}
