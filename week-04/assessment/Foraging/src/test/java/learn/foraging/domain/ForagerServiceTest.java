package learn.foraging.domain;

import learn.foraging.data.DataException;
import learn.foraging.data.ForageRepositoryDouble;
import learn.foraging.data.ForagerRepositoryDouble;
import learn.foraging.data.ItemRepositoryDouble;
import learn.foraging.models.Category;
import learn.foraging.models.Forage;
import learn.foraging.models.Forager;
import learn.foraging.models.Item;
import org.junit.jupiter.api.Test;
import learn.foraging.models.States;

import static org.junit.jupiter.api.Assertions.*;

public class ForagerServiceTest {

    ForagerService service = new ForagerService(new ForagerRepositoryDouble());

    Forager forager1 = new Forager();
    Forager forager2 = new Forager();

    @Test
    void shouldReturnAll() throws DataException {
        assertEquals(1,service.findAll().size());
    }

    void shouldNotSaveNullForager() throws DataException {
        Forager forager = null;
        Result<Forager> result = service.add(forager);
        assertFalse(result.isSuccess());
    }

    void shouldNotSaveBlankFirstName() throws DataException {
        Forager forager = new Forager();
        forager.setFirstName("");
        forager.setLastName("Doe");
        forager.setState(States.VA.toString());
        Result<Forager> result = service.add(forager);
        assertFalse(result.isSuccess());
    }

    void shouldNotSaveBlankLastName() throws DataException {
        Forager forager = new Forager();
        forager.setFirstName("John");
        forager.setLastName("");
        forager.setState(States.VA.toString());
        Result<Forager> result = service.add(forager);
        assertFalse(result.isSuccess());
    }

    void shouldNotSaveBlankState() throws DataException {
        Forager forager = new Forager();
        forager.setFirstName("John");
        forager.setLastName("Doe");
        forager.setState(null);
        Result<Forager> result = service.add(forager);
        assertFalse(result.isSuccess());
    }

    void shouldNotSaveDuplicates() throws DataException {
        Forager forager = new Forager();
        forager.setFirstName("John");
        forager.setLastName("Doe");
        forager.setState(States.AK.toString());
        Forager foragerDup = new Forager();
        foragerDup.setFirstName("John");
        foragerDup.setLastName("Doe");
        foragerDup.setState(States.AK.toString());
        Result<Forager> result = service.add(foragerDup);
        assertFalse(result.isSuccess());
    }

}
