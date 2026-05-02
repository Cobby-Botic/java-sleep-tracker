package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class MinDuration implements SleepAnalizator {

    @Override
    public SleepAnalysisResult analyze(List<SleepingSession> sleepingSessions) {

        Duration maxDuration = sleepingSessions.stream()
                .min(Comparator.comparing(SleepingSession::getPeriod))
                .map(SleepingSession::getPeriod)
                .orElse(Duration.ZERO);

        String result = maxDuration.toHours() + "ч " + maxDuration.toMinutesPart() + "м";

        return new SleepAnalysisResult("Максимальная продолжительность сессии", result);
    }
}
