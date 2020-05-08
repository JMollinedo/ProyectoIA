/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author jmoll
 */
public class Pelicula {
    public static final int NO = 0;
    public static final int YES = 50;

    private String color;
    private String num_critic_for_reviews;
    private String duration;
    private String gross;
    private List<String> genres;
    private String movie_title;
    private String num_voted_users;
    private String cast_total_facebook_likes;
    private String facenumber_in_poster;
    private List<String> plot_keywords;
    private String movie_imdb_link;
    private String num_user_for_reviews;
    private String language;
    private String country;
    private String content_rating;
    private String budget;
    private String title_year;
    private String imdb_score;
    private String aspect_ratio;
    private String movie_facebook_likes;
    private String director;
    private String director_likes;
    private String actor1;
    private String actor1_likes;
    private String actor2;
    private String actor2_likes;
    private String actor3;
    private String actor3_likes;
    private int valorizacion;

    private boolean isBlank(String string) {
        return string == null || string.trim().isEmpty();
    }
    private String castString(String s){
        return isBlank(s)?null:s;
    }

    public Pelicula(String pelicula) throws NumberFormatException {
        if(pelicula.contains("avatar")){
            int i = 1;
        }
        String[] parts = decodeLine(pelicula); // pelicula.split(",");
        color = castString(parts[0]);
        num_critic_for_reviews = castString(parts[2]);
        duration = castString(parts[3]);
        gross = castString(parts[8]);
        genres = Arrays.stream(parts[9].split("\\|")).filter(p -> !isBlank(p)).collect(Collectors.toList());
        
        movie_title = castString(parts[11]);
        num_voted_users = castString(parts[12]);
        cast_total_facebook_likes = castString(parts[13]);
        facenumber_in_poster = castString(parts[15]);
        plot_keywords = Arrays.stream(parts[16].split("\\|")).filter(p -> !isBlank(p)).collect(Collectors.toList());
        movie_imdb_link = castString(parts[17]);
        num_user_for_reviews = castString(parts[18]);
        language = castString(parts[19]);
        country = castString(parts[20]);
        content_rating = castString(parts[21]);
        budget = castString(parts[22]);
        title_year = castString(parts[23]);
        imdb_score = castString(parts[25]);
        aspect_ratio = castString(parts[26]);
        movie_facebook_likes = castString(parts[27]);

        director = castString(parts[1]);
        director_likes = castString(parts[4]);

        actor1 = castString(parts[10]);
        actor1_likes = castString(parts[7]);

        actor2 = castString(parts[6]);
        actor2_likes = castString(parts[24]);

        actor3 = castString(parts[14]);
        actor3_likes = castString(parts[5]);
    }
    private Pelicula(){}

    public Pelicula copy(){
        Pelicula p = new Pelicula();
        p.color = this.getColor();
        p.num_critic_for_reviews = this.getNum_critic_for_reviews();
        p.duration = this.getDuration();
        p.gross = this.getGross();
        p.genres = this.getGenres();
        p.movie_title = this.getMovie_title();
        p.num_voted_users = this.getNum_voted_users();
        p.cast_total_facebook_likes = this.getCast_total_facebook_likes();
        p.facenumber_in_poster = this.getFacenumber_in_poster();
        p.plot_keywords = this.getPlot_keywords();
        p.movie_imdb_link = this.getMovie_imdb_link();
        p.num_user_for_reviews = this.getNum_user_for_reviews();
        p.language = this.getLanguage();
        p.country = this.getCountry();
        p.content_rating = this.getContent_rating();
        p.budget = this.getBudget();
        p.title_year = this.getTitle_year();
        p.imdb_score = this.getImdb_score();
        p.aspect_ratio = this.getAspect_ratio();
        p.movie_facebook_likes = this.getMovie_facebook_likes();
        p.director = this.getDirector();
        p.director_likes = this.getDirector_likes();
        p.actor1 = this.getActor1();
        p.actor1_likes = this.getActor1_likes();
        p.actor2 = this.getActor2();
        p.actor2_likes = this.getActor2_likes();
        p.actor3 = this.getActor3();
        p.actor3_likes = this.getActor3_likes();
        p.valorizacion = this.getValorizacion();
        return p;
    }

    public String getDecada(){
        int y = Integer.parseInt(Optional.ofNullable(title_year).orElse("0"));
        y = Math.floorDiv(y,10)*10;
        return Integer.toString(y);
    }
    public String getDiscreteScore(){
        return Integer.toString((int)Math.floor(getScore()));
    }
    public double getScore(){
        return Double.parseDouble(Optional.ofNullable(imdb_score).orElse("0"));
    }

    public List<String> listFromName(String s){
        switch (s){
            case "genres": return getGenres();
            case "plot_keywords": return getPlot_keywords();
            case "actors": return getActors();
            default: return new ArrayList();
        }
    }

    public String fromName(String s){
        switch (s){
            case "color": return getColor();
            case "language": return getLanguage();
            case "country": return getCountry();
            case "content_rating": return getContent_rating();
            case "decade": return getDecada();
            case "score": return getDiscreteScore();
            case "director": return getDirector();
            default: return null;
        }
    }

    public String getColor() { return color; }

    public String getNum_critic_for_reviews() { return num_critic_for_reviews; }

    public String getDuration() { return duration; }

    public String getGross() { return gross; }

    public List<String> getGenres() { return new ArrayList(genres); }

