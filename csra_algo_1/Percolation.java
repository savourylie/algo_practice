import java.util.Random;    
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class Percolation {
    private SuperQuickUnionUF sites;
    private boolean[] siteStatusArray;
    private int matrixShape;
    private int numOpenSites;
    
    public Percolation(int n) {              // create n-by-n grid, with all sites blocked
        if (n <= 0) {
            IllegalArgumentException e = new IllegalArgumentException();
            throw e;
        }
            
        matrixShape = n;
        sites = new SuperQuickUnionUF(n*n + 2);
        siteStatusArray = new boolean[n*n + 2];
        siteStatusArray[0] = true;
        siteStatusArray[1] = true;
        
        for (int i = 2; i < n*n; ++i) {
            siteStatusArray[i] = false;
        }
        
        numOpenSites = 0; //
    };
    
    private int[] coordConvert1D_2D(int index) {
        int[] coord2D = new int[2];
        coord2D[0] = (index - 2) / matrixShape;
        coord2D[1] = (index - 2) % matrixShape;
        
        return coord2D;
    }
    
    private int coordConvert2D_1D(int[] coords) {
        int coord1D;
        coord1D = coords[0] * matrixShape + coords[1] + 2;
        
        return coord1D;
    };

    public void open(int row, int col) {
        if (row > matrixShape || col > matrixShape) {
            IndexOutOfBoundsException e = new IndexOutOfBoundsException();
            throw e;
        }
        
        if (row < 1 || col < 1) {
            IndexOutOfBoundsException e = new IndexOutOfBoundsException();
            throw e;
        }
        
        row = row - 1;
        col = col - 1;
        
        int coord1D;
        int[] coord2D = new int[2];
        
        coord2D[0] = row;
        coord2D[1] = col;
           
        coord1D = coordConvert2D_1D(coord2D);
//        System.out.println(coord1D);
        
        // Open the appointed site
        if (!siteStatusArray[coord1D]) {
            siteStatusArray[coord1D] = true;
            ++numOpenSites;

            if (coord2D[0] == 0) {
                sites.union(0, coord1D);
            }
            
            if (coord2D[0] == matrixShape - 1) {
//                System.out.println(coord1D);
                sites.union(1, coord1D);
            }
        }
        
        // Union the sites around it
        int[] up_index = new int[2];
        int[] right_index = new int[2];
        int[] butt_index = new int[2];
        int[] left_index = new int[2];
//        
//        System.out.print("Now coord: \n");
//        System.out.println(row);
//        System.out.println(col);
//        System.out.println("======");
        up_index[0] = row - 1;
        up_index[1] = col;
        right_index[0] = row;
        right_index[1] = col + 1;
        butt_index[0] = row + 1;
        butt_index[1] = col;
        left_index[0] = row;
        left_index[1] = col - 1;
        
        int[][] vicinityIndex = {up_index, right_index, butt_index, left_index};
        
        for (int i = 0; i < vicinityIndex.length; ++i) {
            int[] v_coord2D = vicinityIndex[i];
            int v_coord1D = coordConvert2D_1D(v_coord2D);
//            
//            System.out.println(v_coord2D[0]);
//            System.out.println(v_coord2D[1]);
//            System.out.println(v_coord1D);
            if (v_coord2D[0] >= 0 && v_coord2D[1] >= 0 && v_coord2D[0] < matrixShape && v_coord2D[1] < matrixShape) {
                if (siteStatusArray[v_coord1D] == true) {
//                    if (coord1D == 9) {
//                        System.out.println(v_coord1D);
//                        System.out.println("WTF");
//                        System.out.println(v_coord2D[0]);
//                        System.out.println(v_coord2D[1]);
//                        System.out.println(" ");
//                    }
//                    System.out.println(v_coord1D);
//                    System.out.println(siteStatusArray[v_coord1D]);
//                    System.out.println(v_coord2D[0]);
//                    System.out.println(v_coord2D[1]);
//                    System.out.println(v_coord1D);
                
                    sites.union(coord1D, v_coord1D);
                }
            }
        }
    };    // open site (row, col) if it is not open already
    
    public boolean isOpen(int row, int col) {
        if (row > matrixShape || col > matrixShape) {
            IndexOutOfBoundsException e = new IndexOutOfBoundsException();
            throw e;
        }
        
        if (row < 1 || col < 1) {
            IndexOutOfBoundsException e = new IndexOutOfBoundsException();
            throw e;
        }
        
        row = row - 1;
        col = col - 1;
            
        int coord1D;
        int[] coord2D = new int[2];
        coord2D[0] = row;
        coord2D[1] = col;
        
        coord1D = coordConvert2D_1D(coord2D);
            
        return siteStatusArray[coord1D];            
    };  // is site (row, col) open?
    
    public boolean isFull(int row, int col) {
        if (row > matrixShape || col > matrixShape) {
            IndexOutOfBoundsException e = new IndexOutOfBoundsException();
            throw e;
        }
        
        if (row < 1 || col < 1) {
            IndexOutOfBoundsException e = new IndexOutOfBoundsException();
            throw e;
        }
        
        row = row - 1;
        col = col - 1;
        
        int coord1D;
        int[] coord2D = new int[2];
        coord2D[0] = row;
        coord2D[1] = col;
        coord1D = coordConvert2D_1D(coord2D);
      
        return sites.connected(0, coord1D);
    };  // is site (row, col) full?
    
    public int numberOfOpenSites() {
        return numOpenSites;
    };       // number of open sites
    
    public boolean percolates() {        
        return sites.connected(0, 1);
    };              // does the system percolate?

    public static void main(String[] args) {
        int n = 60;
        int num_iter = 1;
        double rate = 0;
        double matrixSize = n * n;
        
        for (int i = 0; i < num_iter; ++i) {
            Percolation percolation = new Percolation(n);
            
            while (!percolation.percolates()) {
                int rnd = new Random().nextInt(n*n);
                rnd += 2;
                int[] coord2D = percolation.coordConvert1D_2D(rnd);
                
                percolation.open(coord2D[0] + 1, coord2D[1] + 1);                                
            }
            
            rate = rate + percolation.numOpenSites / matrixSize;
        }
        
        
        
        double rate_avg = rate / num_iter;
        System.out.println(rate_avg);
        
    };   // test client (optional)
    
    private class SuperQuickUnionUF {
        private int[] id;
        private int[] size;
        
        public SuperQuickUnionUF(int N) {
//        System.out.println(N);
            id = new int[N];
            size = new int[N];
            
            for (int i = 0; i < N; i++) {
                id[i] = i;
                size[i] = 1;
            }
        };
        
        private int root(int i) {
//        System.out.println(i);
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
            
            int p_root = root(p);
            int q_root = root(q);
            
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
}

