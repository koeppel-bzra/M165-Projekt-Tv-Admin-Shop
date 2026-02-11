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
    private String telefonPrivat;
    private String telefonMobile;
    private String email;
    private LocalDate geburtsdatum;
    private String username;
    private String password;

    public Kunde(String anrede, String nachname, String vorname, String telefonPrivat, String telefonMobile, String email, LocalDate geburtsdatum, String username, String password) {
        this.anrede = anrede;
        this.nachname = nachname;
        this.vorname = vorname;
        this.telefonPrivat = telefonPrivat;
        this.telefonMobile = telefonMobile;
        this.email = email;
        this.geburtsdatum = geburtsdatum;
        this.username = username;
        this.password = password;
    }

    public Kunde(ObjectId kundeId, String anrede, String nachname, String vorname, String telefonPrivat, String telefonMobile, String email, LocalDate geburtsdatum, String username, String password) {
        this.kundeId = kundeId;
        this.anrede = anrede;
        this.nachname = nachname;
        this.vorname = vorname;
        this.telefonPrivat = telefonPrivat;
        this.telefonMobile = telefonMobile;
        this.email = email;
        this.geburtsdatum = geburtsdatum;
        this.username = username;
        this.password = password;
    }

    public Kunde() {

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
