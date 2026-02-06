package controller;

import model.Fernseher;
import persistence.FernseherRepo;

import java.util.List;

public class FernseherController {

    private FernseherRepo repo = new FernseherRepo();

    public void addFernseher(Fernseher f) {
        repo.addFernseher(f);
        System.out.println("Fernseher erfolgreich hinzugefügt\n" + f);
    }

    public void deleteFernseher(Fernseher f) {
        repo.deleteFernseher(f);
        System.out.println("Fernseher erfolgreich gelöscht\n" + f);
    }

    public void updateFernseher(Fernseher f) {
        repo.updateFernseher(f);
        System.out.println("Fernseher erfolgreich aktualisiert");
    }

    public List<Fernseher> getAllFernseher() {
        return repo.getAll();
    }
}
