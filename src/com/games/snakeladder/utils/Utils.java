package utils;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import elements.SnakeLadderGameElement;

public class Utils {

    public static Point generatePoint(double maxX, double maxY) {
        return generatePoint(0, 0, maxX, maxY);
    }

    public static Point generatePoint(double startX, double startY, double maxX, double maxY) {
        return new Point(
                (int) (startX + Math.random() * (maxX - startX)),
                (int) (startY + Math.random() * (maxY - startY))
        );
    }

    public static List<SnakeLadderGameElement> deepClone(List<SnakeLadderGameElement> elements) {
        if(elements == null) return null;
        return elements.stream()
                    .map(t -> t.clone())
                    .collect(Collectors.toCollection(ArrayList::new));
    } 
}
