package com.batch9.beritaappsjwt.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private String username;
    private String password;
    private String role;

    public User(String username, String password,String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(){

    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.username);
        dest.writeString(this.password);
        dest.writeString(this.role);
    }

    public void readFromParcel(Parcel source) {
        this.username = source.readString();
        this.password = source.readString();
        this.role = source.readString();
    }

    protected User(Parcel in) {
        this.username = in.readString();
        this.password = in.readString();
        this.role = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
