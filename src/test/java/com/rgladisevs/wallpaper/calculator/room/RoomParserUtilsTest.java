package com.rgladisevs.wallpaper.calculator.room;

import com.googlecode.zohhak.api.TestWith;
import com.googlecode.zohhak.api.runners.ZohhakRunner;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(ZohhakRunner.class)
public class RoomParserUtilsTest {

    @TestWith({
            "1x2x3, x, 1, 2, 3",
            "3x4x5, x, 3, 4, 5",
            "1;2;3, ;, 1, 2, 3",
            "1&2&3, &, 1, 2, 3"
    })
    public void parseFromString(String string,
                                String delimiter,
                                Long expectedLength,
                                Long expectedWidth,
                                Long expectedHeight) {
        Room room = RoomParserUtils.parseFromString(string, delimiter);
        assertEquals(expectedLength, room.getLength());
        assertEquals(expectedWidth, room.getWidth());
        assertEquals(expectedHeight, room.getHeight());
    }

    @TestWith({
            "1x2;3, x",
            "12x3, x",
            "1ax2x3, x",
            "-1x2x3, x"
    })
    public void parseFromStringWithException(String string,
                                             String delimiter) {
        boolean exceptionThrown = false;
        try {
            RoomParserUtils.parseFromString(string, delimiter);
        } catch (RoomParserException e) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }
}