package com.rgladisevs.wallpaper.calculator.room;

import com.googlecode.zohhak.api.TestWith;
import com.googlecode.zohhak.api.runners.ZohhakRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(ZohhakRunner.class)
public class RoomTest {

    private static final Long DEFAULT_LENGTH = 12L;
    private static final Long DEFAULT_WIDTH = 45L;
    private static final Long DEFAULT_HEIGHT = 4L;

    @TestWith({
            "1, 2, 3, 24",
            "1, 1, 5, 23"
    })
    public void getSurfaceAreaWithExtraOfSmallestSide(Long length, Long width, Long height, Long expectedResult) {
        Room room = new Room(length, width, height);
        Long result = room.getSurfaceAreaWithExtraOfSmallestSide();
        assertEquals(expectedResult, result);
    }

    @Test
    public void getLength() {
        Room room = new Room(DEFAULT_LENGTH, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        Long length = room.getLength();
        assertEquals(DEFAULT_LENGTH, length);
    }

    @Test
    public void getWidth() {
        Room room = new Room(DEFAULT_LENGTH, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        Long width = room.getWidth();
        assertEquals(DEFAULT_WIDTH, width);
    }

    @Test
    public void getHeight() {
        Room room = new Room(DEFAULT_LENGTH, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        Long height = room.getHeight();
        assertEquals(DEFAULT_HEIGHT, height);
    }
}