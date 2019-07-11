import java.util.ArrayList;
import java.util.List;

public class FastCollinearPoints {
  private List<LineSegment> segments;

  public FastCollinearPoints(Point[] points) {
    if (points == null) {
      throw new IllegalArgumentException("points can't be null");
    }
    for (int i = 0; i < points.length; i++) {
      Point point = points[i];
      if (point == null) {
        throw new IllegalArgumentException("point can't be null");
      }
      for (int j = i + 1; j < points.length; j++) {
        if (point.compareTo(points[j]) == 0) {
          throw new IllegalArgumentException("can't have duplicate points");
        }
      }
    }

    this.segments = new ArrayList<>();

    for (int i = 0; i < points.length; i++) {
      mergeSort(points[i], points);
    }
  }

  private Point[] mergeSort(Point point, Point[] points) {
    return mergeSplit(point, points, 0, points.length - 1);

  }
  
  private Point[] mergeSplit(Point point, Point[] points, int min, int max) {
    if (max - min == 0) {
      Point[] singlePoint = new Point[1];
      singlePoint[0] = points[min];
      return singlePoint;
    }

    Point[] lower = mergeSplit(point, points, min, max / 2);
    Point[] upper = mergeSplit(point, points, max / 2 + 1, max);
    return merge(point, lower, upper);
  }

  private Point[] merge(Point point, Point[] lower, Point[] upper) {
    int size = lower.length + upper.length;
    Point[] sorted = new Point[size];
    int n = 0, i = 0, j = 0;
    while (n < size) {
      if (point.slopeOrder().compare(lower[i], upper[j]) < 0) {
        sorted[n] = lower[i];
        n++;
        i++;
        if (i == lower.length) {
          while (n < size) {
            sorted[n] = upper[j];
            n++;
            j++;
          }
        }
      } else {
        sorted[n] = upper[j];
        n++;
        j++;
        if (j == upper.length) {
          while (n < size) {
            sorted[n] = upper[i];
            n++;
            i++;
          }
        }
      }
    }

    return sorted;
  }
	
	public int numberOfSegments() {
		return this.segments.size();
	}

	public LineSegment[] segments() {
		LineSegment[] segmentArray = new LineSegment[this.segments.size()];
		this.segments.toArray(segmentArray);
		return segmentArray;
	}
}