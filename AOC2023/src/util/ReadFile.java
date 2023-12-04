package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReadFile {
    private String filePath;

    public ReadFile(String filePath) {
        this.filePath = filePath;
    }

    public Stream<String> getLinesStream() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        return br.lines().onClose(() -> closeQuietly(br));
    }

    public String getContentAsString() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            return br.lines().collect(Collectors.joining(System.lineSeparator()));
        }
    }

    private void closeQuietly(BufferedReader br) {
        try {
            if (br != null) {
                br.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

