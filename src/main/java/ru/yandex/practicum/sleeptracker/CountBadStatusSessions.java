package ru.yandex.practicum.sleeptracker;

import java.util.List;

public class CountBadStatusSessions implements SleepAnalizator {

    @Override
    public SleepAnalysisResult analyze(List<SleepingSession> sleepingSessions) {
        List<SleepingSession> badSession = sleepingSessions.stream()
                .filter(s -> s.getSleepStatus().equals(SleepStatus.BAD))
                .toList();
        return new SleepAnalysisResult("Количество сессий с плохим сном", badSession.size());
    }
}
