package ru.yandex.practicum.sleeptracker.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.SleepStatus;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.function.CountBadStatusSessions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountBadStatusSessionsTest {
    CountBadStatusSessions countBadStatusSessions = new CountBadStatusSessions();
    DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

    LocalDateTime date1 = LocalDateTime.parse("01.02.26 23:00", formatDate);
    LocalDateTime date2 = LocalDateTime.parse("02.02.26 06:00", formatDate);
    LocalDateTime date3 = LocalDateTime.parse("02.02.26 23:00", formatDate);
    LocalDateTime date4 = LocalDateTime.parse("03.02.26 03:00", formatDate);
    LocalDateTime date5 = LocalDateTime.parse("03.02.26 23:00", formatDate);
    LocalDateTime date6 = LocalDateTime.parse("04.02.26 05:00", formatDate);

    @Test
    public void shouldReturnOnlyBad() {
        SleepingSession sleep1 = new SleepingSession(date1, date2, SleepStatus.GOOD);
        SleepingSession sleep2 = new SleepingSession(date3, date4, SleepStatus.GOOD);
        SleepingSession sleep3 = new SleepingSession(date5, date6, SleepStatus.GOOD);
        List<SleepingSession> list = List.of(sleep1, sleep2, sleep3);

        assertEquals(0, countBadStatusSessions.analyze(list).getResult());
    }

    @Test
    public void shouldReturn1Bad() {
        SleepingSession sleep4 = new SleepingSession(date1, date2, SleepStatus.GOOD);
        SleepingSession sleep5 = new SleepingSession(date3, date4, SleepStatus.BAD);
        SleepingSession sleep6 = new SleepingSession(date5, date6, SleepStatus.GOOD);
        List<SleepingSession> list1 = List.of(sleep4, sleep5, sleep6);

        assertEquals(1, countBadStatusSessions.analyze(list1).getResult());
    }
}
