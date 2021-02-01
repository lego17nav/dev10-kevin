package learn.field_agent.domain;

import learn.field_agent.data.SecurityClearanceRepository;
import learn.field_agent.models.SecurityClearance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class SecurityClearanceServiceTest {

    @Autowired
    SecurityClearanceService service;

    @MockBean
    SecurityClearanceRepository repository;

    @Test
    void shouldNotAddWhenNameIsEmpty() {
        SecurityClearance sc = makeSecurityClearance();
        sc.setName(null);
        Result<SecurityClearance> result = service.add(sc);
        assertEquals(ResultType.INVALID,result.getType());
    }

    @Test
    void shouldAdd() {
        SecurityClearance sc = makeSecurityClearance();
        sc.setSecurityClearanceId(0);
        SecurityClearance out = makeSecurityClearance();
        out.setSecurityClearanceId(3);
        when(repository.add(sc)).thenReturn(out);
        Result<SecurityClearance> result = service.add(sc);
        assertEquals(ResultType.SUCCESS,result.getType());
        assertEquals(3,result.getPayload().getSecurityClearanceId());
        assertNotNull(result.getPayload());
    }


    @Test
    void shouldNotAddIfIdExist() {
        SecurityClearance sc = makeSecurityClearance();
        sc.setSecurityClearanceId(1);
        Result<SecurityClearance> result = service.add(sc);
        assertEquals(ResultType.INVALID,result.getType());
    }

    @Test
    void shouldNotAddIfDuplicate() {
        when(service.findAll()).thenReturn(List.of(
                new SecurityClearance(1,"Secret")
        ));

        SecurityClearance sc = makeSecurityClearance();
        sc.setName("Secret");
        Result<SecurityClearance> result = service.add(sc);
        assertEquals(ResultType.INVALID,result.getType());
    }


    @Test
    void shouldNotUpdateWhenNameIsEmpty() {
        SecurityClearance sc = makeSecurityClearance();
        sc.setSecurityClearanceId(1);
        sc.setName(null);
        Result<SecurityClearance> result = service.update(sc);
        assertEquals(ResultType.INVALID,result.getType());
    }

    @Test
    void shouldUpdate() {
        SecurityClearance sc1 = makeSecurityClearance();
        sc1.setSecurityClearanceId(1);
        when(repository.update(sc1)).thenReturn(true);
        Result<SecurityClearance> result = service.update(sc1);
        assertEquals(ResultType.SUCCESS,result.getType());
    }

    @Test
    void shouldNotUpdateMissingId() {
        SecurityClearance sc = makeSecurityClearance();
        Result<SecurityClearance> result = service.update(sc);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotUpdateDuplicateName() {
        when(service.findAll()).thenReturn(List.of(
                new SecurityClearance(1,"Secret")
        ));
        SecurityClearance sc = makeSecurityClearance();
        sc.setSecurityClearanceId(4);
        sc.setName("Secret");
        Result<SecurityClearance> result = service.update(sc);
        assertEquals(ResultType.INVALID,result.getType());
    }


    @Test
    void shouldNotUpdateIfNoMatch() {
        SecurityClearance sc = makeSecurityClearance();
        sc.setSecurityClearanceId(100);
        when(repository.update(sc)).thenReturn(false);
        Result<SecurityClearance> result = service.update(sc);
        assertEquals(ResultType.NOT_FOUND,result.getType());
    }

    @Test
    void shouldNotDelete() {
        when(repository.delete(10)).thenReturn(false);
        assertFalse(service.delete(10));
    }
    
    @Test
    void shouldDelete() {
        when(repository.delete(1)).thenReturn(true);
        assertTrue(service.delete(1));
    }

    @Test
    void shouldNotFIndMissingID() {
        when(repository.findById(1)).thenReturn(null);
        SecurityClearance sc = service.findById(1);
        assertEquals(null,sc);
    }

    @Test
    void shouldFindId() {
        SecurityClearance sc = makeSecurityClearance();
        sc.setSecurityClearanceId(1);
        when(repository.findById(1)).thenReturn(sc);
        SecurityClearance out = service.findById(1);
        assertEquals(1,out.getSecurityClearanceId());
    }

    @Test
    void shouldFindAll() {
        when(repository.findAll()).thenReturn(List.of(
                new SecurityClearance(1,"Secret"),
                new SecurityClearance(2,"Top Secret")
        ));
        assertEquals(2,service.findAll().size());
    }


    private SecurityClearance makeSecurityClearance() {
        SecurityClearance sc = new SecurityClearance();
        sc.setName("Omega Secret");
        return sc;
    }

}