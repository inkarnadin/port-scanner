package scanner;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Slf4j
public class SourceReader {

    @SneakyThrows
    public static List<String> readSource(String path) {
        List<String> sources = new ArrayList<>();
        if (Objects.isNull(path))
            return sources;

        InputStream in = new FileInputStream(new File(path));
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            Stream<String> lines = reader.lines();
            lines.forEach(sources::add);
            lines.close();
        }
        return sources;
    }

}