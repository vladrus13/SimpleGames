package ru.vladrus13.RPG.core.utils.ways;

/**
 * @author vladkuznetsov
 * Point class for game. Can be position of point, size of any, etc.
 */
public class Point implements Cloneable {
    /**
     * Position, size, ect. of x-axis
     */
    private int x;

    /**
     * Position, size, ect. of y-axis
     */
    private int y;

    /**
     * Constructor for class
     *
     * @param x x
     * @param y y
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter for x
     *
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * Getter for y
     *
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * Increasing X
     *
     * @param a how much we add
     */
    public void incX(int a) {
        x += a;
    }

    /**
     * Increasing Y
     *
     * @param a how much we add
     */
    public void incY(int a) {
        y += a;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x &&
                y == point.y;
    }

    /**
     * Manhattan distance from this point and given point
     *
     * @param a another point
     * @return distance
     */
    public int distance(Point a) {
        return Math.abs(a.x - this.x) + Math.abs(a.y - this.y);
    }

    /**
     * Get a neighboring point of this
     *
     * @param direction direction from this
     * @return new point
     */
    public Point makePoint(Direction direction) {
        Point returned = new Point(this.x, this.y);
        switch (direction) {
            case UP:
                returned.incY(-1);
                break;
            case DOWN:
                returned.incY(1);
                break;
            case LEFT:
                returned.incX(-1);
                break;
            case RIGHT:
                returned.incX(1);
                break;
        }
        return returned;
    }

    @Override
    public Point clone() {
        Point object;
        try {
            object = (Point) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
        object.x = this.x;
        object.y = this.y;
        return object;
    }
}
