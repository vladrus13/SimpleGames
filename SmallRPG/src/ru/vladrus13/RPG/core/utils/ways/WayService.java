package ru.vladrus13.RPG.core.utils.ways;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Static class for ways
 */
public class WayService {
    /**
     * Find all points on distance of x
     *
     * @param x        point
     * @param distance distance
     * @return list of points
     */
    public static ArrayList<Point> findPointInDistance(Point x, double distance) {
        Set<Point> onUse = new HashSet<>(Collections.singleton(x));
        Set<Point> answer = new HashSet<>(Collections.singleton(x));
        while (!onUse.isEmpty()) {
            Point current = onUse.iterator().next();
            onUse.remove(onUse.iterator().next());
            for (Direction direction : Direction.values()) {
                Point future = current.makePoint(direction);
                if (future.getY() >= 0 && future.getX() >= 0 && !answer.contains(future) && !onUse.contains(future) && x.distance(future) <= distance) {
                    onUse.add(future);
                    answer.add(future);
                }
            }
        }
        return new ArrayList<>(answer.stream().collect(Collectors.toUnmodifiableList()));
    }
}
