package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.Chronotype;
import ru.yandex.practicum.sleeptracker.SleepAnalizator;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DetermineChronotype implements SleepAnalizator {

    private final String description = "Ваш хронотип";

    @Override
    public SleepAnalysisResult analyze(List<SleepingSession> sleepingSessions) {

        if (sleepingSessions.isEmpty()) {
            return new SleepAnalysisResult(description, Chronotype.PIGEON);
        }

        LocalDate dayStart = sleepingSessions.get(0).getStartSession().toLocalDate();
        LocalDate dayEnd = sleepingSessions.get(sleepingSessions.size() - 1).getEndSession().toLocalDate();
        long nights = ChronoUnit.DAYS.between(dayStart, dayEnd) + 1; //тут оставил +1 потому что between не
        // учитывает крайний день

        Map<Chronotype, Long> chronoType = Stream.iterate(dayStart, date -> date.plusDays(1))
                .limit(nights)
                .map(day -> {
                    LocalDateTime nightStart = day.atStartOfDay();
                    LocalDateTime nightEnd = day.atTime(6, 0);

                    return sleepingSessions.stream()
                            .filter(session ->
                                    session.getStartSession().isBefore(nightEnd)
                                            && session.getEndSession().isAfter(nightStart)
                            )
                            .findFirst();
                })
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(this::determineChronotype)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));

        Optional<Map.Entry<Chronotype, Long>> maxEntry =
                chronoType.entrySet().stream()
                        .max(Comparator.comparing(Map.Entry::getValue));

        long maxValue = maxEntry
                .map(Map.Entry::getValue)
                .orElse(0L);

        long countMax = chronoType.values().stream()
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

    public Chronotype determineChronotype(SleepingSession session) {
        LocalTime start = session.getStartSession().toLocalTime();
        LocalTime end = session.getEndSession().toLocalTime();

        LocalTime owlSleep = LocalTime.of(23, 0);
        LocalTime owlAwake = LocalTime.of(9, 0);

        LocalTime larkSleep = LocalTime.of(22, 0);
        LocalTime larkAwake = LocalTime.of(7, 0);

        boolean isNightStart = start.isBefore(LocalTime.of(6, 0));

        if ((start.isAfter(owlSleep) || isNightStart) && end.isAfter(owlAwake)) {
            return Chronotype.OWL;
        } else if (start.isBefore(larkSleep) && end.isBefore(larkAwake)) {
            return Chronotype.LARK;
        } else {
            return Chronotype.PIGEON;
        }
    }
}