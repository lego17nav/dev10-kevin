package learn.field_agent.controllers;


import learn.field_agent.domain.Result;
import learn.field_agent.domain.SecurityClearanceService;
import learn.field_agent.models.SecurityClearance;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"localhost:3000"})
@RequestMapping("/api/security")

public class SecurityClearanceController {

    private final SecurityClearanceService service;

    public SecurityClearanceController(SecurityClearanceService sc){this.service = sc;}

    @GetMapping
    public List<SecurityClearance> findAll() {return service.findAll();}

    @GetMapping("/{securityId}")
    public ResponseEntity<SecurityClearance> findById(@PathVariable int securityId) {
        SecurityClearance sc = service.findById(securityId);
        if (sc == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(sc);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody SecurityClearance securityClearance) {
        Result<SecurityClearance> result = service.add(securityClearance);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{Id}")
    public ResponseEntity<Object> update(@PathVariable int Id, @RequestBody SecurityClearance sc) {
        if (Id != sc.getSecurityClearanceId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<SecurityClearance> result = service.update(sc);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{securityId}")
    public ResponseEntity<Void> deleteById(@PathVariable int securityId) {
        if (service.delete(securityId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
