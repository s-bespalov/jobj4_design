package ru.job4j.algo.finaltask;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class BankMaxLoadTime {

    public static int[] findMaxLoadTime(List<int[]> visitTimes) {
        int maxLoadStartTime = 0;
        int maxLoadEndTime = 0;
        var events = visitTimes.stream()
                .flatMap(times -> Stream.of(
                        new Event(times[0], EventType.ARRIVAL),
                        new Event(times[1], EventType.DEPARTURE)))
                .sorted()
                .toList();
        var counter = 0;
        var time = 0;
        var max = 0;
        var maxStarted = false;
        for (var event : events) {
            if (maxStarted) {
                maxLoadEndTime = time;
            }
            if (event.time > time) {
                if (counter > max) {
                    max = counter;
                    maxLoadStartTime = time;
                    maxStarted = true;
                } else if (maxStarted && counter < max) {
                    maxStarted = false;
                }
                if (maxStarted) {
                    maxLoadEndTime = time;
                }
                time = event.time;
            }
            counter += Objects.equals(event.type, EventType.ARRIVAL) ? 1 : -1;
        }
        return new int[]{maxLoadStartTime, maxLoadEndTime};
    }

    static class Event implements Comparable<Event> {
        int time;
        EventType type;

        Event(int time, EventType type) {
            this.time = time;
            this.type = type;
        }

        @Override
        public int compareTo(Event other) {
            if (this.time == other.time) {
                return this.type == EventType.ARRIVAL ? -1 : 1;
            }
            return Integer.compare(this.time, other.time);
        }
    }

    enum EventType {
        ARRIVAL, DEPARTURE
    }
}