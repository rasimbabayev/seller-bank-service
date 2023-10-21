package com.reausnta.selllerbank.persistent;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class RefSessionHolder {
    private final ConcurrentMap<String, Set<String>> refToSessionsMap = new ConcurrentHashMap<>();
    private final ConcurrentMap<String, String> sessionToRefMap = new ConcurrentHashMap<>();

    @NonNull
    public Set<String> getSessions(String reference) {
        return Optional.of(refToSessionsMap)
                .map(r -> r.get(reference))
                .orElse(Set.of());
    }

    public void addRefSessionLink(String reference, String session) {
        refToSessionsMap.computeIfAbsent(reference, k -> new HashSet<>()).add(session);
        sessionToRefMap.put(session, reference);

        System.out.println(refToSessionsMap);
        System.out.println(sessionToRefMap);
    }

    public void removeReference(String reference) {
        if (refToSessionsMap.containsKey(reference)) {
            refToSessionsMap.get(reference).forEach(sessionToRefMap::remove);
        }
        refToSessionsMap.remove(reference);

        System.out.println(refToSessionsMap);
        System.out.println(sessionToRefMap);
    }

    public void removeSession(String session) {
        refToSessionsMap.forEach((ref, sessions) -> sessions.remove(session));
        refToSessionsMap.entrySet().removeIf(entry -> entry.getValue().isEmpty());
        sessionToRefMap.remove(session);

        System.out.println(refToSessionsMap);
        System.out.println(sessionToRefMap);
    }
}
