package com.mkyong.model;

public class Artikel {

    private String name;
    private String kuenstler;
    private Long id;
    private String beschreibung;
    private String img;
    private double preis;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKuenstler() {
        return kuenstler;
    }

    public void setKuenstler(String kuenstler) {
        this.kuenstler = kuenstler;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    public Artikel(String name, Long id, String beschreibung, String img) {

        this.name = name;
        this.id = id;
        this.beschreibung = beschreibung;
        this.img = img;
    }

    public Artikel() {

    }
}
