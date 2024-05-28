package org.example.Model.entities;

import java.io.Serializable;
import java.util.Objects;

//Pojo --> Plain Old Java Object

/**z
 * Classe que conté els atributs i metodes dels productes
 * */
public class Producte implements Serializable {

    private String nom;
    private double preu;
    private int calories;
    private boolean disponible;
    private Categoria categoria;

    /**
     * Constructor de la classe Producte
     * */
    public Producte() {
    }

    /**
     * Constructor de la classe Producte
     * */
    public Producte(String nom, double preu, int calories, boolean disponible, String categoria) {
        this.nom = nom;
        this.preu = preu;
        this.calories = calories;
        this.disponible = disponible;
        setCategoriaStr(categoria);
    }


    /**
     * Getter del nom
     * */
    public String getNom() {
        return nom;
    }

    /**
     * Getter del preu
     * */
    public double getPreu() {
        return preu;
    }

    /**
     * Getter de les calories
     * */
    public int getCalories() {
        return calories;
    }

    /**
     * Getter de la disponibilitat
     * */
    public boolean isDisponible() {
        return disponible;
    }

    /**
     * Getter de la categoria
     * */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * Setter del nom
     * */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Setter del preu
     * */
    public void setPreu(double preu) {
        this.preu = preu;
    }

    /**
     * Setter de les calories
     * */
    public void setCalories(int calories) {
        this.calories = calories;
    }

    /**
     * Setter de la disponibilitat
     * */
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    /**
     * Setter de la categoria
     * */
    public void setCategoriaStr(String categoria) {

        if (Objects.equals(categoria, "Fresc")){
            this.categoria = Categoria.Fresc;
        } else if (Objects.equals(categoria, "Processat")){
            this.categoria = Categoria.Processat;
        } else if (Objects.equals(categoria, "Cereal")){
            this.categoria = Categoria.Cereal;
        } else if (Objects.equals(categoria, "Beguda")){
            this.categoria = Categoria.Beguda;
        } else if (Objects.equals(categoria, "Congelat")){
            this.categoria = Categoria.Congelat;
        }
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public enum Categoria {
        Fresc,
        Processat,
        Cereal,
        Beguda,
        Congelat,
    }
}