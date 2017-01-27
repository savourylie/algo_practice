public class Percolation {
    public Bicycle(int startCadence, int startSpeed, int startGear) {
        gear = startGear;
        cadence = startCadence;
        speed = startSpeed;
    }
    
    public Percolation(int n) {              // create n-by-n grid, with all sites blocked
        
    }
    public    void open(int row, int col)    // open site (row, col) if it is not open already
    public boolean isOpen(int row, int col)  // is site (row, col) open?
    public boolean isFull(int row, int col)  // is site (row, col) full?
    public     int numberOfOpenSites()       // number of open sites
    public boolean percolates()              // does the system percolate?

    public static void main(String[] args)   // test client (optional)
}

public class SuperQuickUnionUF {
    public SuperQuickUnionUF(N) {
        id = new int[N];
        Map size = new HashMap();
    }
    
    private int root(int p) {
        if (id[p] != p) {
            
        }
    }
    public boolean connected(int p, int q) {
        
    }
    
    
    
}