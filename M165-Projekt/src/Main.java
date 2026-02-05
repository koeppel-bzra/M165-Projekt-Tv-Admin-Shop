import controller.FernseherController;
import view.FernseherView;

public class Main {
    public static void main(String[] args) {
        FernseherController controller = new FernseherController();
        FernseherView view = new FernseherView();
        view.menu();
    }
}
