package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

    public ArrayList<String> getContentAsSArrayList() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        ArrayList<String> data = new ArrayList<>();
        String s;
        while((s=br.readLine())!=null) {
            data.add(s);
        }
        br.close();

        return data;
    }

    public String[] getContentAsStringArray() throws IOException {
        return getContentAsSArrayList().toArray(new String[]{});
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

