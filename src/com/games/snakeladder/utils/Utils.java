package utils;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import elements.SnakeLadderGameElement;

public class Utils {

    public static Point generatePoint(int startX, int startY, int maxX, int maxY) {
        return new Point(
                ThreadLocalRandom.current().nextInt(startX, maxX),
                ThreadLocalRandom.current().nextInt(startY, maxY)
        );
    }

    public static List<SnakeLadderGameElement> deepClone(List<SnakeLadderGameElement> elements) {
        if(elements == null) return null;
        return elements.stream()
                    .map(t -> t.clone())
                    .collect(Collectors.toCollection(ArrayList::new));
    } 
}
