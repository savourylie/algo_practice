import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import java.lang.IllegalArgumentException;
import java.util.Random;

public class PercolationStats {
    int matrixShape;
    int trials;
    double[] thresholds;
    double matrixSize;
        
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            IllegalArgumentException e = new IllegalArgumentException();
            throw e;
        }
        
        matrixShape = n;
        matrixSize = n * n;
                
        trials = trials;
        thresholds = new double[trials];
        
        int num_iter = 1;
        double rate = 0;
        double matrixSize = n * n;
        
        for (int i = 0; i < trials; ++i) {
            Percolation percolation = new Percolation(n);
            
            while (!percolation.percolates()) {
                int rnd = new Random().nextInt(n*n);
                rnd += 2;
                int[] coord2D = percolation.coordConvert1D_2D(rnd);
                
                percolation.open(coord2D[0] + 1, coord2D[1] + 1);                                
            }
            
            thresholds[i] = percolation.numOpenSites / matrixSize;
//            rate = rate + percolation.numOpenSites / matrixSize;
        }
        
    }    // perform trials independent experiments on an n-by-n grid
    public double mean() {
        double thresholdSum = 0;
        
        for (int i = 0; i < thresholds.length; ++i) {
            thresholdSum = thresholdSum + thresholds[i];
        }
        
        return thresholdSum / thresholds.length;
    }                          // sample mean of percolation threshold
    
    public double stddev() {
        return StdStats.stddev(thresholds);
    }                        // sample standard deviation of percolation threshold
    
    public double confidenceLo() {
        return mean() - 1.96 * stddev();
    }                  // low  endpoint of 95% confidence interval
    
    public double confidenceHi() {
        return mean() + 1.96 * stddev();
    }                  // high endpoint of 95% confidence interval
   
    public static void main(String[] args) {
        PercolationStats percoStats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));

        System.out.print("mean ");
        System.out.println(percoStats.mean());
        
        System.out.print("stddev ");
        System.out.println(percoStats.stddev());
        
        System.out.print("95% confidence interval ");
        System.out.println("[" + Double.toString(percoStats.confidenceLo()) + ", " + percoStats.confidenceHi() + "]");
    }   // test client (described below)
       
}