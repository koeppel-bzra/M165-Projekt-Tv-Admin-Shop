package controller;

import model.Kunde;
import persistence.KundenRepo;

import java.util.List;

public class KundenController {
    KundenRepo kundenRepo = new KundenRepo();

    public void addKunde(Kunde kunde) {
        kundenRepo.addKunde(kunde);
        System.out.println("Kunden erfolgreich hinzugefügt");
    }

    public void deleteKunde(Kunde kunde) {
        kundenRepo.deleteKunde(kunde);
        System.out.println("Kunden erfolgreich gelöscht");
    }

    public void updateKunde(Kunde kunde) {
        kundenRepo.updateKunde(kunde);
        System.out.println("Kunden erfolgreich aktualisiert");
    }

    public List<Kunde> getAllKunden() {
        return kundenRepo.getAllKunden();
    }
}
