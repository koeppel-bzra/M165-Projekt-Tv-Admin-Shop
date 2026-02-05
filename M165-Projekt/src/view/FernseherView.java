package view;

import controller.FernseherController;
import model.Fernseher;

import java.util.List;
import java.util.Scanner;

public class FernseherView {
    FernseherController controller = new FernseherController();
    Scanner sc = new Scanner(System.in);
    boolean running = true;

    public void menu() {
        while (running) {
            System.out.println("Wähle eine Option");
            ui();
            int entscheidung = sc.nextInt();
            sc.nextLine();

            switch (entscheidung) {
                case 1:
                    addFernseherUI();
                    break;

                case 2:
                    getAllFernseherUI();
                    break;

                case 0:
                    running = false;
                    break;
            }
        }

    }

    public void ui() {
        System.out.println("1 - Fernseher hinzufügen");
        System.out.println("2 - Alle Fernseher anschauen");
        System.out.println("0 - Abbrechen");
    }



    public void addFernseherUI() {
        System.out.println("Marke eingeben:");
        String marke = sc.nextLine();

        System.out.println("Modell eingeben:");
        String modell = sc.nextLine();

        Fernseher f = new Fernseher(marke, modell);

        controller.addFernseher(f);
    }

    public void getAllFernseherUI() {
        System.out.println("Fernseher: \n");
        List<Fernseher> alle = controller.getAllFernseher();

        if (alle.isEmpty()) {
            System.out.println("Keine Fernseher vorhanden");
        }

        else {
            for (Fernseher f : alle) {
                System.out.println(f);
            }
        }
    }

}
