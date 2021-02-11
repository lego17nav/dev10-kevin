package learn.solarfarm.controllers;
import learn.solarfarm.data.DataAccessException;
import learn.solarfarm.domain.SolarPanelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SolarPanelThymeController {

    private final SolarPanelService service;

    public SolarPanelThymeController(SolarPanelService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String getHome() {
        return "home";
    }

    @GetMapping("/viewAll")
    public String viewAllPanels(Model model) throws DataAccessException {
        model.addAttribute("solarpanels", service.findAll());
        return "allpanels";
    }
}
