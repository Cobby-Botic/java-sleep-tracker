package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.SleepAnalizator;
import ru.yandex.practicum.sleeptracker.SleepStatus;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;

public class CountBadStatusSessions implements SleepAnalizator {
    private final String description = "Количество сессий с плохим сном";

    @Override
    public SleepAnalysisResult analyze(List<SleepingSession> sleepingSessions) {
        List<SleepingSession> badSession = sleepingSessions.stream()
                .filter(s -> s.getSleepStatus().equals(SleepStatus.BAD))
                .toList();
        return new SleepAnalysisResult(description, badSession.size());
    }
}
