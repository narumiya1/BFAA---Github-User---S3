package com.cryptbrn.favapp.model;

import android.database.Cursor;
import android.os.Parcelable;

import com.cryptbrn.favapp.db.DbUserContract;
import com.google.gson.annotations.SerializedName;

import static com.cryptbrn.favapp.db.DbUserContract.getFavoriteItem;

public class User implements Parcelable {

    @SerializedName("login")
    public String login;

    @SerializedName("avatar_url")
    public String avatarUrl;

    @SerializedName("id")
    public int id;

    @SerializedName("type")
    private String type;

    @SerializedName("repos_url")
    private String reposUrl;

    public User() {

    }

    public User(Cursor c){
        this.id = c.getInt(0);
        this.login = getFavoriteItem(c, DbUserContract.UserColumns.USERNAME);
        this.avatarUrl = getFavoriteItem(c,DbUserContract.UserColumns.AVATAR);
        this.reposUrl = getFavoriteItem(c, DbUserContract.UserColumns.URLREPOS);
        this.type = getFavoriteItem(c, DbUserContract.UserColumns.TYPE);
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

    public User(android.os.Parcel in) {
        login = in.readString();
        avatarUrl = in.readString();
        id = in.readInt();
        reposUrl = in.readString();
        type = in.readString();

    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(android.os.Parcel in) {
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
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeString(login);
        dest.writeString(avatarUrl);
        dest.writeInt(id);
        dest.writeString(reposUrl);
        dest.writeString(type);
    }
}