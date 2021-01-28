package learn.hodgepodge.Controllers;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
public class HodgePodgeController {

    @GetMapping("/name")
    public String getName() {
        return "Kevin Limlengco";
    }

    @GetMapping("/current/time")
    public LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }

    @GetMapping("/greet/{name}")
    public String getGreeting(@PathVariable String name) {
        return "Hello, " + name + "!";
    }
    static int sheepCount = 0;
    @PutMapping("/sheep")
    public void incrementSheepCount() {
        sheepCount ++;
    }

    @GetMapping("/sheep")
    public int getSheepCount() {
        return sheepCount;
    }
    @PutMapping("/sheep/{amount}")
    public void addSheepAmount(@PathVariable int amount) {
        sheepCount += amount;
    }
    @PostMapping("/sheep")
    public void incrementSheepByObject(@RequestBody SheepValue value) {
        System.out.println(value.getAmount());
    }

}