    public String getMovie_title() { return movie_title; }

    public String getNum_voted_users() { return num_voted_users; }

    public String getCast_total_facebook_likes() { return cast_total_facebook_likes; }

    public String getFacenumber_in_poster() { return facenumber_in_poster; }

    public List<String> getPlot_keywords() { return new ArrayList(plot_keywords); }

    public String getMovie_imdb_link() { return movie_imdb_link; }

    public String getNum_user_for_reviews() { return num_user_for_reviews; }

    public String getLanguage() { return language; }

    public String getCountry() { return country; }

    public String getContent_rating() { return content_rating; }

    public String getBudget() { return budget; }

    public String getTitle_year() { return title_year; }

    public String getImdb_score() { return imdb_score; }

    public String getAspect_ratio() { return aspect_ratio; }

    public String getMovie_facebook_likes() { return movie_facebook_likes; }

    public String getDirector() { return director; }

    public String getDirector_likes() { return director_likes; }

    public List<String> getActors(){
        List<String> as = new ArrayList();
        if(Optional.ofNullable(actor1).isPresent()) as.add(actor1);
        if(Optional.ofNullable(actor2).isPresent()) as.add(actor2);
        if(Optional.ofNullable(actor3).isPresent()) as.add(actor3);
        return as;
    }

    public String getActor1() { return actor1; }

    public String getActor1_likes() { return actor1_likes; }

    public String getActor2() { return actor2; }

    public String getActor2_likes() { return actor2_likes; }

    public String getActor3() { return actor3; }

    public String getActor3_likes() { return actor3_likes; }

    public int getValorizacion() { return valorizacion; }

    public void setValorizacion(int valorizacion){
        this.valorizacion = valorizacion;
    }

    public void calcValorazacion(double media){
        double d = getScore();
        if(d < media) valorizacion = NO;
        else valorizacion = YES;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pelicula pelicula = (Pelicula) o;
        return Objects.equals(color, pelicula.color) &&
                Objects.equals(num_critic_for_reviews, pelicula.num_critic_for_reviews) &&
                Objects.equals(duration, pelicula.duration) &&
                Objects.equals(gross, pelicula.gross) &&
                listEq(genres, pelicula.genres) &&
                Objects.equals(movie_title, pelicula.movie_title) &&
                Objects.equals(num_voted_users, pelicula.num_voted_users) &&
                Objects.equals(cast_total_facebook_likes, pelicula.cast_total_facebook_likes) &&
                Objects.equals(facenumber_in_poster, pelicula.facenumber_in_poster) &&
                listEq(plot_keywords, pelicula.plot_keywords) &&
                Objects.equals(movie_imdb_link, pelicula.movie_imdb_link) &&
                Objects.equals(num_user_for_reviews, pelicula.num_user_for_reviews) &&
                Objects.equals(language, pelicula.language) &&
                Objects.equals(country, pelicula.country) &&
                Objects.equals(content_rating, pelicula.content_rating) &&
                Objects.equals(budget, pelicula.budget) &&
                Objects.equals(title_year, pelicula.title_year) &&
                Objects.equals(imdb_score, pelicula.imdb_score) &&
                Objects.equals(aspect_ratio, pelicula.aspect_ratio) &&
                Objects.equals(movie_facebook_likes, pelicula.movie_facebook_likes) &&
                Objects.equals(director, pelicula.director) &&
                Objects.equals(director_likes, pelicula.director_likes) &&
                Objects.equals(actor1, pelicula.actor1) &&
                Objects.equals(actor1_likes, pelicula.actor1_likes) &&
                Objects.equals(actor2, pelicula.actor2) &&
                Objects.equals(actor2_likes, pelicula.actor2_likes) &&
                Objects.equals(actor3, pelicula.actor3) &&
                Objects.equals(actor3_likes, pelicula.actor3_likes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, num_critic_for_reviews, duration, gross, genres, movie_title, num_voted_users, cast_total_facebook_likes, facenumber_in_poster, plot_keywords, movie_imdb_link, num_user_for_reviews, language, country, content_rating, budget, title_year, imdb_score, aspect_ratio, movie_facebook_likes, director, director_likes, actor1, actor1_likes, actor2, actor2_likes, actor3, actor3_likes);
    }

    public static String listToString(List list, String separador){
        StringBuilder stringBuilder = new StringBuilder();
        list.forEach(i -> stringBuilder.append(i.toString()).append(separador));
        return  stringBuilder.toString();
    }

    private Boolean listEq(List a, List b){
        if(a.size() != b.size()) return false;
        for (int i = 0; i < a.size(); i++){
            if(!b.get(i).equals(a.get(i))) return false;
        }
        return true;
    }

    private String[] decodeLine(String line){
        List<String> ls = new ArrayList();
        StringBuilder sb = new StringBuilder("");
        boolean inquot = false;
        for (char c:
                line.toCharArray()) {
            if(c == '"') inquot = !inquot;
            else if(inquot) sb.append(c);
            else if(c != ',') sb.append(c);
            else{
                ls.add(sb.toString().trim());
                sb.setLength(0);
                sb = new StringBuilder();
            }
        }
        ls.add(sb.toString());
        return ls.toArray(new String[ls.size()]);
    }

    public static boolean inList(List<String> lista, String val){
        return lista.stream().filter(s -> s.contains(val)).findFirst().isPresent();
    }
}
