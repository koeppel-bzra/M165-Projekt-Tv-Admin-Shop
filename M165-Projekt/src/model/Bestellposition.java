package model;

public class Bestellposition {
    private Fernseher fernseher;
    private double einzelpreis;
    private int stueckzahl;

    public Bestellposition(Fernseher fernseher, double einzelpreis, int stueckzahl) {
        this.fernseher = fernseher;
        this.einzelpreis = einzelpreis;
        this.stueckzahl = stueckzahl;
    }

    public Bestellposition() {

    }

    public Fernseher getFernseher() {
        return fernseher;
    }

    public void setFernseher(Fernseher fernseher) {
        this.fernseher = fernseher;
    }

    public int getStueckzahl() {
        return stueckzahl;
    }

    public void setStueckzahl(int stueckzahl) {
        this.stueckzahl = stueckzahl;
    }

    public double getEinzelpreis() {
        return einzelpreis;
    }

    public void setEinzelpreis(double einzelpreis) {
        this.einzelpreis = einzelpreis;
    }
}
