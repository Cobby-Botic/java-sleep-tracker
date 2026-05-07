package ru.yandex.practicum.sleeptracker.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.Chronotype;
import ru.yandex.practicum.sleeptracker.SleepStatus;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.function.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DetermineChronotypeTest {
    DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
    DetermineChronotype determineChronotype = new DetermineChronotype();

    @Test
    public void shouldReturn0WhenListIsEmpty() {
        List<SleepingSession> list = List.of();

        assertEquals(0, determineChronotype.analyze(list).getResult());
    }

    @Test
    public void shouldReturnLarkType() {
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

        LocalDateTime date1 = LocalDateTime.parse("01.02.26 21:01", formatDate);
        LocalDateTime date2 = LocalDateTime.parse("02.02.26 06:59", formatDate);

        LocalDateTime date3 = LocalDateTime.parse("02.02.26 23:01", formatDate);
        LocalDateTime date4 = LocalDateTime.parse("03.02.26 10:01", formatDate);

        SleepingSession sleep1 = new SleepingSession(date1, date2, SleepStatus.GOOD);
        SleepingSession sleep2 = new SleepingSession(date3, date4, SleepStatus.GOOD);

        List<SleepingSession> list = List.of(sleep1, sleep2);

        assertEquals(Chronotype.PIGEON, determineChronotype.analyze(list).getResult());
    }
}