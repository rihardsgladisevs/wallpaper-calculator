package com.rgladisevs.wallpaper.calculator;

import com.rgladisevs.wallpaper.calculator.room.Room;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class WallpaperCalculator {

    private List<Room> rooms;

    public Long calculate() {
        return rooms.stream()
                .map(Room::getSurfaceAreaWithExtraOfSmallestSide)
                .mapToLong(Long::longValue)
                .sum();
    }
}
