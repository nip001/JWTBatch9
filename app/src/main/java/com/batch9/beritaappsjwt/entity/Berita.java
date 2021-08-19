package com.batch9.beritaappsjwt.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Berita implements Parcelable {
    private String title;
    private String deskripsi;
    private String tanggal;
    private String author;

    public Berita(String title, String deskripsi, String author, String tanggal) {
        this.title = title;
        this.deskripsi = deskripsi;
        this.author = author;
        this.tanggal = tanggal;
    }

    public Berita(){

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.deskripsi);
        dest.writeString(this.author);
        dest.writeString(this.tanggal);
    }

    public void readFromParcel(Parcel source) {
        this.title = source.readString();
        this.deskripsi = source.readString();
        this.author = source.readString();
        this.tanggal = source.readString();
    }

    protected Berita(Parcel in) {
        this.title = in.readString();
        this.deskripsi = in.readString();
        this.author = in.readString();
        this.tanggal = in.readString();
    }

    public static final Creator<Berita> CREATOR = new Creator<Berita>() {
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
