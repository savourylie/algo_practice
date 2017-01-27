public class Percolation {
    public Percolation(int n) {              // create n-by-n grid, with all sites blocked
        
    };
    public void open(int row, int col) {return;};    // open site (row, col) if it is not open already
    public boolean isOpen(int row, int col) {return;};  // is site (row, col) open?
    public boolean isFull(int row, int col) {return;};  // is site (row, col) full?
    public int numberOfOpenSites() {return;};       // number of open sites
    public boolean percolates() {return;};              // does the system percolate?

    public static void main(String[] args) {return;};   // test client (optional)
}

class SuperQuickUnionUF {
    public SuperQuickUnionUF(int N) {
        id = new int[N];
        size = new int[N];
        
        IntStream.range(0, N).forEach(
        n -> {
            System.out.println(n);
            id[n] = n;
            size[n] = 1;
        }
    );
    };
    
    private int root(int i) {
        while (id[i] != i) {
            id[i] = id[id[i]];
            i = id[i];
        };
        
        return i;
    };
    
    public boolean connected(int p, int q) {
        return root(p) == root(q);
    };
    
    public void union(int p, int q) {
        p_root = root(p);
        q_root = root(q);
        
        if (p_root == q_root) {
            return;
        }
        
        if (size[p_root] > size[q_root]) {
            id[q_root] = p_root;
            size[p_root] += size[q_root];
        }
        
        else {
            id[p_root] = q_root;
            size[q_root] += size[p_root];
        }    
    }
    
}