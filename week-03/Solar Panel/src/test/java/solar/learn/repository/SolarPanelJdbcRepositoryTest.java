package solar.learn.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import solar.learn.model.SolarPanel;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SolarPanelJdbcRepositoryTest {

    SolarPanelJdbcRepository repository;

    public SolarPanelJdbcRepositoryTest() {
        ApplicationContext context = new AnnotationConfigApplicationContext(DbTestConfig.class);
        repository = context.getBean(SolarPanelJdbcRepository.class);
    }

    @BeforeAll
    static void oneTimeSetup() {
        ApplicationContext context = new AnnotationConfigApplicationContext(DbTestConfig.class);
        JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);
        jdbcTemplate.update("call set_known_good_state();");
    }
    @Test
    void ShouldFindAll() {
        List<SolarPanel> solarPanels = repository.findAll();
        assertNotNull(solarPanels);
        assertEquals(2,solarPanels.size());
    }

    @Test
    void ShouldAdd() {
        SolarPanel expected = new SolarPanel();

        SolarPanel out = repository.findByUniqueKey(1);
        System.out.println(out.getRow());
    }

}