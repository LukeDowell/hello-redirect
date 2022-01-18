package dev.dowell.helloredirect.proxy;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProxyController {

    @PostMapping("/legacy/password")
    public ResponseEntity<String> resetPassword(@RequestBody PasswordReset passwordReset) {
        return ResponseEntity.ok(":)");
    }
}
