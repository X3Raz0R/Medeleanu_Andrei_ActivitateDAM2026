package com.example.lab5;

import java.io.Serializable;

public class Magazin implements Serializable {
    private String nume;
    private String adresa;
    private String tip;
    private String categorie;
    private float rating;
    private boolean livrare;
    private boolean deschisWeekend;

    public Magazin(String nume, String adresa, String tip, String categorie, float rating, boolean livrare, boolean deschisWeekend) {
        this.nume = nume;
        this.adresa = adresa;
        this.tip = tip;
        this.categorie = categorie;
        this.rating = rating;
        this.livrare = livrare;
        this.deschisWeekend = deschisWeekend;
    }

    public String getNume() {
        return nume;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getTip() {
        return tip;
    }

    public String getCategorie() {
        return categorie;
    }

    public float getRating() {
        return rating;
    }

    public boolean isLivrare() {
        return livrare;
    }

    public boolean isDeschisWeekend() {
        return deschisWeekend;
    }
}
