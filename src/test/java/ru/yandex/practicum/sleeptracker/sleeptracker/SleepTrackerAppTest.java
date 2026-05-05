package ru.yandex.practicum.sleeptracker.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.function.*;
import ru.yandex.practicum.sleeptracker.function.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    public void shouldRreturnMinSession() {
        MinDuration minDuration = new MinDuration();
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
        LocalDateTime date1 = LocalDateTime.parse("01.01.26 23:00", formatDate);
        LocalDateTime date2 = LocalDateTime.parse("02.02.26 06:00", formatDate);
        LocalDateTime date3 = LocalDateTime.parse("02.02.26 23:00", formatDate);
        LocalDateTime date4 = LocalDateTime.parse("03.02.26 03:00", formatDate);
        LocalDateTime date5 = LocalDateTime.parse("03.02.26 23:00", formatDate);
        LocalDateTime date6 = LocalDateTime.parse("04.02.26 05:00", formatDate);
        SleepingSession sleep1 = new SleepingSession(date1, date2, SleepStatus.GOOD);
        SleepingSession sleep2 = new SleepingSession(date3, date4, SleepStatus.GOOD);
        SleepingSession sleep3 = new SleepingSession(date5, date6, SleepStatus.GOOD);
        List<SleepingSession> list = List.of(sleep1, sleep2, sleep3);
        List<SleepingSession> list1 = new ArrayList<>();

        assertEquals("0ч 0м", minDuration.analyze(list1).getResult());
        assertEquals("4ч 0м", minDuration.analyze(list).getResult());
    }

    @Test
    public void shouldReturnMaxSession() {
        MaxDuration maxDuration = new MaxDuration();
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
        LocalDateTime date1 = LocalDateTime.parse("01.01.26 23:00", formatDate);
        LocalDateTime date2 = LocalDateTime.parse("02.01.26 06:00", formatDate);
        LocalDateTime date3 = LocalDateTime.parse("02.02.26 23:00", formatDate);
        LocalDateTime date4 = LocalDateTime.parse("03.02.26 03:00", formatDate);
        LocalDateTime date5 = LocalDateTime.parse("03.02.26 23:00", formatDate);
        LocalDateTime date6 = LocalDateTime.parse("04.02.26 05:00", formatDate);
        SleepingSession sleep1 = new SleepingSession(date1, date2, SleepStatus.GOOD);
        SleepingSession sleep2 = new SleepingSession(date3, date4, SleepStatus.GOOD);
        SleepingSession sleep3 = new SleepingSession(date5, date6, SleepStatus.GOOD);
        List<SleepingSession> list = List.of(sleep1, sleep2, sleep3);
        List<SleepingSession> list1 = new ArrayList<>();
        assertEquals("0ч 0м", maxDuration.analyze(list1).getResult());
        assertEquals("7ч 0м", maxDuration.analyze(list).getResult());
    }

    @Test
    public void shouldReturnAverageSession() {
        AverageDuration averageDuration = new AverageDuration();
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
        List<SleepingSession> list1 = new ArrayList<>();

        assertEquals("0ч 0м", averageDuration.analyze(list1).getResult());
        assertEquals("5ч 40м", averageDuration.analyze(list).getResult());
    }

    @Test
    public void shouldReturnOnlyBad() {
        CountBadStatusSessions countBadStatusSessions = new CountBadStatusSessions();
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

        SleepingSession sleep4 = new SleepingSession(date1, date2, SleepStatus.GOOD);
        SleepingSession sleep5 = new SleepingSession(date3, date4, SleepStatus.BAD);
        SleepingSession sleep6 = new SleepingSession(date5, date6, SleepStatus.GOOD);
        List<SleepingSession> list1 = List.of(sleep4, sleep5, sleep6);

        assertEquals(0, countBadStatusSessions.analyze(list).getResult());
        assertEquals(1, countBadStatusSessions.analyze(list1).getResult());
    }

    @Test
    public void shouldReturnSleeplessNights() {
        SleeplessNights sleeplessNights = new SleeplessNights();
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
        LocalDateTime date1 = LocalDateTime.parse("01.02.26 23:00", formatDate);
        LocalDateTime date2 = LocalDateTime.parse("02.02.26 06:00", formatDate);

        LocalDateTime date3 = LocalDateTime.parse("02.02.26 23:00", formatDate);
        LocalDateTime date4 = LocalDateTime.parse("03.02.26 03:00", formatDate);

        LocalDateTime date5 = LocalDateTime.parse("03.02.26 23:00", formatDate);
        LocalDateTime date6 = LocalDateTime.parse("04.02.26 05:00", formatDate);

        LocalDateTime date7 = LocalDateTime.parse("04.02.26 11:00", formatDate);
        LocalDateTime date8 = LocalDateTime.parse("04.02.26 17:00", formatDate);

        SleepingSession sleep1 = new SleepingSession(date1, date2, SleepStatus.GOOD);
        SleepingSession sleep2 = new SleepingSession(date3, date4, SleepStatus.GOOD);
        SleepingSession sleep3 = new SleepingSession(date5, date6, SleepStatus.GOOD);
        SleepingSession sleep4 = new SleepingSession(date7, date8, SleepStatus.GOOD);
        List<SleepingSession> list = List.of(sleep1, sleep2, sleep3);
        List<SleepingSession> list1 = List.of(sleep1, sleep2, sleep4);
        assertEquals((long) 0, sleeplessNights.analyze(list).getResult());
        assertEquals((long) 1, sleeplessNights.analyze(list1).getResult());
    }

    @Test
    public void shouldReturnOwlType() {
        DetermineChronotype determineChronotype = new DetermineChronotype();
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
        LocalDateTime date1 = LocalDateTime.parse("01.02.26 23:01", formatDate);
        LocalDateTime date2 = LocalDateTime.parse("02.02.26 09:01", formatDate);

        LocalDateTime date3 = LocalDateTime.parse("02.02.26 23:01", formatDate);
        LocalDateTime date4 = LocalDateTime.parse("03.02.26 09:01", formatDate);

        LocalDateTime date5 = LocalDateTime.parse("03.02.26 23:05", formatDate);
        LocalDateTime date6 = LocalDateTime.parse("04.02.26 09:05", formatDate);
        SleepingSession sleep1 = new SleepingSession(date1, date2, SleepStatus.GOOD);
        SleepingSession sleep2 = new SleepingSession(date3, date4, SleepStatus.GOOD);
        SleepingSession sleep3 = new SleepingSession(date5, date6, SleepStatus.GOOD);

        List<SleepingSession> list = List.of(sleep1, sleep2, sleep3);

        assertEquals(Chronotype.OWL, determineChronotype.analyze(list).getResult());
    }

    @Test
    public void shouldReturnLarkType() {
        DetermineChronotype determineChronotype = new DetermineChronotype();
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
        LocalDateTime date1 = LocalDateTime.parse("01.02.26 21:59", formatDate);
        LocalDateTime date2 = LocalDateTime.parse("02.02.26 06:59", formatDate);

        LocalDateTime date3 = LocalDateTime.parse("02.02.26 21:40", formatDate);
        LocalDateTime date4 = LocalDateTime.parse("03.02.26 06:59", formatDate);

        LocalDateTime date5 = LocalDateTime.parse("03.02.26 21:59", formatDate);
        LocalDateTime date6 = LocalDateTime.parse("04.02.26 06:59", formatDate);
        SleepingSession sleep1 = new SleepingSession(date1, date2, SleepStatus.GOOD);
        SleepingSession sleep2 = new SleepingSession(date3, date4, SleepStatus.GOOD);
        SleepingSession sleep3 = new SleepingSession(date5, date6, SleepStatus.GOOD);

        List<SleepingSession> list = List.of(sleep1, sleep2, sleep3);

        assertEquals(Chronotype.LARK, determineChronotype.analyze(list).getResult());
    }

    @Test
    public void shouldReturnPigeonType() {
        DetermineChronotype determineChronotype = new DetermineChronotype();
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
        LocalDateTime date1 = LocalDateTime.parse("01.02.26 21:00", formatDate);
        LocalDateTime date2 = LocalDateTime.parse("02.02.26 07:00", formatDate);

        LocalDateTime date3 = LocalDateTime.parse("02.02.26 21:00", formatDate);
        LocalDateTime date4 = LocalDateTime.parse("03.02.26 07:00", formatDate);

        LocalDateTime date5 = LocalDateTime.parse("03.02.26 21:00", formatDate);
        LocalDateTime date6 = LocalDateTime.parse("04.02.26 07:00", formatDate);
        SleepingSession sleep1 = new SleepingSession(date1, date2, SleepStatus.GOOD);
        SleepingSession sleep2 = new SleepingSession(date3, date4, SleepStatus.GOOD);
        SleepingSession sleep3 = new SleepingSession(date5, date6, SleepStatus.GOOD);

        List<SleepingSession> list = List.of(sleep1, sleep2, sleep3);

        assertEquals(Chronotype.PIGEON, determineChronotype.analyze(list).getResult());
    }

    @Test
    public void shouldReturnPigeonWhen1OwlAnd1Lark() {
        DetermineChronotype determineChronotype = new DetermineChronotype();
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
        LocalDateTime date1 = LocalDateTime.parse("01.02.26 21:01", formatDate);
        LocalDateTime date2 = LocalDateTime.parse("02.02.26 06:59", formatDate);

        LocalDateTime date3 = LocalDateTime.parse("02.02.26 22:01", formatDate);
        LocalDateTime date4 = LocalDateTime.parse("03.02.26 09:01", formatDate);

        SleepingSession sleep1 = new SleepingSession(date1, date2, SleepStatus.GOOD);
        SleepingSession sleep2 = new SleepingSession(date3, date4, SleepStatus.GOOD);

        List<SleepingSession> list = List.of(sleep1, sleep2);

        assertEquals(Chronotype.PIGEON, determineChronotype.analyze(list).getResult());
    }
}