package com.fzu.xms.rtable;

public class History {
    private String date;
    private String document;

    History(String date, String document){
        this.date = date;
        this.document = document;
    }
    /*
    public void setDate(String date){
        this.date = date;
    }
    public void setDocument(String document){
        this.document = document;
    }*/

    public String getDate(){
        return this.date;
    }
    public String getDocument(){
        return this.document;
    }
}
