public class SuperQuickUnionUF {
    private int[] id;
    private int[] size;
    
    public SuperQuickUnionUF(int N) {    
        id = new int[N];
        size = new int[N];
        
        for (int i = 0; i < N; i++) {
            id[i] = i;
            size[i] = 1;
        }
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
    
    public static void main(String[] args) {
        SuperQuickUnionUF squuf = new SuperQuickUnionUF(10);
        squuf.union(0, 4);
        squuf.union(1, 9);
        squuf.union(2, 5);
        squuf.union(3, 7);
        squuf.union(3, 8);
        squuf.union(5, 7);
    };
    
}