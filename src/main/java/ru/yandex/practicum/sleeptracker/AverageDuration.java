package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.util.List;

public class AverageDuration implements SleepAnalizator {

    @Override
    public SleepAnalysisResult analyze(List<SleepingSession> sleepingSessions) {

        long avgMinutes = (long) sleepingSessions.stream()
                .mapToLong(s -> s.getPeriod().toMinutes())
                .average()
                .orElse(0);

        Duration averageDuration = Duration.ofMinutes(avgMinutes);

        String result = averageDuration.toHours() + "ч " +
                averageDuration.toMinutesPart() + "м";

        return new SleepAnalysisResult("Средняя продолжительность сессии", result);
    }
}
