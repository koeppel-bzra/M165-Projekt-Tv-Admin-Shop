package controller;

import model.Kunde;
import persistence.BestellungRepo;
import persistence.KundenRepo;

import java.util.List;

public class KundenController {
    KundenRepo kundenRepo = new KundenRepo();

    public void addKunde(Kunde kunde) {
        kundenRepo.addKunde(kunde);
    }

    public void deleteKunde(Kunde kunde) {
        kundenRepo.deleteKunde(kunde);
    }

    public void updateKunde(Kunde kunde) {
        kundenRepo.updateKunde(kunde);
    }

    public List<Kunde> getAllKunden() {
        return kundenRepo.getAllKunden();
    }
}
