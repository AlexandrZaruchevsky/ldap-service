package ru.az.sfr.services.ldap.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class LogService {

    private final List<LogEntity> logStore = Collections.synchronizedList(new ArrayList<>());

    public void requestExecute(@NonNull Map<String, Map<String, String>> log) {
        String remoteAddr = log.get(RequestLog.ATTRIBUTES.getName()).get("RemoteAddr");
        if (remoteAddr != null) {
            synchronized (logStore) {
                if (logStore.size() > 10000) {
                    logStore.clear();
                }
                logStore.add(new LogEntity(LocalDateTime.now(), remoteAddr, log));
            }
        }
    }

    public synchronized String getLog() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.findAndRegisterModules();
            return objectMapper.writeValueAsString(logStore);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "[]";
        }
    }

    public void clearLog() {
        synchronized (logStore) {
            logStore.clear();
        }
    }

    @Data
    @AllArgsConstructor
    static class LogEntity {
        private LocalDateTime requestTime;
        private String ipAddress;
        private Map<String, Map<String, String>> log;
    }

}
