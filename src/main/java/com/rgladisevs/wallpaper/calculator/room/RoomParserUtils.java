package com.rgladisevs.wallpaper.calculator.room;

import java.text.MessageFormat;

public class RoomParserUtils {

    private static final int LENGTH_COLUMN_INDEX = 0;
    private static final int WIDTH_COLUMN_INDEX = 1;
    private static final int HEIGHT_COLUMN_INDEX = 2;

    private static final String POSITIVE_INTEGER_NUMBER_EXCLUDING_ZERO = "^[1-9]\\d*$";

    private static final String LENGTH_DIMENSION_NAME = "length";
    private static final String WIDTH_DIMENSION_NAME = "width";
    private static final String HEIGHT_DIMENSION_NAME = "height";

    private RoomParserUtils() {
    }

    public static Room parseFromString(String string, String delimiter) {
        String[] lineStringArray = string.split(delimiter);
        checkIfAllArgsPassed(lineStringArray);
        String possibleLength = lineStringArray[LENGTH_COLUMN_INDEX];
        String possibleWidth = lineStringArray[WIDTH_COLUMN_INDEX];
        String possibleHeight = lineStringArray[HEIGHT_COLUMN_INDEX];
        checkIfAllArgsArePositiveIntegerNumbers(possibleLength, possibleWidth, possibleHeight);
        Long length = Long.valueOf(possibleLength);
        Long width = Long.valueOf(possibleWidth);
        Long height = Long.valueOf(possibleHeight);
        return new Room(length, width, height);
    }

    private static void checkIfAllArgsPassed(String[] lineStringArray) {
        if (lineStringArray.length != 3) {
            String message = MessageFormat.format("Can''t parse room from array [{0}], expected 3 arguments, got {1}",
                    String.join(", ", lineStringArray),
                    lineStringArray.length);
            throw new RoomParserException(message);
        }
    }

    private static void checkIfAllArgsArePositiveIntegerNumbers(String possibleLength, String possibleWidth, String possibleHeight) {
        checkIfDimensionIsPositiveIntegerNumber(possibleLength, LENGTH_DIMENSION_NAME);
        checkIfDimensionIsPositiveIntegerNumber(possibleWidth, WIDTH_DIMENSION_NAME);
        checkIfDimensionIsPositiveIntegerNumber(possibleHeight, HEIGHT_DIMENSION_NAME);
    }

    private static void checkIfDimensionIsPositiveIntegerNumber(String dimension, String dimensionName) {
        if (!dimension.matches(POSITIVE_INTEGER_NUMBER_EXCLUDING_ZERO)) {
            String message = MessageFormat.format("Can''t parse rooms {0} of {1}, should be positive integer number",
                    dimensionName,
                    dimension);
            throw new RoomParserException(message);
        }
    }
}
