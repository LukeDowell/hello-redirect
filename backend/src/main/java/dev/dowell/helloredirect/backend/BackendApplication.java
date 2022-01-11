package dev.dowell.helloredirect.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
@RestController
@RequestMapping("/legacy")
public class BackendApplication {

    private ConcurrentHashMap<String, String> db = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @GetMapping("/content")
    public ResponseEntity<String> content() {
        return ResponseEntity.ok(UUID.randomUUID().toString());
    }

    @GetMapping("/content/two")
    public ResponseEntity<String> contentTwo() {
        return ResponseEntity.ok(UUID.randomUUID().toString().concat(" -- 2"));
    }

    @PostMapping("/entity/{id}/{value}")
    public ResponseEntity<String> entity(@PathVariable String id, @PathVariable String value) {
        db.put(id, value);
        return ResponseEntity.ok(id);
    }
}
