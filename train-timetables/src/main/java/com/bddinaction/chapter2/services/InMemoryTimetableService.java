package com.bddinaction.chapter2.services;

import com.bddinaction.chapter2.model.Line;
import com.google.common.collect.ImmutableList;
import org.joda.time.LocalTime;

import java.util.List;

public class InMemoryTimetableService implements TimetableService {

    List<Line> lines = ImmutableList.of(
            Line.named("Western").departingFrom("Emu Plains")
                    .withStations("Emu Plains","Parramatta","Town Hall", "North Richmond"),
            Line.named("Western").departingFrom("North Richmond")
                    .withStations("North Richmond","Town Hall","Parramatta","Emu Plains"),
            Line.named("Epping").departingFrom("Epping")
                    .withStations("Epping","Strathfield","Central"),
            Line.named("Epping").departingFrom("City")
                    .withStations("Central", "Strathfield", "Epping")
    );

    // All trains leave the depots at the same time.
    List<LocalTime> universalDepartureTimes = ImmutableList.of(
            new LocalTime(7,53), new LocalTime(7,55), new LocalTime(7,57),
            new LocalTime(8,6), new LocalTime(8,9), new LocalTime(8,16));

    @Override
    public List<LocalTime> findArrivalTimes(Line line, String targetStation) {
        return null;
    }

    @Override
    public List<Line> findLinesThrough(String departure, String destination) {
        return null;
    }

    @Override
    public void scheduleArrivalTime(String line, LocalTime departureTime) {

    }

    @Override
    public LocalTime getArrivalTime(String travellingOnLine, String destination) {
        return null;
    }
}
