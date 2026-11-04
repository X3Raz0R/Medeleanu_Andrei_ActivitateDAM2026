package com.example.lab5;

import android.os.Parcel;
import android.os.Parcelable;

public class Magazin implements Parcelable {
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

    protected Magazin(Parcel in) {
        nume = in.readString();
        adresa = in.readString();
        tip = in.readString();
        categorie = in.readString();
        rating = in.readFloat();
        livrare = in.readByte() != 0;
        deschisWeekend = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nume);
        dest.writeString(adresa);
        dest.writeString(tip);
        dest.writeString(categorie);
        dest.writeFloat(rating);
        dest.writeByte((byte) (livrare ? 1 : 0));
        dest.writeByte((byte) (deschisWeekend ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Magazin> CREATOR = new Creator<Magazin>() {
        @Override
        public Magazin createFromParcel(Parcel in) {
            return new Magazin(in);
        }

        @Override
        public Magazin[] newArray(int size) {
            return new Magazin[size];
        }
    };

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

    @Override
    public String toString() {
        return nume + " (" + tip + ", " + categorie + ")\n" +
                "Adresă: " + adresa + "\n" +
                "Rating: " + rating +
                " | Livrare: " + (livrare ? "Da" : "Nu") +
                " | Weekend: " + (deschisWeekend ? "Da" : "Nu");
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
