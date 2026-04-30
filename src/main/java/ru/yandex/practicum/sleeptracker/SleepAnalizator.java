package ru.yandex.practicum.sleeptracker;

import java.util.List;

@FunctionalInterface
public interface SleepAnalizator {
    public SleepAnalysisResult analyze(List<SleepingSession> sleepingSessions);

}
