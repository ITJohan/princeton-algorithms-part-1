import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
  private Percolation percolation;
  public PercolationStats(int n, int trials) {  // perform trials independent experiments on an n-by-n grid
    if (n <= 0 || trials <= 0) {
      throw new IllegalArgumentException("n and trails must be > 0");
    }
    
    percolation = new Percolation(n);
    for (int i = 0; i < trials; i++) {

    }
  }
    
  // public double mean()                          // sample mean of percolation threshold
  // public double stddev()                        // sample standard deviation of percolation threshold
  // public double confidenceLo()                  // low  endpoint of 95% confidence interval
  // public double confidenceHi()                  // high endpoint of 95% confidence interval

  // public static void main(String[] args)        // test client (described below)
}