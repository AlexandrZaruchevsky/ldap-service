package ru.az.sfr.services.ldap.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.az.sfr.services.ldap.dto.EmployeeDtoV1;
import ru.az.sfr.services.ldap.model.UserAD;
import ru.az.sfr.services.ldap.services.LdapService;

import java.util.List;

@RestController
@RequestMapping("api")
public class MainRestController {

    private final LdapService ldapService;

    public MainRestController(LdapService ldapService) {
        this.ldapService = ldapService;
    }

    @GetMapping("users")
    public List<UserAD> getUserADList(){
        return ldapService.getUserADList();
    }

    @GetMapping("employees")
    public List<EmployeeDtoV1> getEmployeeList(){
        return ldapService.getEmployeeList();
    }

}
