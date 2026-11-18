package com.example.iamcooked;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class monitor implements Parcelable {
    public static final Creator<monitor> CREATOR = new Creator<monitor>() {
        @Override
        public monitor createFromParcel(Parcel in) {
            return new monitor(in);
        }

        @Override
        public monitor[] newArray(int size) {
            return new monitor[size];
        }
    };
    private int marime;
    private String name;

    protected monitor(Parcel in) {
        marime = in.readInt();
        name = in.readString();
    }

    public monitor(int marime, String name) {
        this.marime = marime;
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public int getMarime() {
        return marime;
    }

    public void setMarime(int marime) {
        this.marime = marime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(marime);
        dest.writeString(name);
    }
}
