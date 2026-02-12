package controller;

import model.Bestellung;
import persistence.BestellungRepo;

import java.util.ArrayList;
import java.util.List;

public class BestellungenController {
    BestellungRepo bestellungRepo = new BestellungRepo();

    public void addBestellung(Bestellung bestellung) {
        bestellungRepo.addBestellung(bestellung);
    }

    public void deleteBestellung(Bestellung bestellung) {
        bestellungRepo.deleteBestellung(bestellung);
    }

    public void updateBestellung(Bestellung bestellung) {
        bestellungRepo.updateBestellung(bestellung);
    }

    public List<Bestellung> getAllBestellungen() {
        return bestellungRepo.getAll();
    }
}
