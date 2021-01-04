package solar.learn.repository;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import solar.learn.model.Material;
import solar.learn.model.SolarPanel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

class SolarFileRepositoryTest {

    static final String SEED_FILE_PATH = "src/main/data/solarpanel-seed.txt";
    static final String TEST_FILE_PATH = "src/main/data/solarpanel-test.txt";

    SolarFileRepository repository = new SolarFileRepository(TEST_FILE_PATH);

    @BeforeEach
    void setupTest() throws IOException {
        Path seedPath = Paths.get(SEED_FILE_PATH);
        Path testPath = Paths.get(TEST_FILE_PATH);

        Files.copy(seedPath, testPath, StandardCopyOption.REPLACE_EXISTING);

    }

    @Test
    void add() throws DataAccessException{
        SolarPanel solarPanel = new SolarPanel("Den",2,2,2016, Material.AMORPHOUS , true);
        repository.add(solarPanel);
        List<SolarPanel> solarPanels = repository.findAll();
        assertEquals(4,solarPanels.size());

    }

    @Test
    void findByUniqueKey() throws DataAccessException{
        SolarPanel solarPanel = repository.findByUniqueKey("Hogwarts:2:2");
        assertEquals("Hogwarts",solarPanel.getSection());
    }

    @Test
    void findAll() throws DataAccessException {
        List<SolarPanel> solarPanels = repository.findAll();
        assertEquals(3,solarPanels.size());
    }

    @Test
    void update() throws DataAccessException {
        SolarPanel toUpdate = new SolarPanel("Hogwarts",2,2,2000, Material.AMORPHOUS ,
                true);
        SolarPanel solarPanel = repository.findByUniqueKey("Hogwarts:2:2");
        assertEquals(2000,toUpdate.getYearInstalled());


    }

    @Test
    void delete() throws DataAccessException{
        repository.delete("Hogwarts:2:2");
        List<SolarPanel> solarPanels = repository.findAll();
        assertEquals(2,solarPanels.size());
    }
}