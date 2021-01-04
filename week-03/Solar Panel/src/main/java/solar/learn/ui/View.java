package solar.learn.ui;

import solar.learn.model.Material;
import solar.learn.model.SolarPanel;
import java.util.List;
import java.util.Scanner;

public class View {

    private final Scanner console = new Scanner(System.in);

    public int chooseMenuOption() {
        System.out.println("Main Menu");
        System.out.println("0. Exit");
        System.out.println("1. Find Panels by Section");
        System.out.println("2. Add a panel");
        System.out.println("3. Update a panel");
        System.out.println("4. Remove a Panel");
        return readInt("Please enter a number [0-4]");
    }

    public void displayHeader(String headerMessage) {
        int length = headerMessage.length();
        displayMessage(headerMessage);
        System.out.println("=".repeat(length));
    }

    public void displaySolarPanels(List<SolarPanel> solarPanels) {
        if (solarPanels.size() == 0) {
            displayHeader("No Solar Panels to Display");
        } else {
            displayHeader("Solar Panels:");
            for (SolarPanel solarPanel : solarPanels) {
                System.out.printf("Section: %s\nColumn - %d\nRow - %d\nMaterial - %s\nYear Built - %d\n" +
                                "Is Tracking - %s\n" +
                                "Unique Key - %s\n" +
                                "===================================",
                        solarPanel.getSection(), solarPanel.getCol(), solarPanel.getRow(),
                        solarPanel.getMaterial().getStringValue(),solarPanel.getYearInstalled(),
                        solarPanel.getIsTracking() == true? "Yes" : "No",solarPanel.getUniqueKey());
            }
        }
    }
    public void displayMessage(String message) {
        System.out.println();
        System.out.println(message);
    }

    public int readInt (String prompt) {
        while(true) {
            System.out.println(prompt);
            String value = console.nextLine();
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException ex) {
                System.out.printf("'%s' is not a number .%n", value);
            }
        }
    }

    private String readString(String prompt) {
        String result = "";
        while (result.isEmpty()) {
            System.out.println(prompt);
            result = console.nextLine();
        }

        return result;
    }

    private boolean readBoolean(String prompt) {
        String result = readString(prompt);
        return result.equalsIgnoreCase("y");
    }

    private Material readMaterial(String prompt) {
        Material material;
        System.out.println("Enter Material used:\n1. Multicrystalline Silicon\n" +
                "2. Monocrystalline Silicon\n" +
                "3. Amorphous Silicon\n" +
                "4. Cadmium Telluride\n" +
                "5. Copper Indium Gallium Selenide");
        int input = readInt("Enter a number Corresponding to the material");
        switch (input) {
            case 1:
               return Material.MULTICRYSTALLINESILICON;
            case 2:
                return Material.MONOCRYSTALLINE;
            case 3:
                return Material.AMORPHOUS;
            case 4:
                return Material.CADMIUM;
            case 5:
                return Material.COPPER;
        }
        return null;
    }

    public SolarPanel createSolarPanel() {
        displayHeader("Add Solar Panel");
        System.out.println("Enter Section Name:");
        String section = console.nextLine();
        int row = readInt("Enter a row Number");
        int col = readInt("Enter a Column Number");
        int yearInstalled = readInt("Enter Year Solar Panel is Installed");
        Material material = readMaterial("Material Used");
        boolean isTracking = readBoolean("Is Tracking enabled");
        SolarPanel solarPanel = new SolarPanel(section,row,col,yearInstalled, material,isTracking);
        return solarPanel;
    }

    public void displayErrors(List<String> errors) {
        displayHeader("Errors: ");
        for(String error: errors) {
            System.out.println(error);
        }
    }

    public SolarPanel choosePanel(List<SolarPanel> solarPanels) {
        displaySolarPanels(solarPanels);
        SolarPanel result = null;
        if (solarPanels.size() > 0) {
            String keyId = readString("Choose key id: ");
            for(SolarPanel solarPanel: solarPanels) {
                if(solarPanel.getUniqueKey().equalsIgnoreCase(keyId)) {
                    result = solarPanel;
                    break;
                }
            }
        }
        return result;
    }

    public SolarPanel editSolarPanel(SolarPanel solarPanel) {
        displayHeader("Update");
        int yearInstalled = readInt("Enter year Installed");
        if (yearInstalled > 0) {
            solarPanel.setYearInstalled(yearInstalled);
        }
        Material material = readMaterial("Pick current Material used");
        if (material != null) {
            solarPanel.setMaterial(material);
        }

        boolean isTracking = readBoolean("Tracking: ");
        if (isTracking != solarPanel.getIsTracking()) {
            solarPanel.setTracking(isTracking);
        }

        return solarPanel;
    }

}
