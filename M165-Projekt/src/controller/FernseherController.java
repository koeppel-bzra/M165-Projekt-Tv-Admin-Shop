package controller;

import model.Fernseher;
import org.bson.types.ObjectId;
import persistence.FernseherRepo;

import java.util.ArrayList;
import java.util.List;

public class FernseherController {

    FernseherRepo repo = new FernseherRepo();
    List<Fernseher> tvs = new ArrayList<>();

    public void addFernseher(Fernseher f) {
        tvs.add(f);

        repo.addFernseher(f);

        System.out.println("Fernseher erfolgreich hinzugefügt\n" + f);
    }

    public void deleteFernseher(Fernseher f, ObjectId id) {
        tvs.remove(f);

        repo.deleteFernseher(id);

        System.out.println("Fernseher erfolgreich gelöscht\n" + f);
    }

    public void updateFernseher(Fernseher f) {
        repo.updateFernseher(f);
        System.out.println("Fernseher erfolgreich aktualisiert");
    }

    public List<Fernseher> getAllFernseher() {
        List<Fernseher> alle = new ArrayList<>();

        alle.addAll(tvs);

        List<Fernseher> ausDB = repo.getAll();

        for (Fernseher f : ausDB) {
            boolean schonVorhanden = false;
            for (Fernseher existierender : alle) {
                if (f.getFernseherId() != null && f.getFernseherId().equals(existierender.getFernseherId())) {
                    schonVorhanden = true;
                }
            }

            if (!schonVorhanden) {
                alle.add(f);
            }
        }

        return alle;
    }
}
