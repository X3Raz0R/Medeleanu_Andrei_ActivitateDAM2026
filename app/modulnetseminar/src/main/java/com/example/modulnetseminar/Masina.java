package com.example.modulnetseminar;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Masina implements Parcelable {
    public static final Creator<Masina> CREATOR = new Creator<Masina>() {
        @Override
        public Masina createFromParcel(Parcel in) {
            return new Masina(in);
        }

        @Override
        public Masina[] newArray(int size) {
            return new Masina[size];
        }
    };
    private final String marca;
    private final String model;
    private final int anFabricatie;
    private final Bitmap imagine;
    private final String link;

    public Masina(String marca, String model, int anFabricatie, Bitmap imagine, String link) {
        this.marca = marca;
        this.model = model;
        this.anFabricatie = anFabricatie;
        this.imagine = imagine;
        this.link = link;
    }

    protected Masina(Parcel in) {
        marca = in.readString();
        model = in.readString();
        anFabricatie = in.readInt();
        imagine = in.readParcelable(Bitmap.class.getClassLoader());
        link = in.readString();
    }

    public String getMarca() {
        return marca;
    }

    public String getModel() {
        return model;
    }

    public int getAnFabricatie() {
        return anFabricatie;
    }

    public Bitmap getImagine() {
        return imagine;
    }

    public String getLink() {
        return link;
    }

    public String getDescriere() {
        return marca + " " + model + ", fabricata in " + anFabricatie;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(marca);
        dest.writeString(model);
        dest.writeInt(anFabricatie);
        dest.writeParcelable(imagine, flags);
        dest.writeString(link);
    }
}
