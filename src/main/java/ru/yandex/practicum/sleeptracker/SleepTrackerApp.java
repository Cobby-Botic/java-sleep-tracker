package ru.yandex.practicum.sleeptracker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SleepTrackerApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        final LogsLoader logsLoader = new LogsLoader();
        List<SleepingSession> sleepingSessions;

        System.out.println("Введите путь до файла Log.txt:");
        String pathFile = "src/main/resources/sleep_lg.txt";
        try {
            sleepingSessions = logsLoader.downloadSession(pathFile);
            List<SleepAnalizator> analyzers = new ArrayList<>();
            analyzers.add(new CountSessionSleeps());
            analyzers.forEach((analyzer) -> System.out.println(analyzer.analyze(sleepingSessions)));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}