package ru.yandex.practicum.sleeptracker;

import java.time.LocalDateTime;

public class SleepingSession {
    private LocalDateTime startSession;
    private LocalDateTime endSession;
    private SleepStatus sleepStatus;

    public SleepingSession(LocalDateTime startSession, LocalDateTime endSession, SleepStatus sleepStatus) {
        this.startSession = startSession;
        this.endSession = endSession;
        this.sleepStatus = sleepStatus;
    }
}
