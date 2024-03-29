import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
  private final int n;                      // Size of grid
  private int openSites;              // Number of open sites
  private int[][] grid;               // 0 closed, 1 open
  private final WeightedQuickUnionUF wquf;

  // create n-by-n grid, with all sites blocked
  public Percolation(int n) {
    if (n <= 0) {
      throw new IllegalArgumentException("n must be > 0");
    }

    this.n = n;
    this.grid = new int[n][n];
    this.wquf = new WeightedQuickUnionUF(n * n);

    // Set all sites to closed and assign virtual top and bottom
    this.openSites = 0;
    for (int i = 0; i < this.grid.length; i++) {
      for (int j = 0; j < this.grid[i].length; j++) {
        this.grid[i][j] = 0;

        if ((i == 0 || i == n - 1)) {
          int p = i * n + j;
          if (j < n - 1)
            this.wquf.union(p, p + 1);
        }
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
      int q = n + 1;                                          // Temporary value
      if (row - 1 > 0 && this.grid[row - 2][col - 1] == 1) {  // Connect to top if it's open
        q = (row - 2) * this.n + (col - 1);
        this.wquf.union(p, q);
      }
      if (col - 1 < n - 1 && this.grid[row - 1][col] == 1) {  // Connect to right if it's open
        q = (row - 1) * this.n + col;
        this.wquf.union(p, q);
      }
      if (row - 1 < n - 1 && this.grid[row][col - 1] == 1) {  // Connect to bottom if it's open
        q = row * this.n + (col - 1);
        this.wquf.union(p, q);
      }
      if (col - 1 > 0 && this.grid[row - 1][col - 2] == 1) {  // Connect to left if it's open
        q = (row - 1) * this.n + (col - 2);
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
  public boolean isFull(int row, int col) { 
    if (row > this.n || row < 1 || col > this.n || col < 1) {
      throw new IllegalArgumentException("Row and col must be >= 1 and <= n");
    }

    // Is site closed?
    if (this.grid[row - 1][col - 1] == 0)
      return false;

    int p = (row - 1) * this.n + (col - 1);
    return this.wquf.connected(p, this.wquf.find(0));
  }

  // number of open sites
  public int numberOfOpenSites() {
    return this.openSites;
  }

  // does the system percolate?
  public boolean percolates() {
    int col = 1;
    for (int i = 0; i < this.n; i++) {
      if (this.grid[this.n - 1][i] == 1) {
        col = i + 1;
        break;
      }
    }
    return isFull(this.n, col);
  }

  public void printGrid() {
    System.out.println("Wquf: ");
    for (int i = 0; i < this.grid.length; i++) {
      for (int j = 0; j < this.grid[i].length; j++) {
        System.out.print(this.wquf.find((i * this.n) + j) + " ");
      }
      System.out.println("");
    }

    System.out.println("");
    System.out.println("Grid: ");
    for (int i = 0; i < this.grid.length; i++) {
      for (int j = 0; j < this.grid[i].length; j++) {
        System.out.print(this.grid[i][j] + " ");
      }
      System.out.println(" ");
    }
    System.out.println("");
    
  }
}
