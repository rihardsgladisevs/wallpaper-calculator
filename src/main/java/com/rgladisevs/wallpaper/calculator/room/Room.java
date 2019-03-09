package com.rgladisevs.wallpaper.calculator.room;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public class Room {
    private Long length;
    private Long width;
    private Long height;

    public Long getSurfaceAreaWithExtraOfSmallestSide() {
        return getSmallestSideArea() + getSurfaceArea();
    }

    private Long getSurfaceArea() {
        long allHorizontalSurfaceArea = 2 * getHorizontalSurfaceArea();
        long allVerticalXSurfaceArea = 2 * getVerticalXSurfaceArea();
        long allVerticalYSurfaceArea = 2 * getVerticalYSurfaceArea();
        return allHorizontalSurfaceArea + allVerticalXSurfaceArea + allVerticalYSurfaceArea;
    }

    private Long getHorizontalSurfaceArea() {
        return length * width;
    }

    private Long getVerticalXSurfaceArea() {
        return width * height;
    }

    private Long getVerticalYSurfaceArea() {
        return length * height;
    }

    private Long getSmallestSideArea() {
        return Stream.of(getHorizontalSurfaceArea(), getVerticalXSurfaceArea(), getVerticalYSurfaceArea())
                .min(Long::compare)
                .orElse(0L);
    }
}
