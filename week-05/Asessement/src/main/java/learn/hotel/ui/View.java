package learn.hotel.ui;

import learn.hotel.models.Host;

import java.util.List;
import java.util.stream.Collectors;

public class View {

    private final ConsoleIO io;


    public View(ConsoleIO io) {
        this.io = io;
    }

    public MainMenuOption selectMainMenuOption() {
        displayHeader("Main Menu");
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (MainMenuOption option : MainMenuOption.values()) {
            if (!option.isHidden()) {
                io.printf("%s. %s%n", option.getValue(), option.getMessage());
            }
            min = Math.min(min, option.getValue());
            max = Math.max(max, option.getValue());
        }

        String message = String.format("Select [%s-%s]: ", min, max - 1);
        return MainMenuOption.fromValue(io.readInt(message, min, max));
    }

    public void displayHeader(String message) {
        io.println("");
        io.println(message);
        io.println("=".repeat(message.length()));
    }

    public void displayException(Exception ex) {
        displayHeader("A critical error occurred:");
        io.println(ex.getMessage());
    }

    public Host chooseHost(List<Host> hosts) {

        displayHosts(hosts);
        if (hosts.size() == 0) {
            io.println("No Hosts Found");
            return null;
        }
        int index = 1;
        for (Host host : hosts.stream().collect(Collectors.toList())) {
            io.printf("%s: %s, %s, %s, %s", index++, host.getLastName(),host.getEmail(),
                    host.getPhone(), host.getAddress());
        }
        index --;

        io.println("0: Exit");
        String message = String.format("Select a Host by their index [0-%s]", index);

        index = io.readInt(message,0,index);
        if(index <= 0) {
            return null;
        }
        return hosts.get(index - 1);
    }

    public void displayHosts(List<Host> hosts) {

        if (hosts.size() == 0) {
            io.println("No hosts Found");
        }
        //last_name,email,phone,address,city,state,postal_code,standard_rate,weekend_rate
        for (Host host : hosts) {
            io.printf("%s,%s,%s,%s,%s%n", host.getLastName(),host.getEmail(),
                    host.getPhone(),host.getAddress(),host.getCity());
        }
    }
}
