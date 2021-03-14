package edu.ubb.model;

public class Frage {
    private String frage;
    private String antwort1;
    private String antwort2;
    private String antwort3;

    //Konstruktor
    public Frage(String frage, String antwort1, String antwort2, String antwort3) {
        this.frage = frage;
        this.antwort1 = antwort1;
        this.antwort2 = antwort2;
        this.antwort3 = antwort3;
    }

    //Getters
    public String getFrage() {
        return frage;
    }

    public String getAntwort1() {
        return antwort1;
    }

    public String getAntwort2() {
        return antwort2;
    }

    public String getAntwort3() {
        return antwort3;
    }
}