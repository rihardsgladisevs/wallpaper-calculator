package com.rgladisevs.wallpaper.calculator;

import com.rgladisevs.wallpaper.calculator.room.Room;
import com.rgladisevs.wallpaper.calculator.room.RoomFileLoader;

import java.text.MessageFormat;
import java.util.List;

public class Application {

    private static final String INPUT_FILE_PATH = "input1.txt";
    private static final String ROOM_DIMENSION_DELIMITER = "x";

    public static void main(String[] args) {
        RoomFileLoader roomFileLoader = new RoomFileLoader(INPUT_FILE_PATH, ROOM_DIMENSION_DELIMITER);
        List<Room> rooms = roomFileLoader.load();
        WallpaperCalculator wallpaperCalculator = new WallpaperCalculator(rooms);
        Long result = wallpaperCalculator.calculate();
        String resultMessage = MessageFormat.format("In total company need {0,number,#} square feet of wallpaper", result);
        System.out.println(resultMessage);
    }
}
