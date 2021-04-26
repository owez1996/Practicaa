package controller;

import entity.Doctors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import service.DoctorsService;

import java.util.List;

@RestController
@RequestMapping(value = "api/doctor", produces = MediaType.APPLICATION_JSON_VALUE)
public class DoctorsController {

    @Autowired
    private DoctorsService doctorsService;

    @GetMapping
    public ResponseEntity<List<Doctors>> get() {
        List<Doctors> doctors = doctorsService.read();
        if (doctors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctors> getById(@PathVariable int id) {
        Doctors doctors = doctorsService.read(id);
        if (doctors == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> put(@RequestBody Doctors doctors) {
        doctorsService.save(doctors);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> post(@RequestBody Doctors doctors) {
        doctorsService.save(doctors);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        doctorsService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }}
