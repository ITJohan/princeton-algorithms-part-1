import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
  private int n;
  private int openSites;
  private int[][] grid;               // 0 closed, 1 open
  private WeightedQuickUnionUF wquf;

  // create n-by-n grid, with all sites blocked
  public Percolation(int n) {
    if (n <= 0) {
      throw new IllegalArgumentException("n must be > 0");
    }

    this.n = n;
    this.grid = new int[n][n];
    this.wquf = new WeightedQuickUnionUF(n * n);

    // Set all sites to closed
    this.openSites = 0;
    for (int i = 0; i < this.grid.length; i++) {
      for (int j = 0; j < this.grid[i].length; j++) {
        this.grid[i][j] = 0;
      }
    }
  }

  // open site (row, col) if it is not open already
  public void open(int row, int col) {
    if (row > this.n || row < 1 || col > this.n || col < 1) {
      throw new IllegalArgumentException("Row and col must be >= 1 and <= n");
    }
    
    if (this.grid[row - 1][col - 1] == 0) {
      // Open site
      this.grid[row - 1][col - 1] = 1;
      this.openSites++;

      // Connect to neighbours
      int p = (row - 1) * this.n + (col - 1);
      if (row - 1 > 0 && this.grid[row - 2][col - 1] == 0) {  // Connect to top if it's open
        int q = (row - 2) * this.n + (col - 1);
        this.wquf.union(p, q);
      }
      if (col - 1 < n - 1 && this.grid[row - 1][col] == 0) {  // Connect to right if it's open
        int q = (row - 1) * this.n + col;
        this.wquf.union(p, q);
      }
      if (row - 1 < n - 1 && this.grid[row][col - 1] == 0) {  // Connect to bottom if it's open
        int q = row * this.n + (col - 1);
        this.wquf.union(p, q);
      }
      if (col - 1 > 0 && this.grid[row - 1][col - 2] == 0) {  // Connect to left if it's open
        int q = (row - 1) * this.n + (col - 2);
        this.wquf.union(p, q);
      }
    }
  }

  // is site (row, col) open?
  public boolean isOpen(int row, int col) {
    if (row > this.n || row < 1 || col > this.n || col < 1) {
      throw new IllegalArgumentException("Row and col must be >= 1 and <= n");
    }
    
    return this.grid[row - 1][col - 1] == 1 ? true : false;
  }

  // is site (row, col) full?
  public boolean isFull(int row, int col) { // EJ KLAR
    if (row > this.n || row < 1 || col > this.n || col < 1) {
      throw new IllegalArgumentException("Row and col must be >= 1 and <= n");
    }

    return false;
  }

  // number of open sites
  public int numberOfOpenSites() {
    return this.openSites;
  }

  // does the system percolate?
  public boolean percolates() { // EJ KLAR
    return false;
  }

  public static void main(String[] args) {
    Percolation percolation = new Percolation(5);
    System.out.println("Open sites: " + percolation.numberOfOpenSites());
    System.out.println(percolation.isOpen(5, 5));
    percolation.open(5, 5);
    System.out.println(percolation.isOpen(5, 5));
    System.out.println("Open sites: " + percolation.numberOfOpenSites());
  }
}
