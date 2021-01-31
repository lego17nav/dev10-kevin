package learn.field_agent.domain;

import learn.field_agent.data.SecurityClearanceRepository;
import learn.field_agent.models.SecurityClearance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

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
        Result<SecurityClearance> result = service.add(sc);
        assertEquals(ResultType.SUCCESS,result.getType());
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
    void shouldNotUpdateIfNoMatch() {
        SecurityClearance sc = makeSecurityClearance();
        sc.setSecurityClearanceId(100);
        when(repository.update(sc)).thenReturn(false);
        Result<SecurityClearance> result = service.update(sc);
        assertEquals(ResultType.NOT_FOUND,result.getType());
    }


    private SecurityClearance makeSecurityClearance() {
        SecurityClearance sc = new SecurityClearance();
        sc.setName("Omega Secret");
        return sc;
    }

}