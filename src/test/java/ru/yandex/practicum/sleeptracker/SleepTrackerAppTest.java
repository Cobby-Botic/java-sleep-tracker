package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class SleepTrackerAppTest {

    @Test
    public void shouldReturnZeroWhenNoSessions() {
        CountSessionSleeps countSessionSleeps = new CountSessionSleeps();
        List<SleepingSession> sleepingSessions = List.of();
        assertEquals((int) countSessionSleeps.analyze(sleepingSessions).getResult(), 0);
    }


    @Test
    public void shouldReturnCorrectCountSessions() {
        CountSessionSleeps countSessionSleeps = new CountSessionSleeps();
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
        LocalDateTime date1 = LocalDateTime.parse("01.01.26 23:00", formatDate);
        LocalDateTime date2 = LocalDateTime.parse("01.02.26 06:00", formatDate);
        SleepingSession sleep1 = new SleepingSession(date1, date2, SleepStatus.GOOD);
        SleepingSession sleep2 = new SleepingSession(date1, date2, SleepStatus.GOOD);
        SleepingSession sleep3 = new SleepingSession(date1, date2, SleepStatus.GOOD);
        List<SleepingSession> sleepingSessions = List.of(sleep1, sleep2, sleep3);
        assertEquals((int)countSessionSleeps.analyze(sleepingSessions).getResult(), 3);
    }
}