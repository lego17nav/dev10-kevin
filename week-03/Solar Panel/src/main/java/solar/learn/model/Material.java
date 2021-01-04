package solar.learn.model;

public enum Material {

    MULTICRYSTALLINESILICON("Multicrystalline silicon"),
    MONOCRYSTALLINE("Monocrystalline silicon"),
    AMORPHOUS("Amorphous silicon"),
    CADMIUM("Cadmium telluride"),
    COPPER("Gallium selenide");

    private final String stringValue;

    Material(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return stringValue;
    }
}


