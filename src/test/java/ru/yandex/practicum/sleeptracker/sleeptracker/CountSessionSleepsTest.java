package ru.yandex.practicum.sleeptracker.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.SleepStatus;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.function.CountSessionSleeps;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountSessionSleepsTest {

    CountSessionSleeps countSessionSleeps = new CountSessionSleeps();
    DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

    @Test
    public void shouldReturnZeroWhenNoSessions() {
        List<SleepingSession> sleepingSessions = List.of();
        assertEquals(0, (int) countSessionSleeps.analyze(sleepingSessions).getResult());
    }


    @Test
    public void shouldReturnCorrectCountSessions() {
        LocalDateTime date1 = LocalDateTime.parse("01.01.26 23:00", formatDate);
        LocalDateTime date2 = LocalDateTime.parse("01.02.26 06:00", formatDate);
        SleepingSession sleep1 = new SleepingSession(date1, date2, SleepStatus.GOOD);
        SleepingSession sleep2 = new SleepingSession(date1, date2, SleepStatus.GOOD);
        SleepingSession sleep3 = new SleepingSession(date1, date2, SleepStatus.GOOD);
        List<SleepingSession> sleepingSessions = List.of(sleep1, sleep2, sleep3);
        assertEquals(3, (int) countSessionSleeps.analyze(sleepingSessions).getResult());
    }
}
