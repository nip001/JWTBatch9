package com.batch9.beritaappsjwt.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Berita implements Parcelable {
    private String title;
    private String deskripsi;

    public Berita(){

    }

    public Berita(String title, String deskripsi) {
        this.title = title;
        this.deskripsi = deskripsi;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.deskripsi);
    }

    public void readFromParcel(Parcel source) {
        this.title = source.readString();
        this.deskripsi = source.readString();
    }

    protected Berita(Parcel in) {
        this.title = in.readString();
        this.deskripsi = in.readString();
    }

    public static final Parcelable.Creator<Berita> CREATOR = new Parcelable.Creator<Berita>() {
        @Override
        public Berita createFromParcel(Parcel source) {
            return new Berita(source);
        }

        @Override
        public Berita[] newArray(int size) {
            return new Berita[size];
        }
    };
}
