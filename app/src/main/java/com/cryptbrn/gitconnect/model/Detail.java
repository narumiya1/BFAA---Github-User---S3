package com.cryptbrn.gitconnect.model;

import com.google.gson.annotations.SerializedName;

public class Detail{

    @SerializedName("company")
    private String company;

    @SerializedName("public_repos")
    private String publicRepos;

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("location")
    private String location;


    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public String getLocation(){
        return location;
    }

    public void setCompany(String company){
        this.company = company;
    }

    public String getCompany(){
        return company;
    }

    public void setPublicRepos(String publicRepos){
        this.publicRepos = publicRepos;
    }

    public String getPublicRepos(){
        return publicRepos;
    }

}