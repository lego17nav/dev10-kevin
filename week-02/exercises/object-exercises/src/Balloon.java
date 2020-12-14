public class Balloon {
    private String color;
    private double psi;

    public Balloon(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
    public double getPsi() {
        if(psi > 16.0) {
            return Double.POSITIVE_INFINITY;
        }
        else {
            return psi;
        }
    }
    protected void inflate() {
        this.psi = this.psi + Math.random() * 5.0;
    }
    protected boolean isExploded(){
        if(psi > 16) {
            return true;
        }
        else {
            return false;
        }
    }
}
