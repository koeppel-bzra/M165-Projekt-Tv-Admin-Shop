package model;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.List;

public class Bestellung {

    @BsonId
    private ObjectId bestellungId;

    private int bestellnummer;
    private LocalDate bestellDatum;
    private Kunde kunde;
    private List<Bestellposition> bestellpositionen;

    public Bestellung(int bestellnummer, LocalDate bestellDatum, Kunde kunde, List<Bestellposition> bestellpositionen) {
        this.bestellnummer = bestellnummer;
        this.bestellDatum = bestellDatum;
        this.kunde = kunde;
        this.bestellpositionen = bestellpositionen;
    }

    public Bestellung(ObjectId bestellungId, int bestellnummer, LocalDate bestellDatum, Kunde kunde, List<Bestellposition> bestellpositionen) {
        this.bestellungId = bestellungId;
        this.bestellnummer = bestellnummer;
        this.bestellDatum = bestellDatum;
        this.kunde = kunde;
        this.bestellpositionen = bestellpositionen;
    }

    public Bestellung() {

    }

    public ObjectId getBestellungId() {
        return bestellungId;
    }

    public void setBestellungId(ObjectId bestellungId) {
        this.bestellungId = bestellungId;
    }

    public int getBestellnummer() {
        return bestellnummer;
    }

    public void setBestellnummer(int bestellnummer) {
        this.bestellnummer = bestellnummer;
    }

    public LocalDate getBestellDatum() {
        return bestellDatum;
    }

    public void setBestellDatum(LocalDate bestellDatum) {
        this.bestellDatum = bestellDatum;
    }

    public Kunde getKunde() {
        return kunde;
    }

    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }

    public List<Bestellposition> getBestellpositionen() {
        return bestellpositionen;
    }

    public void setBestellpositionen(List<Bestellposition> bestellpositionen) {
        this.bestellpositionen = bestellpositionen;
    }
}
