package controller;

import model.Fernseher;
import persistence.FernseherRepo;

import java.util.List;

public class FernseherController {

    private FernseherRepo repo = new FernseherRepo();

    public void addFernseher(Fernseher f) {
        repo.addFernseher(f);
    }

    public void deleteFernseher(Fernseher f) {
        repo.deleteFernseher(f);
    }

    public void updateFernseher(Fernseher f) {
        repo.updateFernseher(f);
    }

    public List<Fernseher> getAllFernseher() {
        return repo.getAll();
    }
}
