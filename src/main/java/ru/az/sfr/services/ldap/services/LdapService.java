package ru.az.sfr.services.ldap.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.filter.LikeFilter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.az.sfr.services.ldap.dto.EmployeeDtoV1;
import ru.az.sfr.services.ldap.model.UserAD;
import ru.az.sfr.services.ldap.model.UserADMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LdapService {

    private final List<UserAD> users = Collections.synchronizedList(new ArrayList<>());

    private final LdapTemplate ldapTemplate;

    @Value("${spring.ldap.prefix-user}")
    private String prefix;

    public LdapService(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }

    public synchronized List<UserAD> getUserADList() {
        return users;
    }

    public synchronized List<EmployeeDtoV1> getEmployeeList() {
        return users.stream()
                .map(EmployeeDtoV1::createFromUserAD)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(employee -> employee.getDescription() != null && !"".equalsIgnoreCase(employee.getDescription().trim()))
                .collect(Collectors.toList());
    }

    @Scheduled(fixedRateString = "${spring.task.ldap.interval}", timeUnit = TimeUnit.MINUTES)
    private void getUserListFromLdapServer() {
        log.info("Load users from AD");
        AndFilter filter = new AndFilter();
        filter.and(new EqualsFilter("objectclass", "person"));
        filter.and(new LikeFilter("sAMAccountName", prefix));
        long t0 = System.currentTimeMillis();
        List<UserAD> usersFromLdap = ldapTemplate.search(
                "", filter.encode(),
                new UserADMapper());
        log.info("Users loaded. Time execution - {}. Amount - {}", System.currentTimeMillis() - t0, usersFromLdap.size());
        synchronized (users) {
            users.clear();
            users.addAll(usersFromLdap);
        }
    }

}
