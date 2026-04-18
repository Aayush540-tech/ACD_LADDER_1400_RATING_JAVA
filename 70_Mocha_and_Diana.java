import java.io.*;
import java.util.*;

public class Main {
    
    // Standard DSU Implementation
    static class DSU {
        int[] parent;
        
        public DSU(int n) {
            parent = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
            }
        }
        
        public int find(int i) {
            if (parent[i] == i) {
                return i;
            }
            // Path compression
            return parent[i] = find(parent[i]);
        }
        
        public boolean union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);
            if (rootI != rootJ) {
                parent[rootI] = rootJ;
                return true;
            }
            return false;
        }
    }

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);
        
        if (!sc.hasNext()) return;
        
        int n = sc.nextInt();
        int m1 = sc.nextInt();
        int m2 = sc.nextInt();
        
        DSU dsuMocha = new DSU(n);
        DSU dsuDiana = new DSU(n);
        
        // Mocha's edges
        for (int i = 0; i < m1; i++) {
            dsuMocha.union(sc.nextInt(), sc.nextInt());
        }
        
        // Diana's edges
        for (int i = 0; i < m2; i++) {
            dsuDiana.union(sc.nextInt(), sc.nextInt());
        }
        
        List<String> ans = new ArrayList<>();
        
        // The Greedy N^2 Loop
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                // If it doesn't form a cycle in Mocha's forest AND Diana's forest
                if (dsuMocha.find(i) != dsuMocha.find(j) && dsuDiana.find(i) != dsuDiana.find(j)) {
                    dsuMocha.union(i, j);
                    dsuDiana.union(i, j);
                    ans.add(i + " " + j);
                }
            }
        }
        
        out.println(ans.size());
        for (String edge : ans) {
            out.println(edge);
        }
        out.flush();
    }

    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        boolean hasNext() {
            while (st == null || !st.hasMoreElements()) {
                try { 
                    String line = br.readLine();
                    if (line == null) return false;
                    st = new StringTokenizer(line); 
                }
                catch (IOException e) { return false; }
            }
            return true;
        }
        String next() {
            if (!hasNext()) return null;
            return st.nextToken();
        }
        int nextInt() { return Integer.parseInt(next()); }
    }
}