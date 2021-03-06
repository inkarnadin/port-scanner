package scanner;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Resource reading class.
 *
 * @author inkarnadin
 */
@Slf4j
public class SourceReader {

    /**
     * Gets a list of values from the specified file.
     *
     * @param path file location
     * @return list of values. Returns an empty list if the file is not found
     */
    public static List<String> readSource(String path) {
        List<String> sources = new ArrayList<>();
        try {
            if (Objects.isNull(path))
                return sources;

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))))) {
                Stream<String> lines = reader.lines();
                lines.forEach(sources::add);
                lines.close();
            }
            return sources;
        } catch (Exception xep) {
            log.error("Error during file {} opening: {}", path, xep.getMessage());
        }

        return sources;
    }

    /**
     * Calculate file checksum.
     *
     * @param path file location
     */
    @SuppressWarnings({"UnstableApiUsage", "deprecation"})
    public static String checksum(String path) {
        try {
            HashCode checksum = Files.asByteSource(new File(path)).hash(Hashing.md5());
            return checksum.toString().toUpperCase();
        } catch (Exception xep) {
            log.error("Error during calculate checksum: {}", xep.getMessage());
        }
        return "";
    }

}