package solar.learn.repository;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import solar.learn.model.Material;
import solar.learn.model.SolarPanel;

import javax.sql.DataSource;
import org.springframework.jdbc.core.RowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SolarPanelJdbcRepository {

    private DataSource dataSource = initDataSource();

    private final JdbcTemplate jdbcTemplate;

    public SolarPanelJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    private DataSource initDataSource() {
        MysqlDataSource result = new MysqlDataSource();
        // 2. connection string is:
        // [db-tech]:[db-vendor]://[host]:[port]/[database-name]
        result.setUrl("jdbc:mysql://localhost:3306/solarpanel");
        // 3. username
        result.setUser("root");
        // 4. password
        result.setPassword("top-secret-password");
        return result;
    }

    public List<SolarPanel> findAll() {
        ArrayList<SolarPanel> result = new ArrayList<>();
        // section, varRow, varCol, yearInstall, material, tracking, uniqueKey
        final String sql = "select `section`, `varRow`, `varCol`, `yearInstall`, `material`, `tracking`, `uniqueKey`" +
                " from solarpanel;";
        // 1. Ask the DataSource for a Connection.
        try (Connection conn = dataSource.getConnection()) {
            // TODO: create Pets from database rows
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                SolarPanel solarPanel = new SolarPanel();
                solarPanel.setSection(rs.getString("section"));
                solarPanel.setRow(rs.getInt("varRow"));
                solarPanel.setCol(rs.getInt("varCol"));
                solarPanel.setYearInstalled(rs.getInt("yearInstall"));
                solarPanel.setMaterial(Material.fromString(rs.getString("material")));
                solarPanel.setTracking(rs.getBoolean("tracking"));
                result.add(solarPanel);
            }

        } catch (SQLException ex) {
            // 2. SQL classes have many checked exceptions.
            ex.printStackTrace();
        }
        return result;
    }

    public SolarPanel findByUniqueKey(int uniqueKey) {
        final String sql = "select `section`, `varRow`, `varCol`, `yearInstall`, `material`, `tracking`" +
                " from solarpanel where `panelid` = ?;";
        try {
            return jdbcTemplate.queryForObject(sql, mapper, uniqueKey);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    // Helper Methods

    private final RowMapper<SolarPanel> mapper = (resultSet, rowNum) -> {
        SolarPanel solarPanel = new SolarPanel();
        solarPanel.setSection(resultSet.getString("section"));
        solarPanel.setRow(resultSet.getInt("varRow"));
        solarPanel.setCol(resultSet.getInt("varCol"));
        solarPanel.setYearInstalled(resultSet.getInt("yearInstall"));
        solarPanel.setMaterial(Material.fromString(resultSet.getString("material")));
        solarPanel.setTracking(resultSet.getBoolean("tracking"));
        return solarPanel;
    };

    public SolarPanel add(SolarPanel solarPanel) {
        final String sql = "insert into solarpanel ('section','varRow','varCol','yearInstall','material','tracking') values" +
                "(?,?,?,?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, solarPanel.getSection());
            ps.setInt(2, solarPanel.getRow());
            ps.setInt(3, solarPanel.getCol());
            ps.setInt(4, solarPanel.getYearInstalled());
            ps.setString(5, solarPanel.getMaterial().getStringValue());
            ps.setBoolean(6, solarPanel.getIsTracking());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }
        solarPanel.setPanelId(keyHolder.getKey().intValue());
        return solarPanel;
    }

    public boolean update(SolarPanel solarPanel) {
        final String sql = "update solarpanel set "
                + "`section` = ?, " +
                "`varRow` = ?, " +
                "`varCol` = ?, " +
                "`yearInstall = ?, " +
                "`material = ?, " +
                "`tracking` = ? " +
                "where panelid = ?;";

        int rowsUpdated = jdbcTemplate.update(sql,
                solarPanel.getSection(), solarPanel.getRow(), solarPanel.getCol(),
                solarPanel.getYearInstalled(), solarPanel.getMaterial().getStringValue(),
                solarPanel.getIsTracking(), solarPanel.getPanelId());

        return rowsUpdated > 0;
    }

    public boolean deleteById(int panelId) {
        final String sql = "delete from solarpanel where panelid =?;";
        return jdbcTemplate.update(sql, panelId) > 0;
    }

}