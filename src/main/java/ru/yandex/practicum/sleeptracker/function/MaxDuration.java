package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.SleepAnalizator;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;

public class MaxDuration implements SleepAnalizator {
    private final String description = "Максимальная продолжительность сессии";

    @Override
    public SleepAnalysisResult analyze(List<SleepingSession> sleepingSessions) {

        Duration maxDuration = sleepingSessions.stream()
                .max(Comparator.comparing(SleepingSession::getPeriod))
                .map(SleepingSession::getPeriod)
                .orElse(Duration.ZERO);

        String result = maxDuration.toHours() + "ч " + maxDuration.toMinutesPart() + "м";

        return new SleepAnalysisResult(description, result);
    }
}