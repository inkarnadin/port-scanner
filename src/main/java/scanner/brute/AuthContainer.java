package scanner.brute;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Auth result store.
 *
 * @author inkarnadin
 */
@RequiredArgsConstructor
public class AuthContainer {

    @Getter
    private final String ip;
    private final Map<String, AuthState> auth = new HashMap<>();

    public void put(String password, AuthState state) {
        auth.put(password, state);
    }

    /**
     * Return all success auth results.
     *
     * @return list of successfully verified passwords
     */
    public List<String> getOnlyAuth() {
        return auth.entrySet().stream()
                .filter(x -> x.getValue() == AuthState.AUTH)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    /**
     * Return state of empty credentials auth.
     *
     * @return The first successfully result - empty credentials. If missing returns {@link AuthState#NOT_AVAILABLE} state.
     */
    public AuthState getEmptyCredentialsAuth() {
        return auth.getOrDefault(null, AuthState.NOT_AVAILABLE);
    }

}