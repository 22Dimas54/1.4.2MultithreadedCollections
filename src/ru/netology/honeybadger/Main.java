package ru.netology.honeybadger;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/*
В ходе тестов удалось выяснить, что ConcurrentHashMap гораздо производительнее Collections.synchronizedMap
 */
public class Main {
    private static final ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private static final int COUNT = 10;
    private static final Test test = new Test();

    public static void main(String[] args) {
        Map<Integer, Integer> concurrentHashMap = new ConcurrentHashMap();
        Map<Integer, Integer> synchronizedMap = Collections.synchronizedMap(new HashMap<>());
        addTasksInExecutorService(concurrentHashMap, synchronizedMap);
        executorService.shutdown();
    }

    private static void addTasksInExecutorService(Map<Integer, Integer> concurrentHashMap, Map<Integer, Integer> synchronizedMap) {
        for (int i = 0; i < COUNT; i++) {
            executorService.submit(() -> test.writeInMapFromList(concurrentHashMap));
            executorService.submit(() -> test.readMap(concurrentHashMap));
            executorService.submit(() -> test.writeInMapFromList(synchronizedMap));
            executorService.submit(() -> test.readMap(synchronizedMap));
        }
    }
}
