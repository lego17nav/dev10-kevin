package solar.learn.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SolarPanelControllers {

    @GetMapping("/")
    public String helloWorld() {
        return "Hello World!";
    }
}
