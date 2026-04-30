package ru.yandex.practicum.sleeptracker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

public class LogsLoader {

    public List<SleepingSession> downloadSession(String pathFile) throws IOException {
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
        List<SleepingSession> sessions;
        try (Stream<String> lines = Files.lines(Paths.get(pathFile))) {
            sessions = lines
                    .map(line -> {
                        String[] parts = line.split(";");
                        LocalDateTime start = LocalDateTime.parse(parts[0], formatDate);
                        LocalDateTime end = LocalDateTime.parse(parts[1], formatDate);
                        SleepStatus status = SleepStatus.valueOf(parts[2]);
                        return new SleepingSession(start, end, status);
                    })
                    .toList();
            return sessions;
        } catch (IOException e) {
            throw new RuntimeException("Файл не найден");
        }
    }
}
