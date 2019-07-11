import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {
  private List<LineSegment> segments;

  public BruteCollinearPoints(Point[] points) {
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
      for (int j = i + 1; j < points.length; j++) {
				double ijSlope = points[i].slopeTo(points[j]);
        for (int k = j + 1; k < points.length; k++) {
					double ikSlope = points[i].slopeTo(points[k]);
          for (int l = k + 1; l < points.length; l++) {
						double ilSlope = points[i].slopeTo(points[l]);
						if (ijSlope == ikSlope && ijSlope == ilSlope) {
							Point[] orderedPoints = new Point[4];
							orderedPoints[0] = points[i];
							orderedPoints[1] = points[j];
							orderedPoints[2] = points[k];
							orderedPoints[3] = points[l];
							Arrays.sort(orderedPoints);
              segments.add(new LineSegment(orderedPoints[0], orderedPoints[3]));
            }
          }
        }
      }
    }
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