package com.rgladisevs.wallpaper.calculator.room;

import com.rgladisevs.wallpaper.calculator.FileReadException;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

public class RoomFileLoaderTest {

    @Test
    public void loadWithXDelimiter() {
        RoomFileLoader roomFileLoader = new RoomFileLoader("test_delimiter_x.txt", "x");
        List<Room> rooms = roomFileLoader.load();
        assertThat(rooms, hasSize(3));
        Room room1 = rooms.get(0);
        assertRoom(room1, 1L, 2L, 3L);
        Room room2 = rooms.get(1);
        assertRoom(room2, 4L, 5L, 6L);
        Room room3 = rooms.get(2);
        assertRoom(room3, 7L, 8L, 9L);
    }

    @Test
    public void loadWithSemicolonDelimiter() {
        RoomFileLoader roomFileLoader = new RoomFileLoader("test_delimiter_semicolon.txt", ";");
        List<Room> rooms = roomFileLoader.load();
        assertThat(rooms, hasSize(2));
        Room room1 = rooms.get(0);
        assertRoom(room1, 34L, 44L, 4L);
        Room room2 = rooms.get(1);
        assertRoom(room2, 35L, 45L, 5L);
    }

    @Test(expected = FileReadException.class)
    public void loadNonExistingFile() {
        RoomFileLoader roomFileLoader = new RoomFileLoader("non_existing.txt", "x");
        roomFileLoader.load();
    }

    @Test
    public void loadEmptyFile() {
        RoomFileLoader roomFileLoader = new RoomFileLoader("empty.txt", "x");
        List<Room> rooms = roomFileLoader.load();
        assertThat(rooms, hasSize(0));
    }

    private void assertRoom(Room actual, Long length, Long width, Long height) {
        assertNotNull(actual);
        assertEquals(length, actual.getLength());
        assertEquals(width, actual.getWidth());
        assertEquals(height, actual.getHeight());

    }
}