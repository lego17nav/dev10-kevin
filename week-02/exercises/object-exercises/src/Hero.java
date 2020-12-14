public class Hero {
    protected String name;
    protected Power[] powers;

    public Hero(String name, Power[] powers) {
        this.name = name;
        this.powers = powers;
    }

    public String getName() {
        return name;
    }
    public Power[] getPowers() {
        return powers;
    }
    public String toLine() {
        String line = getName();
        System.out.println(line);
        String powerString = "";
        for(Power p :this.getPowers()) {
            powerString = p.toString();
        }
        line = line + powerString;

        return line;
    }
}
