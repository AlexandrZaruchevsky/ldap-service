package ru.az.sfr.services.ldap.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.az.sfr.services.ldap.dto.EmployeeDtoV1;
import ru.az.sfr.services.ldap.model.UserAD;
import ru.az.sfr.services.ldap.services.LdapService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api")
public class MainRestController {

    private final LdapService ldapService;

    public MainRestController(LdapService ldapService) {
        this.ldapService = ldapService;
    }

    @GetMapping("users")
    public List<UserAD> getUserADList() {
        return ldapService.getUserADList();
    }

    @GetMapping("employees")
    public List<EmployeeDtoV1> getEmployeeList() {
        return ldapService.getEmployeeList();
    }

    @GetMapping
    public ResponseEntity<?> info(
            @RequestHeader Map<String, String> headers,
            HttpServletRequest req
    ) throws IOException {
        return ResponseEntity.ok("Info");
    }

    @GetMapping("auth")
    public ResponseEntity<Boolean> isAuth(
            @RequestParam(required = false, defaultValue = "") String username,
            @RequestParam(required = false, defaultValue = "") String password
    ) {
        return ResponseEntity.ok(ldapService.ldapAuth(username, password));
    }
}
