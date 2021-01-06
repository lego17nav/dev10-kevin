
import org.springframework.context.support.ClassPathXmlApplicationContext;
import solar.learn.repository.SolarFileRepository;
import solar.learn.domain.SolarPanelService;
import solar.learn.ui.Controller;
import solar.learn.ui.View;

import org.springframework.context.ApplicationContext;

public class App {

    public static void main(String[] args) {


        /*SolarPanelService service = new SolarPanelService(new SolarFileRepository("C:\\Users\\KevinLimlengco\\" +
                "Source\\dev10-kevin\\week-03\\Solar Panel\\src\\main\\data\\solar-panel.txt"));
        Controller controller = new Controller(new View(), service);
        controller.run();*/

        ApplicationContext context = new ClassPathXmlApplicationContext("dependency-configuration.xml");

        Controller controller = context.getBean(Controller.class);

        controller.run();

    }
}
