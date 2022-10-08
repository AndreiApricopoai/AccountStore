package com.andrewsapp.accstore2;

import java.io.Serializable;

public class RowDB implements Serializable {
    public int id ;
    public String title="title";
    public String account="account";
    public String username="username";
    public String pass="pass";
    public String website="web";
    public String aditionalinfo="ad";
    public String date="date";
    public byte[] icon;
    public String color_light="colorw";
    public String color_dark="colorb";

    public RowDB(String title, String account, String username, String pass, String website, String aditionalinfo, String date, byte[] icon, String color_light, String color_dark, String ispredefined) {
        this.title = title;
        this.account = account;
        this.username = username;
        this.pass = pass;
        this.website = website;
        this.aditionalinfo = aditionalinfo;
        this.date = date;
        this.icon = icon;
        this.color_light = color_light;
        this.color_dark = color_dark;
    }

    public RowDB(int id, String title, String account, String username, String pass, String website, String aditionalinfo, String date, byte[] icon, String color_light, String color_dark, String ispredefined) {
        this.id = id;
        this.title = title;
        this.account = account;
        this.username = username;
        this.pass = pass;
        this.website = website;
        this.aditionalinfo = aditionalinfo;
        this.date = date;
        this.icon = icon;
        this.color_light = color_light;
        this.color_dark = color_dark;
    }

    public RowDB() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAditionalinfo() {
        return aditionalinfo;
    }

    public void setAditionalinfo(String aditionalinfo) {
        this.aditionalinfo = aditionalinfo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
    }

    public String getColor_light() {
        return color_light;
    }

    public void setColor_light(String color_light) {
        this.color_light = color_light;
    }

    public String getColor_dark() {
        return color_dark;
    }

    public void setColor_dark(String color_dark) {
        this.color_dark = color_dark;
    }

}
