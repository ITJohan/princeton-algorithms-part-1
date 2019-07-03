import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
  private final double mean, stddev, confidenceLo, confidenceHi;
  private final static double CONFIDENCE_96 = 1.96;

  public PercolationStats(int n, int trials) {  // perform trials independent experiments on an n-by-n grid
    if (n <= 0 || trials <= 0) {
      throw new IllegalArgumentException("n and trails must be > 0");
    }

    Percolation percolation;
    double[] trialsArray = new double[trials];
    
    for (int i = 0; i < trials; i++) {
      percolation = new Percolation(n);
  
      for (int j = 0; j < n * n; j++) {
        int row = StdRandom.uniform(1, n + 1);
        int col = StdRandom.uniform(1, n + 1);
        percolation.open(row, col);
        if (percolation.percolates()) {
          break;
        }
      }

      trialsArray[i] = (double) percolation.numberOfOpenSites() / (n * n);
    }

    this.mean = StdStats.mean(trialsArray);
    this.stddev = StdStats.stddev(trialsArray);
    this.confidenceLo = this.mean - CONFIDENCE_96 * stddev / Math.sqrt(trialsArray.length);
    this.confidenceHi = this.mean + CONFIDENCE_96 * stddev / Math.sqrt(trialsArray.length);
  }
  
  // sample mean of percolation threshold
  public double mean() {
    return this.mean;
  }

  // sample standard deviation of percolation threshold
  public double stddev() {
    return this.stddev;
  }

  // low  endpoint of 95% confidence interval
  public double confidenceLo() {
    return this.confidenceLo;
  }

  // high endpoint of 95% confidence interval
  public double confidenceHi() {
    return this.confidenceHi;
  }

  public static void main(String[] args) {        // test client (described below)
    PercolationStats percolationStats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
    System.out.println("mean                    = " + percolationStats.mean());
    System.out.println("stddev                  = " + percolationStats.stddev());
    System.out.println("95% confidence interval = [" + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi() + "]");
  }
}