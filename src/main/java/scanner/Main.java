package scanner;

import lombok.extern.slf4j.Slf4j;
import scanner.brute.BruteForceScanner;
import scanner.brute.IpBruteFilter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
public class Main {

    public static void main(String[] args) {
        List<String> params = Arrays.asList(args);
        Optional<String> source = params.stream().filter(s -> s.contains("source")).findFirst();
        Optional<String> passwords = params.stream().filter(s -> s.contains("passwords")).findFirst();

        if (source.isEmpty()) {
            log.error("source cannot be empty!");
            System.exit(0);
        }

        List<String> listSources = SourceReader.readSource(source.get().split(":")[1]);
        List<String> listPasswords = passwords
                .map(s -> SourceReader.readSource(s.split(":")[1]))
                .orElseGet(() -> Collections.singletonList("12345"));

        if (params.contains("-b")) {
            final BruteForceScanner bruteForceScanner = new BruteForceScanner();
            int i = 0;
            for (String ip : listSources) {
                bruteForceScanner.brute(ip, listPasswords.toArray(new String[0]));
                log.info("progress: {} {}/{}", ip, ++i, listSources.size());
            }
            if (params.contains("-r"))
                IpBruteFilter.cleaning(source.get().split(":")[1]);
        }

        if (params.contains("-c")) {
            final CameraScanner scanner = new CameraScanner();
            int c = 0;
            int allAddresses = 0;
            for (String range : listSources) {
                int count = scanner.prepareSinglePortScanning(range, 8000);
                scanner.scanning();
                log.info("progress: {} {}/{}", range, ++c, listSources.size());
                allAddresses += count;
            }
            log.info("addresses checked = " + allAddresses);
        }
        System.exit(0);
    }

}