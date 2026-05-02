package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.time.LocalDateTime;

public class SleepingSession {
    private LocalDateTime startSession;
    private LocalDateTime endSession;
    private SleepStatus sleepStatus;
    private Duration period;

    public SleepingSession(LocalDateTime startSession, LocalDateTime endSession, SleepStatus sleepStatus) {
        this.startSession = startSession;
        this.endSession = endSession;
        this.sleepStatus = sleepStatus;
        this.period = Duration.between(startSession, endSession);
    }

    public LocalDateTime getStartSession() {
        return startSession;
    }

    public LocalDateTime getEndSession() {
        return endSession;
    }

    public SleepStatus getSleepStatus() {
        return sleepStatus;
    }

    public Duration getPeriod() {
        return period;
    }
}
