package scanner.analyze;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

/**
 * Store for addresses which will be checking again.
 *
 * @author inkarnadin
 */
public class ProblemHolder {

    @Getter
    private static final Set<ProblemTarget<?>> store = new HashSet<>();

    /**
     * Save problem target to store.
     *
     * @param target ip
     * @return {@code true} if saved success
     */
    public static boolean save(ProblemTarget<?> target) {
        return store.add(target);
    }

    /**
     * Clear problem target list.
     */
    public static void clear() {
        store.clear();
    }

}