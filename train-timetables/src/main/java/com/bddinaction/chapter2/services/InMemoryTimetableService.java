package com.bddinaction.chapter2.services;

import com.bddinaction.chapter2.model.Line;
import com.beust.jcommander.internal.Lists;
import com.google.common.collect.ImmutableList;
import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.List;

public class InMemoryTimetableService implements TimetableService {

    private List<Line> lines = ImmutableList.of(
            Line.named("Western").departingFrom("Emu Plains")
                    .withStations("Emu Plains","Parramatta","Town Hall", "North Richmond"),
            Line.named("Western").departingFrom("North Richmond")
                    .withStations("North Richmond","Town Hall","Parramatta","Emu Plains"),
            Line.named("Epping").departingFrom("Epping")
                    .withStations("Epping","Strathfield","Central"),
            Line.named("Epping").departingFrom("Central")
                    .withStations("Central", "Strathfield", "Epping")
    );

    // All trains leave the depots at the same time.
    private List<LocalTime> universalDepartureTimes = ImmutableList.of(
            new LocalTime(7,53), new LocalTime(7,55), new LocalTime(7,57),
            new LocalTime(8,6), new LocalTime(8,9), new LocalTime(8,16));

    @Override
    public List<LocalTime> findArrivalTimes(Line line, String targetStation) {
        List<LocalTime> arrivalTimes = Lists.newArrayList();
        Line targetLine = lineMatching(line);
        int timeTaken = 0;
        if (targetLine != null) {

            for (String station : targetLine.getStations()) {
                if (station.equals(targetStation)) {
                    break;
                }
                timeTaken += 5;
            }

            for (LocalTime time : universalDepartureTimes) {
                arrivalTimes.add(time.plusMinutes(timeTaken));
            }
        }
        return arrivalTimes;
    }

    private Line lineMatching(Line requestedLine) {
        for(Line line : lines) {
            if (line.equals(requestedLine)) {
                return line;
            }
        }
        return null;
    }

    @Override
    public List<Line> findLinesThrough(String departure, String destination) {
        List<Line> resultLines = new ArrayList<Line>();
        for (Line line : lines) {
            if (line.getStations().contains(departure) && line.getStations().contains(destination)) {
                if (line.getStations().indexOf(destination) > line.getStations().indexOf(departure)) {
                    resultLines.add(line);
                }
            }
        }
        return resultLines;
    }

    @Override
    public void scheduleArrivalTime(String line, LocalTime departureTime) {
    }

    @Override
    public LocalTime getArrivalTime(String travellingOnLine, String destination) {
        return null;
    }
}
