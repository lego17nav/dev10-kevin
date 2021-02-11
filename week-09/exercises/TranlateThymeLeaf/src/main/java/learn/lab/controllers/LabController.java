package learn.lab.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LabController {
    @GetMapping("/")
    public String getHome() {
        return "index";
    }

    @GetMapping("/encouragement")
    public String getEncouragement() {
        return "encouragement";
    }

    @GetMapping("/personal/encouragement")
    public String getPersonalEncouragement() {
        return "personal-encouragement";
    }

    @GetMapping("/tip")
    public String getTip() {
        return "tip";
    }

    @GetMapping("/color")
    public String getColor() {
        return "color";
    }

    @GetMapping("/math")
    public String getMath() {
        return "math";
    }

    @GetMapping("/hockey")
    public String getHockey() {
        return "hockey";
    }
}
