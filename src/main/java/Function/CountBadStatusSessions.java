package Function;

import ru.yandex.practicum.sleeptracker.SleepStatus;
import ru.yandex.practicum.sleeptracker.SleepingSession;

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
