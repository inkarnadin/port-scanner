package scanner.analyze;

/**
 * Authorization problem solution.
 *
 * @author inkarnadin
 */
public class AuthResolve implements Resolve<String> {

    /**
     * Get solution.
     *
     * @return default credentials
     */
    @Override
    public String resolve() {
        return "admin:asdf1234";
    }

}