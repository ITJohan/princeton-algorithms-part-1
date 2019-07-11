/******************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *  Dependencies: none
 *  
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    /**
     * Initializes a new point.
     *
     * @param  x the <em>x</em>-coordinate of the point
     * @param  y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param  that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        if (this.x == that.x && this.y == that.y) {
            // Same point?
            return Double.NEGATIVE_INFINITY;
        } else if (this.x == that.x) {
            // Vertical?
            return Double.POSITIVE_INFINITY;
        } else if (this.y == that.y) {
            // Horizontal?
            return 0.0;
        }

        return (double) (that.y - this.y) / (that.x - this.x);
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param  that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     *         point (x0 = x1 and y0 = y1);
     *         a negative integer if this point is less than the argument
     *         point; and a positive integer if this point is greater than the
     *         argument point
     */
    public int compareTo(Point that) {
        if (this.x == that.x && this.y == that.y) {
            // Same point?
            return 0;
        } else if (this.y < that.y || (this.y == that.y && this.x < that.x)) {
            // This point less?
            return -1;
        }
        // This point greater?
        return 1;
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        return (Point p1, Point p2) -> (int) (p1.slopeTo(this) - p2.slopeTo(this));
    }


    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        int n = 5;
        int size = 5;

        StdDraw.setScale(0.0, (double) size);

        StdDraw.setPenRadius(0.001);
        StdDraw.setPenColor(StdDraw.BLACK);

        for (int i = 1; i < size; i++) {
            LineSegment yLine = new LineSegment(new Point(i, 0), new Point(i, size));
            yLine.draw();
            for (int j = 1; j < size; j++) {
                LineSegment xLine = new LineSegment(new Point(0, j), new Point(size, j));
                xLine.draw();
            }
        }

        StdDraw.setPenRadius(0.02);
        StdDraw.setPenColor(StdDraw.BLUE);

        List<Integer> xValues = new ArrayList<>();
        List<Integer> yValues = new ArrayList<>();

        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = (int) (Math.random() * size);
            int y = (int) (Math.random() * size);
            while (xValues.contains(x) && yValues.contains(y)) {
                x = (int) (Math.random() * size);
                y = (int) (Math.random() * size);
            }
            xValues.add(x);
            yValues.add(y);
            points[i] = new Point(x, y);
            points[i].draw();
        }

        BruteCollinearPoints bcp = new BruteCollinearPoints(points);
        FastCollinearPoints fcp = new FastCollinearPoints(points);

        // StdDraw.setPenRadius(0.005);
        // StdDraw.setPenColor(StdDraw.RED);
        // for (LineSegment segment : bcp.segments()) {
        //     segment.draw();
        // }

        // System.out.println(bcp.numberOfSegments());
    }
}
