package model;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import java.time.LocalDate;

public class Kunde {

    @BsonId
    private ObjectId kundeId;

    private String anrede;
    private String nachname;
    private String vorname;

    private Adresse adresse;

    private String telefonPrivat;
    private String telefonMobile;
    private String email;
    private LocalDate geburtsdatum;
    private String username;
    private String password;

    public Kunde(ObjectId kundeId, String nachname, String anrede, String vorname, Adresse adresse, String telefonPrivat, String email, String telefonMobile, LocalDate geburtsdatum, String username, String password) {
        this.kundeId = kundeId;
        this.nachname = nachname;
        this.anrede = anrede;
        this.vorname = vorname;
        this.adresse = adresse;
        this.telefonPrivat = telefonPrivat;
        this.email = email;
        this.telefonMobile = telefonMobile;
        this.geburtsdatum = geburtsdatum;
        this.username = username;
        this.password = password;
    }

    public Kunde(String anrede, String nachname, String vorname, String telefonPrivat, Adresse adresse, String telefonMobile, String email, LocalDate geburtsdatum, String username, String password) {
        this.anrede = anrede;
        this.nachname = nachname;
        this.vorname = vorname;
        this.telefonPrivat = telefonPrivat;
        this.adresse = adresse;
        this.telefonMobile = telefonMobile;
        this.email = email;
        this.geburtsdatum = geburtsdatum;
        this.username = username;
        this.password = password;
    }

    public Kunde() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Kunde)) return false;
        Kunde k = (Kunde) o;
        return kundeId.equals(k.kundeId);
    }

    @Override
    public int hashCode() {
        return kundeId.hashCode();
    }


    @Override
    public String toString() {
        return anrede + " " + nachname + " " + vorname;
    }

    public ObjectId getKundeId() {
        return kundeId;
    }

    public void setKundeId(ObjectId kundeId) {
        this.kundeId = kundeId;
    }

    public String getAnrede() {
        return anrede;
    }

    public void setAnrede(String anrede) {
        this.anrede = anrede;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public String getTelefonPrivat() {
        return telefonPrivat;
    }

    public void setTelefonPrivat(String telefonPrivat) {
        this.telefonPrivat = telefonPrivat;
    }

    public String getTelefonMobile() {
        return telefonMobile;
    }

    public void setTelefonMobile(String telefonMobile) {
        this.telefonMobile = telefonMobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(LocalDate geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
