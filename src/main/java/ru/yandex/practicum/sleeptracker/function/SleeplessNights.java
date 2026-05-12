package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.SleepAnalizator;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Stream;

public class SleeplessNights implements SleepAnalizator {

    private final String description = "Количество бессонных ночей";

    @Override
    public SleepAnalysisResult analyze(List<SleepingSession> sleepingSessions) {

        if (sleepingSessions.isEmpty()) {
            return new SleepAnalysisResult(description, 0L);
        }

        LocalDateTime firstStart = sleepingSessions.get(0).getStartSession();
        LocalDateTime lastEnd = sleepingSessions.get(sleepingSessions.size() - 1).getEndSession();

        LocalDate startDate = firstStart.toLocalTime().isAfter(LocalTime.of(6, 0))
                ? firstStart.toLocalDate().plusDays(1)
                : firstStart.toLocalDate();

        LocalDate endDate = lastEnd.toLocalDate();

        long nights = ChronoUnit.DAYS.between(startDate, endDate) + 1;

        long sleeplessNights = Stream.iterate(startDate, date -> date.plusDays(1))
                .limit(nights)
                .filter(day -> {
                    LocalDateTime nightStart = day.atStartOfDay();
                    LocalDateTime nightEnd = day.atTime(6, 0);

                    return sleepingSessions.stream()
                            .noneMatch(session ->
                                    session.getStartSession().isBefore(nightEnd)
                                            && session.getEndSession().isAfter(nightStart)
                            );
                })
                .count();

        return new SleepAnalysisResult(description, sleeplessNights);
    }
}