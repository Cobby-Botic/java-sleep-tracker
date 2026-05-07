package ru.yandex.practicum.sleeptracker.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.SleepStatus;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.function.SleeplessNights;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SleeplessNightsTest {
    SleeplessNights sleeplessNights = new SleeplessNights();
    DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

    @Test
    public void shouldReturnSleeplessNights() {
        SleepingSession sleep1 = new SleepingSession(LocalDateTime.parse("01.02.26 23:00", formatDate),
                LocalDateTime.parse("02.02.26 06:00", formatDate),
                SleepStatus.GOOD);
        SleepingSession sleep3 = new SleepingSession(LocalDateTime.parse("03.02.26 23:00", formatDate),
                LocalDateTime.parse("04.02.26 05:00", formatDate),
                SleepStatus.GOOD);
        List<SleepingSession> list = List.of(sleep1, sleep3);

        assertEquals(1L, sleeplessNights.analyze(list).getResult());
    }

    @Test
    public void shoulReturn0WhenListIsEmpty() {
        List<SleepingSession> list1 = List.of();
        assertEquals(0, sleeplessNights.analyze(list1).getResult());
    }

    @Test
    public void shouldCountNightWhenLoggingStartsAfterMidnight() {
        SleepingSession sleep = new SleepingSession(
                LocalDateTime.parse("01.01.26 01:00", formatDate),
                LocalDateTime.parse("01.01.26 07:00", formatDate),
                SleepStatus.GOOD
        );

        List<SleepingSession> list = List.of(sleep);

        assertEquals(0L, sleeplessNights.analyze(list).getResult());
    }

    @Test
    public void shouldCorrectlyCountSleeplessNightsBetweenMonths() {
        SleepingSession sleep = new SleepingSession(
                LocalDateTime.parse("31.01.26 23:00", formatDate),
                LocalDateTime.parse("01.02.26 05:00", formatDate),
                SleepStatus.GOOD
        );

        List<SleepingSession> list = List.of(sleep);

        assertEquals(0L, sleeplessNights.analyze(list).getResult());
    }
}
