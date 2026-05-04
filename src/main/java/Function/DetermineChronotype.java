package Function;

import ru.yandex.practicum.sleeptracker.Chronotype;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.*;

public class DetermineChronotype implements SleepAnalizator {
    @Override
    public SleepAnalysisResult analyze(List<SleepingSession> sleepingSessions) {
        Map<Chronotype, Long> chronoType = sleepingSessions.stream()
                .filter(session -> {
                    LocalDate date = session.getStartSession().toLocalDate();
                    LocalDateTime nightStart = date.plusDays(1).atStartOfDay();
                    LocalDateTime nightEnd = date.plusDays(1).atTime(6, 0);
                    return session.getStartSession().isBefore(nightEnd)
                            && session.getEndSession().isAfter(nightStart);
                })
                .map(this::determineChronotype)
                .collect(groupingBy(chronotype -> chronotype, counting()));

        Optional<Map.Entry<Chronotype, Long>> maxEntry =
                chronoType.entrySet().stream()
                        .max(Comparator.comparing(Map.Entry::getValue));

        long maxValue = maxEntry.get().getValue();

        long countMax = chronoType.values().stream()
                .filter(v -> v == maxValue)
                .count();

        Chronotype result;

        if (countMax > 1) {
            result = Chronotype.PIGEON;
        } else {
            result = maxEntry.get().getKey();
        }

        return new SleepAnalysisResult("Ваш хронотип", result);
    }

    public Chronotype determineChronotype (SleepingSession session) {
        LocalTime timeOwlSleep = LocalTime.of(23, 0);
        LocalTime timeOwlAwake = LocalTime.of(9, 0);

        LocalTime timeLarkSleep = LocalTime.of(22, 0);
        LocalTime timeLarkAwake = LocalTime.of(7, 0);

        if (session.getStartSession().toLocalTime().isAfter(timeOwlSleep) &&
                session.getEndSession().toLocalTime().isAfter(timeOwlAwake))
            return Chronotype.OWL;
        else if (session.getStartSession().toLocalTime().isBefore(timeLarkSleep) && session.getEndSession().toLocalTime().isBefore(timeLarkAwake))
            return Chronotype.LARK;
        else return Chronotype.PIGEON;
    }
}