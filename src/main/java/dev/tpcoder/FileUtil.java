package dev.tpcoder;

import net.datafaker.Faker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.IntStream;

public class FileUtil {

    private static final String FILE_PATH = "data.csv";

    public static void writingFile() {
        Faker faker = new Faker();
        try (var writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            IntStream.range(0, 10_000_000)
                    .forEach((n) -> {
                        String firstName = faker.name().firstName();
                        String lastName = faker.name().lastName();
                        String email = faker.internet().emailAddress();
                        StringBuilder sb = new StringBuilder();
                        sb.append(firstName)
                                .append(",")
                                .append(lastName)
                                .append(",")
                                .append(email)
                                .append("\n");
                        try {
                            writer.write(sb.toString());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFullFile() {
        try {
            var dataList = Files.readAllLines(Paths.get(FILE_PATH));
            for (var line : dataList) {
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void readFile() {
        try (var reader = Files.newBufferedReader(Paths.get(FILE_PATH))) {
            reader
                    .lines()
                    .forEach((l) -> System.out.println(l));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
