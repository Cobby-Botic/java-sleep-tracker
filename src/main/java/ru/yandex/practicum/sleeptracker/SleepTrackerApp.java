package ru.yandex.practicum.sleeptracker;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class SleepTrackerApp {
    LogsLoader logsLoader = new LogsLoader();
    String pathFile = "src/main/resources/sleep_log.txt";
    List<SleepingSession> sleepingSessions;
    {
        try {
            sleepingSessions = logsLoader.downloadSession(pathFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите путь до файла Log.txt:");
        /*
        String pathFile = scanner.nextLine();
        SleepTrackerApp app = new SleepTrackerApp();
         */


    }
}