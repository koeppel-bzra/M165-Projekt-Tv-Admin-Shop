package model;

import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.Date;

public class Fernseher {
    private ObjectId fernseherId;
    private String marke;
    private String modell;
    private double preis;
    private int bildschirmdiagonale;
    private DisplayTechnologie displayTechnologie;
    private Bildschirmaufloesung bildschirmAufloesung;
    private int bildwiederholFrequenz;
    private double gewicht;
    private LocalDate releaseDatum;
    private Pixelaufloesung pixelaufloesung;
    private int nennleistung;

    @Override
    public String toString() {
        return "ID: " + fernseherId + "\n" +
                "Marke: " + marke + "\n" +
                "Modell: " + modell + "\n";
    }



    public Fernseher(ObjectId fernseherId, String marke, String modell) {
        this.marke = marke;
        this.modell = modell;
        this.fernseherId = fernseherId;
    }

    public Fernseher(String marke, String modell) {
        this.marke = marke;
        this.modell = modell;
    }

    public Fernseher(String marke, String modell, double preis, int bildschirmdiagonale, DisplayTechnologie displayTechnologie, Bildschirmaufloesung bildschirmAufloesung, int bildwiederholFrequenz, double gewicht, LocalDate releaseDatum, Pixelaufloesung pixelaufloesung, int nennleistung) {
        this.marke = marke;
        this.modell = modell;
        this.preis = preis;
        this.bildschirmdiagonale = bildschirmdiagonale;
        this.displayTechnologie = displayTechnologie;
        this.bildschirmAufloesung = bildschirmAufloesung;
        this.bildwiederholFrequenz = bildwiederholFrequenz;
        this.gewicht = gewicht;
        this.releaseDatum = releaseDatum;
        this.pixelaufloesung = pixelaufloesung;
        this.nennleistung = nennleistung;
    }

    public Fernseher(ObjectId fernseherId, String marke, String modell, double preis, int bildschirmdiagonale, DisplayTechnologie displayTechnologie, Bildschirmaufloesung bildschirmAufloesung, int bildwiederholFrequenz, double gewicht, LocalDate releaseDatum, Pixelaufloesung pixelaufloesung, int nennleistung) {
        this.fernseherId = fernseherId;
        this.marke = marke;
        this.modell = modell;
        this.preis = preis;
        this.bildschirmdiagonale = bildschirmdiagonale;
        this.displayTechnologie = displayTechnologie;
        this.bildschirmAufloesung = bildschirmAufloesung;
        this.bildwiederholFrequenz = bildwiederholFrequenz;
        this.gewicht = gewicht;
        this.releaseDatum = releaseDatum;
        this.pixelaufloesung = pixelaufloesung;
        this.nennleistung = nennleistung;
    }

    public ObjectId getFernseherId() {
        return fernseherId;
    }

    public void setFernseherId(ObjectId fernseherId) {
        this.fernseherId = fernseherId;
    }

    public String getMarke() {
        return marke;
    }

    public void setMarke(String marke) {
        this.marke = marke;
    }

    public String getModell() {
        return modell;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    public int getBildschirmdiagonale() {
        return bildschirmdiagonale;
    }

    public void setBildschirmdiagonale(int bildschirmdiagonale) {
        this.bildschirmdiagonale = bildschirmdiagonale;
    }

    public DisplayTechnologie getDisplayTechnologie() {
        return displayTechnologie;
    }

    public void setDisplayTechnologie(DisplayTechnologie displayTechnologie) {
        this.displayTechnologie = displayTechnologie;
    }

    public Bildschirmaufloesung getBildschirmAufloesung() {
        return bildschirmAufloesung;
    }

    public void setBildschirmAufloesung(Bildschirmaufloesung bildschirmAufloesung) {
        this.bildschirmAufloesung = bildschirmAufloesung;
    }

    public int getBildwiederholFrequenz() {
        return bildwiederholFrequenz;
    }

    public void setBildwiederholFrequenz(int bildwiederholFrequenz) {
        this.bildwiederholFrequenz = bildwiederholFrequenz;
    }

    public double getGewicht() {
        return gewicht;
    }

    public void setGewicht(double gewicht) {
        this.gewicht = gewicht;
    }

    public LocalDate getReleaseDatum() {
        return releaseDatum;
    }

    public void setReleaseDatum(LocalDate releaseDatum) {
        this.releaseDatum = releaseDatum;
    }

    public Pixelaufloesung getPixelaufloesung() {
        return pixelaufloesung;
    }

    public void setPixelaufloesung(Pixelaufloesung pixelaufloesung) {
        this.pixelaufloesung = pixelaufloesung;
    }

    public int getNennleistung() {
        return nennleistung;
    }

    public void setNennleistung(int nennleistung) {
        this.nennleistung = nennleistung;
    }
}
