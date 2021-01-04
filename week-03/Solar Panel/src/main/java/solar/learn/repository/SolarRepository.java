package solar.learn.repository;

import solar.learn.model.SolarPanel;

import java.util.List;

public interface SolarRepository {

    SolarPanel add(SolarPanel solarPanel) throws DataAccessException;
    SolarPanel findByUniqueKey(String key) throws DataAccessException;
    List<SolarPanel> findAll() throws DataAccessException;
    boolean update(SolarPanel solarPanel) throws DataAccessException;
    boolean delete(String uniqueKey) throws DataAccessException;

}
