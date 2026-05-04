package Function;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;

public class CountSessionSleeps implements SleepAnalizator {

    @Override
    public SleepAnalysisResult analyze(List<SleepingSession> sleepingSessions) {
        return new SleepAnalysisResult("Кол-во загруженных сессий сна",
                sleepingSessions.size());
    }
}
