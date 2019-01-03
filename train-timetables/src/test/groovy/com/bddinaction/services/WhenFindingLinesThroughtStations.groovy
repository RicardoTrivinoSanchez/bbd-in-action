package com.bddinaction.services

import com.bddinaction.chapter2.model.Line
import com.bddinaction.chapter2.services.InMemoryTimetableService
import spock.lang.Specification

class WhenFindingLinesThroughtStations extends Specification {

    def timetableService = new InMemoryTimetableService()

    def "should find the correct lines between two stations"() {
        when:
            def lines = timetableService.findLinesThrough(departure, destination)

        then:
            def expectedLine = Line.named(lineName).departingFrom(lineDeparture)
            lines == [expectedLine]
        where:
            departure     | destination   | lineName  | lineDeparture
            "Parramatta"  | "Town Hall"   | "Western" | "Emu Plains"
            "Town Hall"   | "Parramatta"  | "Western" | "North Richmond"
            "Strathfield" | "Epping"      | "Epping"  | "Central"
    }
}
