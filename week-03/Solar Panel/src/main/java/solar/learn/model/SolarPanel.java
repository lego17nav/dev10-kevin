package solar.learn.model;

public class SolarPanel {
    private String section;
    private int row;
    private int col;
    private int yearInstalled;
    private Material material;
    private boolean isTracking;

    public int getPanelId() {
        return panelId;
    }

    public void setPanelId(int panelId) {
        this.panelId = panelId;
    }

    private int panelId;
    private String uniqueKey;

    public void setSection(String section) {
        this.section = section;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public boolean isTracking() {
        return isTracking;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    // Constructor
    public SolarPanel(String section, int row, int col, int yearInstalled, Material material, boolean isTracking) {

        this.section = section;
        this.row = row;
        this.col = col;
        this.yearInstalled = yearInstalled;
        this.material = material;
        this.isTracking = isTracking;
        this.uniqueKey = section + ":" + row + ":" + col;
    }

    public SolarPanel() {

    }

    // Getters
    public String getUniqueKey() {
        return uniqueKey;
    }
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    public String getSection() {
        return section;
    }
    public int getYearInstalled() {
        return yearInstalled;
    }
    public Material getMaterial() {
        return material;
    }
    public boolean getIsTracking() {
        return isTracking;
    }

    // Setters

    public void setYearInstalled(int yearInstalled) {
        this.yearInstalled = yearInstalled;
    }
    public void setMaterial(Material material) {
        this.material = material;
    }
    public void setTracking(boolean isTracking) {
        this.isTracking = isTracking;
    }

}
