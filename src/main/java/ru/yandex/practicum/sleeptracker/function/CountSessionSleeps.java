package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.SleepAnalizator;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;

public class CountSessionSleeps implements SleepAnalizator {
    private final String description = "Кол-во загруженных сессий сна";

    @Override
    public SleepAnalysisResult analyze(List<SleepingSession> sleepingSessions) {
        return new SleepAnalysisResult(description, sleepingSessions.size());
    }
}