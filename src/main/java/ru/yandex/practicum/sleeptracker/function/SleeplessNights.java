package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.SleepAnalizator;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Stream;

public class SleeplessNights implements SleepAnalizator {
    private final String description = "Количество бессонных ночей";

    @Override
    public SleepAnalysisResult analyze(List<SleepingSession> sleepingSessions) {

        if (sleepingSessions.isEmpty()) {
            return new SleepAnalysisResult(description, 0);
        }

        LocalDate dayStart = sleepingSessions.get(0).getStartSession().toLocalDate();
        LocalDate dayEnd = sleepingSessions.get(sleepingSessions.size() - 1).getEndSession().toLocalDate();
        long nights = ChronoUnit.DAYS.between(dayStart, dayEnd);

        long sleeplessNights = Stream.iterate(dayStart, date -> date.plusDays(1))
                .limit(nights)
                .filter(day -> {
                    LocalDateTime nightStart = day.plusDays(1).atStartOfDay();
                    LocalDateTime nightEnd = day.plusDays(1).atTime(6, 0);

                    return sleepingSessions.stream()
                            .noneMatch(sleepingSession ->
                                    (sleepingSession.getStartSession().isBefore(nightEnd))
                                            && (sleepingSession.getEndSession().isAfter(nightStart))
                            );
                })
                .count();
        return new SleepAnalysisResult(description, sleeplessNights);
    }
}