package learn.hotel.domain;

import learn.hotel.data.DataException;
import learn.hotel.data.HostRepositoryDouble;
import learn.hotel.models.Host;
import org.junit.jupiter.api.Test;

import java.util.jar.JarEntry;

import static org.junit.jupiter.api.Assertions.*;

public class HostServiceTest {

    HostService service = new HostService( new HostRepositoryDouble());

    Host host1 = new Host();
    Host host2 = new Host();

    @Test
    void shouldReturnAll() throws DataException {
        assertEquals(1,service.findAll().size());
    }

    @Test
    void shouldFindDoe() throws DataException {
        assertEquals("George", service.findByLastName("G").get(0).getLastName());
    }

    @Test
    void shouldFindTestId() throws DataException {
        assertNotNull(service.findByHostId("Test-abc"));
    }

    @Test
    void shouldNotFindNoneExistingHostId() throws DataException {
        assertEquals(0,service.findByHostId("None").size());
    }

    @Test
    void shouldFindNotFindPrefix() throws DataException {
        assertEquals(0, service.findByLastName("F").size());
    }

}