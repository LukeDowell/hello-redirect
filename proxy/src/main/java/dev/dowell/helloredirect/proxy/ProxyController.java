package dev.dowell.helloredirect.proxy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/legacy")
public class ProxyController {

    private final String proxyTarget;
    private final RestTemplate restTemplate;

    public ProxyController(@Value("{proxy.target}") String proxyTarget, RestTemplate restTemplate) {
        this.proxyTarget = proxyTarget;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/**") // Does this work?
    public ResponseEntity<Object> putProxy() {
        return ResponseEntity.ok("");
    }

    @PostMapping("/**")
    public ResponseEntity<Object> postProxy(@RequestBody Object body) {
        return ResponseEntity.ok("");
    }
}
