/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package awa.entities;

import java.time.LocalDate;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class Repas {
    private LocalDate date;
    private boolean soir ;
    private String specailite;
    private int nbCouverts ;
    private boolean bio;
    private boolean vegan ;
    private int paf ;

    public Repas(LocalDate date, boolean soir, String specailite, int nbCouverts, boolean bio, boolean vegan, int paf) {
        this.date = date;
        this.soir = soir;
        this.specailite = specailite;
        this.nbCouverts = nbCouverts;
        this.bio = bio;
        this.vegan = vegan;
        this.paf = paf;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setSoir(boolean soir) {
        this.soir = soir;
    }

    public void setSpecailite(String specailite) {
        this.specailite = specailite;
    }

    public void setNbCouverts(int nbCouverts) {
        this.nbCouverts = nbCouverts;
    }

    public void setBio(boolean bio) {
        this.bio = bio;
    }

    public void setVegan(boolean vegan) {
        this.vegan = vegan;
    }

    public void setPaf(int paf) {
        this.paf = paf;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isSoir() {
        return soir;
    }

    @Override
    public String toString() {
        return "Repas{" + "date=" + date + ", soir=" + soir + ", specailite=" + specailite + ", nbCouverts=" + nbCouverts + ", bio=" + bio + ", vegan=" + vegan + ", paf=" + paf + '}';
    }

    public String getSpecailite() {
        return specailite;
    }

    public int getNbCouverts() {
        return nbCouverts;
    }

    public boolean isBio() {
        return bio;
    }

    public boolean isVegan() {
        return vegan;
    }

    public int getPaf() {
        return paf;
    }

    public Repas(LocalDate date, String specailite, boolean bio, boolean vegan, int paf) {
        this.date = date;
        this.specailite = specailite;
        this.bio = bio;
        this.vegan = vegan;
        this.paf = paf;
    }
    
}
