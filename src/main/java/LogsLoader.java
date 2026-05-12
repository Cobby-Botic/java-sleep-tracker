import ru.yandex.practicum.sleeptracker.SleepStatus;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

public class LogsLoader {

    public List<SleepingSession> downloadSession(String pathFile) throws IOException {
        final DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
        List<SleepingSession> sessions;
        try (Stream<String> lines = Files.lines(Paths.get(pathFile), StandardCharsets.UTF_8)) {
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
            throw new IOException("Файл не найден");
        }
    }
}
