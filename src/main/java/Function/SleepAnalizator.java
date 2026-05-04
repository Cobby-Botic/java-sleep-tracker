package Function;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;

@FunctionalInterface
public interface SleepAnalizator {
    public SleepAnalysisResult analyze(List<SleepingSession> sleepingSessions);

}
