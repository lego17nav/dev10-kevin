
import solar.learn.repository.SolarFileRepository;
import solar.learn.domain.SolarPanelService;
import solar.learn.ui.Controller;
import solar.learn.ui.View;

public class App {

    public static void main(String[] args) {


        SolarPanelService service = new SolarPanelService(new SolarFileRepository("C:\\Users\\KevinLimlengco\\" +
                "Source\\dev10-kevin\\week-03\\Solar Panel\\src\\main\\data\\solar-panel.txt"));
        Controller controller = new Controller(new View(), service);
        controller.run();

    }
}
