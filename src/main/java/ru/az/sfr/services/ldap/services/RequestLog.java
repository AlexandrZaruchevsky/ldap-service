package ru.az.sfr.services.ldap.services;

import lombok.Getter;
import org.springframework.lang.NonNull;

import java.util.Optional;
import java.util.stream.Stream;

public enum RequestLog {

    HEADERS("headers"),
    ATTRIBUTES("attributes");

    @Getter
    private final String name;

    RequestLog(String name) {
        this.name=name;
    }

    public static RequestLog getByName(@NonNull String name) throws Exception {
        return Stream.of(RequestLog.values())
                .filter(requestLog -> name.equalsIgnoreCase(requestLog.getName()))
                .findFirst()
                .orElseThrow(()-> new Exception("Enum not found"));
    }

}
