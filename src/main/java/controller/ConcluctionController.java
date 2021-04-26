package controller;

import entity.Conclusion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ConclusionService;

import java.util.List;

@RestController
@RequestMapping(value = "api/concluction", produces = MediaType.APPLICATION_JSON_VALUE)
public class ConcluctionController {
    @Autowired
    private ConclusionService conclusionService;

    @GetMapping
    public ResponseEntity<List<Conclusion>> get() {
        List<Conclusion> conclusions = conclusionService.read();
        if (conclusions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(conclusions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conclusion> getById(@PathVariable int id) {
        Conclusion conclusion = conclusionService.read(id);
        if (conclusion == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(conclusion, HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> put(@RequestBody Conclusion conclusion) {
        conclusionService.save(conclusion);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> post(@RequestBody Conclusion conclusion) {
        conclusionService.save(conclusion);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        conclusionService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
