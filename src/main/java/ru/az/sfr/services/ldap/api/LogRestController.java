package ru.az.sfr.services.ldap.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.az.sfr.services.ldap.services.LogService;

@RestController
@RequestMapping("log")
public class LogRestController {

    private final LogService logService;

    public LogRestController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping(headers = "Accept=application/json")
    public ResponseEntity<String> getLog(){
        return ResponseEntity.ok(logService.getLog());
    }

}
