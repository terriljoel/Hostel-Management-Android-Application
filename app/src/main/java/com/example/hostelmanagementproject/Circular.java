package com.example.hostelmanagementproject;




public class Circular {

    private String title,Dat,Cref;
    private String img;

    public Circular(String title, String dat, String Cref, String img) {
        this.title = title;
        this.Dat = dat;
        this.Cref = Cref;
        this.img = img;
    }



    public String getTitle() {
        return title;
    }

    public String getDat() {
        return Dat;
    }

    public String getCref() {
        return Cref;
    }



    public void settitle(String title) {
        this.title = title;
    }

    public void setDat(String dat) {
        Dat = dat;
    }

    public void setCref(String cref) {
        Cref = cref;
    }

    public void setImg( String img) {
        this.img = img;
    }

    public String getImg() {
        return img;
    }
    public Circular()
    {

    }
}
