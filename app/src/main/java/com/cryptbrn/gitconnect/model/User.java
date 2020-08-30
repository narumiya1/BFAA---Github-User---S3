package com.cryptbrn.gitconnect.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class User implements Parcelable {

    @SerializedName("id")
    public int id;

    @SerializedName("login")
    public String login;

    @SerializedName("avatar_url")
    public String avatarUrl;

    @SerializedName("type")
    private String type;

    @SerializedName("repos_url")
    private String reposUrl;

    public User() {

    }

    public void setLogin(String login){
        this.login = login;
    }

    public String getLogin(){
        return login;
    }

    public void setAvatarUrl(String avatarUrl){
        this.avatarUrl = avatarUrl;
    }

    public String getAvatarUrl(){
        return avatarUrl;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReposUrl() {
        return reposUrl;
    }

    public void setReposUrl(String reposUrl) {
        this.reposUrl = reposUrl;
    }


    public static Creator<User> getCREATOR() {
        return CREATOR;
    }

    public User(Parcel in) {
        login = in.readString();
        avatarUrl = in.readString();
        id = in.readInt();
        type = in.readString();
        reposUrl = in.readString();

    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(login);
        parcel.writeString(avatarUrl);
        parcel.writeInt(id);
        parcel.writeString(type);
        parcel.writeString(reposUrl);
    }
}