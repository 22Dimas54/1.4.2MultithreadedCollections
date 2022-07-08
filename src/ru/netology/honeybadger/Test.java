package ru.netology.honeybadger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Test {
    private final static Integer COUNT = 1_000_000;
    List<Integer> list = creationAndFilling();

    public static List<Integer> creationAndFilling() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < COUNT; i++) {
            list.add(i);
        }
        return list;
    }

    public void writeInMapFromList(Map<Integer, Integer> map) {
        long start = System.currentTimeMillis();
        int value = 0;
        for (int i = 0; i < list.size(); i++) {
            value = list.get(i);
            map.put(i, value);
        }
        long finish = System.currentTimeMillis();
        long elapsed = finish - start;
        System.out.println("Поток-запись " + Thread.currentThread().getName() + " класса" + map.getClass() + " отработал, мс: " + elapsed);
    }

    public void readMap(Map<Integer, Integer> map) {
        long start = System.currentTimeMillis();
        int value = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer key = entry.getKey();
            value = entry.getValue();
        }
        long finish = System.currentTimeMillis();
        long elapsed = finish - start;
        System.out.println("Поток-чтение " + Thread.currentThread().getName() + " класса" + map.getClass() + " отработал, мс: " + elapsed);
    }
}
