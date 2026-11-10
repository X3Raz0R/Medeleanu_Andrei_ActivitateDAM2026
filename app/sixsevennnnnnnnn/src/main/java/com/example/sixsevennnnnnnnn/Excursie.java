package com.example.sixsevennnnnnnnn;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Excursie implements Parcelable {
    public static final Creator<Excursie> CREATOR = new Creator<Excursie>() {
        @Override
        public Excursie createFromParcel(Parcel in) {
            return new Excursie(in);
        }

        @Override
        public Excursie[] newArray(int size) {
            return new Excursie[size];
        }
    };
    private String destinatie;
    private int pretpers;
    private boolean bagajextra;
    private boolean pensionar;
    private String anotimp;
    private float rating;
    private String locatie;

    public Excursie(String destinatie, int pretpers, boolean bagajextra, boolean pensionar, String anotimp, float rating, String locatie) {
        this.destinatie = destinatie;
        this.pretpers = pretpers;
        this.bagajextra = bagajextra;
        this.pensionar = pensionar;
        this.anotimp = anotimp;
        this.rating = rating;
        this.locatie = locatie;
    }

    protected Excursie(Parcel in) {
        destinatie = in.readString();
        pretpers = in.readInt();
        bagajextra = in.readByte() != 0;
        pensionar = in.readByte() != 0;
        anotimp = in.readString();
        rating = in.readFloat();
        locatie = in.readString();
    }

    @Override
    public String toString() {
        return "Excursie{" +
                "destinatie='" + destinatie + '\'' +
                ", pretpers=" + pretpers +
                ", bagajextra=" + bagajextra +
                ", pensionar=" + pensionar +
                ", anotimp='" + anotimp + '\'' +
                ", rating=" + rating +
                ", locatie='" + locatie + '\'' +
                '}';
    }

    public String getDestinatie() {
        return destinatie;
    }

    public void setDestinatie(String destinatie) {
        this.destinatie = destinatie;
    }

    public int getPretpers() {
        return pretpers;
    }

    public void setPretpers(int pretpers) {
        this.pretpers = pretpers;
    }

    public boolean isBagajextra() {
        return bagajextra;
    }

    public void setBagajextra(boolean bagajextra) {
        this.bagajextra = bagajextra;
    }

    public boolean isPensionar() {
        return pensionar;
    }

    public void setPensionar(boolean pensionar) {
        this.pensionar = pensionar;
    }

    public String getAnotimp() {
        return anotimp;
    }

    public void setAnotimp(String anotimp) {
        this.anotimp = anotimp;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(destinatie);
        dest.writeInt(pretpers);
        dest.writeByte((byte) (bagajextra ? 1 : 0));
        dest.writeByte((byte) (pensionar ? 1 : 0));
        dest.writeString(anotimp);
        dest.writeFloat(rating);
        dest.writeString(locatie);
    }
}
