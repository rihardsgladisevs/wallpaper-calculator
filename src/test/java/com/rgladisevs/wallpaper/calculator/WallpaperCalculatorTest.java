package com.rgladisevs.wallpaper.calculator;

import com.rgladisevs.wallpaper.calculator.room.Room;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WallpaperCalculatorTest {

    @Mock
    private Room room1;

    @Mock
    private Room room2;

    @Mock
    private Room room3;

    private List<Room> rooms;

    @Before
    public void setUp() {
        when(this.room1.getSurfaceAreaWithExtraOfSmallestSide()).thenReturn(22L);
        when(this.room2.getSurfaceAreaWithExtraOfSmallestSide()).thenReturn(44L);
        when(this.room3.getSurfaceAreaWithExtraOfSmallestSide()).thenReturn(11L);
        this.rooms = Arrays.asList(room1, room2, room3);
    }

    @Test
    public void calculate() {
        WallpaperCalculator calculator = new WallpaperCalculator(this.rooms);
        Long actualResult = calculator.calculate();
        Long expectedResult = 77L;
        assertEquals(expectedResult, actualResult);
    }
}