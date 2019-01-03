package com.bddinaction.services

import com.bddinaction.chapter2.model.Line
import com.bddinaction.chapter2.services.InMemoryTimetableService
import com.beust.jcommander.internal.Lists
import com.google.common.collect.ImmutableList
import org.joda.time.LocalTime
import spock.lang.Specification

class WhenFindingArrivalTimeAtStations extends Specification {

    def timetableService = new InMemoryTimetableService()

    def "should find the arrival times of a given line at a particular station"() {
        given:
            Line line = Line.named(lineName).departingFrom(lineDeparture)

        when:
            def arrivalTimes = timetableService.findArrivalTimes(line, station)

        then:
            arrivalTimes == expectedArrivalTimes
        where:
            lineName  | lineDeparture   | station       | expectedArrivalTimes
            "Epping"  | "Central"       | "Strathfield" | getExpectedArrivalTimes(5)
            "Epping"  | "Epping"        | "Strathfield" | getExpectedArrivalTimes(5)
            "Western" | "Emu Plains"    | "Town Hall"   | getExpectedArrivalTimes(10)
            "Western" | "North Richmond"| "Emu Plains"  | getExpectedArrivalTimes(15)

    }

    def at(int hour, int minute) {
        new LocalTime(hour, minute)
    }

    def getExpectedArrivalTimes(int minutes) {
        List<LocalTime> arrivalTimes = Lists.newArrayList()

        List<LocalTime> universalDepartureTimes = ImmutableList.of(
                new LocalTime(7,53), new LocalTime(7,55), new LocalTime(7,57),
                new LocalTime(8,6), new LocalTime(8,9), new LocalTime(8,16))

        for (LocalTime departureTime : universalDepartureTimes) {
            arrivalTimes.add(departureTime.plusMinutes(minutes))
        }
        return arrivalTimes
    }
}
