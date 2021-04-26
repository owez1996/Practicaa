package controller;


import entity.Appeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.AppealService;

import java.util.List;

@RestController
@RequestMapping(value = "api/appeal", produces = MediaType.APPLICATION_JSON_VALUE)
public class AppealController {

    @Autowired
    private AppealService appealService;

    @GetMapping
    public ResponseEntity<List<Appeal>> get() {
        List<Appeal> appeals = appealService.read();
        if (appeals.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(appeals, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appeal> getById(@PathVariable int id) {
        Appeal appeal = appealService.read(id);
        if (appeal == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(appeal, HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> put(@RequestBody Appeal appeal) {
        appealService.save(appeal);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> post(@RequestBody Appeal appeal) {
        appealService.save(appeal);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@PathVariable int id) {
        appealService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
