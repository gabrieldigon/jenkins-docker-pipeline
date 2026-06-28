package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnitConverterTest {

    @Test
    void shouldConvertKilometersToMiles() {
        UnitConverter converter = new UnitConverter();

        assertEquals(0.621371, converter.kilometersToMiles(1), 0.000001);
    }

    @Test
    void shouldConvertInchesToCentimeters() {
        UnitConverter converter = new UnitConverter();

        assertEquals(25.4, converter.inchesToCentimeters(10), 0.01);
    }
}