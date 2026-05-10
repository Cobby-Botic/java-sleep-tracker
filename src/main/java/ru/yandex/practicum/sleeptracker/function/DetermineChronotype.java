package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.Chronotype;
import ru.yandex.practicum.sleeptracker.SleepAnalizator;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DetermineChronotype implements SleepAnalizator {

    private final String description = "Ваш хронотип";

    @Override
    public SleepAnalysisResult analyze(List<SleepingSession> sleepingSessions) {

        if (sleepingSessions.isEmpty()) {
            return new SleepAnalysisResult(description, Chronotype.PIGEON);
        }

        LocalDateTime firstStart = sleepingSessions.get(0).getStartSession();
        LocalDateTime lastEnd = sleepingSessions.get(sleepingSessions.size() - 1).getEndSession();

        // если первая сессия началась после 06:00 → пропускаем первую ночь
        LocalDate startDate = firstStart.toLocalTime().isAfter(LocalTime.of(6, 0))
                ? firstStart.toLocalDate().plusDays(1)
                : firstStart.toLocalDate();

        LocalDate endDate = lastEnd.toLocalDate();

        long nights = ChronoUnit.DAYS.between(startDate, endDate) + 1;

        Map<Chronotype, Long> stats = Stream.iterate(startDate, date -> date.plusDays(1))
                .limit(nights)

                .map(day -> {
                    LocalDateTime nightStart = day.atStartOfDay();
                    LocalDateTime nightEnd = day.atTime(6, 0);

                    List<SleepingSession> sessions = sleepingSessions.stream()
                            .filter(session ->
                                    session.getStartSession().isBefore(nightEnd)
                                            && session.getEndSession().isAfter(nightStart)
                            )
                            .toList();

                    if (sessions.isEmpty()) {
                        return null;
                    }

                    LocalTime start = sessions.stream()
                            .map(s -> s.getStartSession().toLocalTime())
                            .min(LocalTime::compareTo)
                            .get();

                    LocalTime end = sessions.stream()
                            .map(s -> s.getEndSession().toLocalTime())
                            .max(LocalTime::compareTo)
                            .get();

                    return determineChronotype(start, end);
                })

                .filter(Objects::nonNull)

                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));

        Optional<Map.Entry<Chronotype, Long>> maxEntry =
                stats.entrySet().stream()
                        .max(Map.Entry.comparingByValue());

        long maxValue = maxEntry.map(Map.Entry::getValue).orElse(0L);

        long countMax = stats.values().stream()
                .filter(v -> v == maxValue)
                .count();

        Chronotype result;

        if (countMax > 1 || countMax == 0) {
            result = Chronotype.PIGEON;
        } else {
            result = maxEntry.get().getKey();
        }

        return new SleepAnalysisResult(description, result);
    }

    public Chronotype determineChronotype(LocalTime start, LocalTime end) {

        LocalTime owlSleep = LocalTime.of(23, 0);
        LocalTime owlAwake = LocalTime.of(9, 0);

        LocalTime larkSleep = LocalTime.of(22, 0);
        LocalTime larkAwake = LocalTime.of(7, 0);

        boolean isOwl = !start.isBefore(owlSleep) && !end.isBefore(owlAwake);
        boolean isLark = !start.isAfter(larkSleep) && !end.isAfter(larkAwake);

        if (isOwl) return Chronotype.OWL;
        if (isLark) return Chronotype.LARK;
        return Chronotype.PIGEON;
    }
}