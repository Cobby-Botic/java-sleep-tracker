package Function;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;

public class MinDuration implements SleepAnalizator {

    @Override
    public SleepAnalysisResult analyze(List<SleepingSession> sleepingSessions) {

        Duration maxDuration = sleepingSessions.stream()
                .min(Comparator.comparing(SleepingSession::getPeriod))
                .map(SleepingSession::getPeriod)
                .orElse(Duration.ZERO);

        String result = maxDuration.toHours() + "ч " + maxDuration.toMinutesPart() + "м";

        return new SleepAnalysisResult("Минимальная продолжительность сессии", result);
    }
}
