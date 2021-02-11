package learn.solarfarm.ui;

import learn.solarfarm.data.DataAccessException;
import learn.solarfarm.domain.SolarPanelService;
import learn.solarfarm.models.SolarPanel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class SolarPanelController {
    private SolarPanelService service;


    public SolarPanelController(SolarPanelService service) {
        this.service = service;
    }

    @GetMapping
    public String index(Model model) throws DataAccessException {
        List<SolarPanel> panels = service.findAll();
        model.addAttribute("panels", panels);
        return "solarpanels/index";

    }
}
