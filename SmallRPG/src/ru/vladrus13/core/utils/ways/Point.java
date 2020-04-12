package ru.vladrus13.core.utils.ways;

public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void incX(int a) {
        x += a;
    }

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

    public int distance(Point a) {
        return Math.abs(a.x - this.x) + Math.abs(a.y - this.y);
    }

    public Point makePoint(Direction direction) {
        Point returned = new Point(this.x, this.y);
        switch (direction) {
            case UP: returned.incY(-1); break;
            case DOWN: returned.incY(1); break;
            case LEFT: returned.incX(-1); break;
            case RIGHT: returned.incX(1); break;
        }
        return returned;
    }
}
