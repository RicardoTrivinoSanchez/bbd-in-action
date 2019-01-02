package com.bddinaction.services

import com.bddinaction.chapter2.model.Line
import com.bddinaction.chapter2.services.ItineraryService
import com.bddinaction.chapter2.services.TimetableService
import org.joda.time.LocalTime
import spock.lang.Specification

class WhenCalculatingArrivalTimes extends Specification {

    TimetableService timetableService = Mock(timetableService)
    ItineraryService itineraryService = new ItineraryService(timetableService)

    def "should calculate the correct arrival time"() {
        given:
            def westernLineFromEmuPlains = Line.named("Western").departingFrom("Emu Plains")
            timetableService.findLinesThrough("Parramatta","Town Hall") >> [westernLineFromEmuPlains]
            timetableService.findArrivalTimes(westernLineFromEmuPlains, "Parramatta") >>
                [at(7,58), at(8,00), at(8,02), at(8,11), at(8,14), at(8,21), at(8,31), at(8,36)]

        when:
            def proposedTrainTimes = itineraryService.findNextDepartures("Parramatta",
                                                                        "Town Hall",
                                                                        at(8,0))
        then:
            proposedTrainTimes == [at(8,02), at(8,11), at(8,14)]
    }

    def at(int hour, int minute) {
        new LocalTime(hour, minute)
    }
}
