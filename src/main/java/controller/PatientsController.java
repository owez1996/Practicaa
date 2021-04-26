package controller;

import entity.Appeal;
import entity.Patients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.PatientsService;

import java.util.List;

@RestController
@RequestMapping(value = "api/patient", produces = MediaType.APPLICATION_JSON_VALUE)
public class PatientsController {
    @Autowired
    private PatientsService patientsService;

    @GetMapping
    public ResponseEntity<List<Patients>> get() {
        List<Patients> patients = patientsService.read();
        if (patients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patients> getById(@PathVariable int id) {
        Patients patients = patientsService.read(id);
        if (patients == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> put(@RequestBody Patients patients) {
        patientsService.save(patients);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> post(@RequestBody Patients patients) {
        patientsService.save(patients);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@PathVariable int id) {
        patientsService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
