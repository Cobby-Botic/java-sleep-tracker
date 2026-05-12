package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.function.SleepAnalysisResult;

import java.util.List;

@FunctionalInterface
public interface SleepAnalizator {
    public SleepAnalysisResult analyze(List<SleepingSession> sleepingSessions);

}
