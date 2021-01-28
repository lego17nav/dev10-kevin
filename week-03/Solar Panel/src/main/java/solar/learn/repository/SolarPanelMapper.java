package solar.learn.repository;

import solar.learn.model.Material;
import solar.learn.model.SolarPanel;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SolarPanelMapper implements RowMapper<SolarPanel> {

    @Override
    public SolarPanel mapRow(ResultSet resultSet, int i) throws SQLException {
        SolarPanel solarPanel = new SolarPanel();
        solarPanel.setSection(resultSet.getString("section"));
        solarPanel.setRow(resultSet.getInt("varRow"));
        solarPanel.setCol(resultSet.getInt("varCol"));
        solarPanel.setYearInstalled(resultSet.getInt("yearInstall"));
        solarPanel.setMaterial(Material.fromString(resultSet.getString("material")));
        solarPanel.setTracking(resultSet.getBoolean("tracking"));
        return solarPanel;
    }
}
