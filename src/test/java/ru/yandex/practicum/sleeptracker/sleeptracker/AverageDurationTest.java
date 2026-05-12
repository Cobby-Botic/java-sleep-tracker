package ru.yandex.practicum.sleeptracker.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.SleepStatus;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.function.AverageDuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AverageDurationTest {

    AverageDuration averageDuration = new AverageDuration();

    @Test
    public void shouldReturnAverageSession() {
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
        LocalDateTime date1 = LocalDateTime.parse("01.02.26 23:00", formatDate);
        LocalDateTime date2 = LocalDateTime.parse("02.02.26 06:00", formatDate);
        LocalDateTime date3 = LocalDateTime.parse("02.02.26 23:00", formatDate);
        LocalDateTime date4 = LocalDateTime.parse("03.02.26 03:00", formatDate);
        LocalDateTime date5 = LocalDateTime.parse("03.02.26 23:00", formatDate);
        LocalDateTime date6 = LocalDateTime.parse("04.02.26 05:00", formatDate);
        SleepingSession sleep1 = new SleepingSession(date1, date2, SleepStatus.GOOD);
        SleepingSession sleep2 = new SleepingSession(date3, date4, SleepStatus.GOOD);
        SleepingSession sleep3 = new SleepingSession(date5, date6, SleepStatus.GOOD);
        List<SleepingSession> list = List.of(sleep1, sleep2, sleep3);

        assertEquals("5ч 40м", averageDuration.analyze(list).getResult());
    }

    @Test
    public void shouldReturnZero() {
        List<SleepingSession> list1 = new ArrayList<>();
        assertEquals("0ч 0м", averageDuration.analyze(list1).getResult());
    }
}
