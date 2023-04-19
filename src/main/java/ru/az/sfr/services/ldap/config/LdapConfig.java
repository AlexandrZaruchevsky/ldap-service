package ru.az.sfr.services.ldap.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.DefaultDirObjectFactory;
import org.springframework.ldap.core.support.LdapContextSource;

@Configuration
public class LdapConfig {

    @Value("${spring.ldap.urls}")
    private String urls;
    @Value("${spring.ldap.username}")
    private String userDn;
    @Value("${spring.ldap.password}")
    private String password;

    @Bean
    public LdapContextSource ldapContextSource(){
        LdapContextSource lsc = new LdapContextSource();
        lsc.setUrl(urls);
        lsc.setBase("DC=0039,DC=PFR,DC=RU");
        lsc.setUserDn(userDn);
        lsc.setPassword(password);
        lsc.setReferral("follow");
        lsc.setDirObjectFactory(DefaultDirObjectFactory.class);
        return lsc;
    }

    @Bean
    public LdapTemplate ldapTemplate(){
        return new LdapTemplate(ldapContextSource());
    }


}
