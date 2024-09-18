package ru.job4j.algo.window;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Main {

    public static int[] findMaxOverlapInterval(List<Interval> intervals) {
        var result = new int[]{-1, -1};
        if (intervals.size() == 1) {
            result[0] = intervals.getFirst().getStart();
            result[1] = intervals.getFirst().getEnd();
        } else if (!intervals.isEmpty()) {
            List<Event> events = intervals.stream()
                    .map(i -> List.of(
                            new Event(i.getStart(), true),
                            new Event(i.getEnd(), false)))
                    .flatMap(Collection::stream)
                    .sorted()
                    .toList();
            events.forEach(System.out::println);
            int endTime = events.getLast().getTime();
            int startTime = events.getFirst().getTime();
            int winSize = 1;
            int maxWinSize = endTime - startTime;
            int resultOverlaps = -1;
            while (winSize <= maxWinSize) {
                int winStart = startTime;
                int winEnd = winStart + winSize;
                while (winEnd <= endTime) {
                    var overlaps = countOverlapsForInterval(winStart, winEnd, events);
                    if (overlaps > resultOverlaps) {
                        result[0] = winStart;
                        result[1] = winEnd;
                        resultOverlaps = overlaps;
                    }
                    winStart++;
                    winEnd++;
                }
                winSize++;
            }
        }
        return result;
    }

    private static int countOverlapsForInterval(int start, int end, List<Event> events) {
        int counter = 0;
        int overlaps = 0;
        for (Event event : events) {
            if (event.isStart) {
                counter++;
            } else {
                counter--;
            }
            if (event.getTime() == start) {
                overlaps = counter;
            } else if (event.getTime() > start && event.getTime() < end) {
                if (counter > overlaps) {
                    overlaps = counter;
                }
            } else if (event.getTime() >= end) {
                break;
            }
        }
        return overlaps;
    }

    public static void main(String[] args) {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 4));
        intervals.add(new Interval(2, 6));
        intervals.add(new Interval(3, 5));
        intervals.add(new Interval(7, 8));

        int[] result = findMaxOverlapInterval(intervals);

        System.out.println("Interval that overlaps the maximum number of intervals: [" + result[0] + ", " + result[1] + "]");

        intervals = new ArrayList<>();
        intervals.add(new Interval(1, 3));
        intervals.add(new Interval(2, 4));
        intervals.add(new Interval(3, 5));
        intervals.add(new Interval(4, 6));

        result = findMaxOverlapInterval(intervals);

        System.out.println("Interval that overlaps the maximum number of intervals: [" + result[0] + ", " + result[1] + "]");
    }
}
