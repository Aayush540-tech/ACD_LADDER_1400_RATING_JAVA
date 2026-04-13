import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);
        
        if (!sc.hasNext()) return;
        int t = sc.nextInt();
        
        while (t-- > 0) {
            int n = sc.nextInt();
            int[] p = new int[n];
            for (int i = 0; i < n; i++) {
                p[i] = sc.nextInt();
            }
            
            if (n == 1) {
                out.println(p[0]);
                continue;
            }
            
            // Step 1: Find max element not at index 0
            int maxVal = -1;
            int maxIdx = -1;
            for (int i = 1; i < n; i++) {
                if (p[i] > maxVal) {
                    maxVal = p[i];
                    maxIdx = i;
                }
            }
            
            // Step 2: Define candidate right bounds
            List<Integer> rCandidates = new ArrayList<>();
            rCandidates.add(maxIdx - 1);
            if (maxIdx == n - 1) {
                rCandidates.add(n - 1);
            }
            
            int[] bestAns = null;
            
            // Step 3: Brute-force l
            for (int r : rCandidates) {
                for (int l = 0; l <= r; l++) {
                    int[] curr = new int[n];
                    int idx = 0;
                    
                    // Add Suffix
                    for (int i = r + 1; i < n; i++) curr[idx++] = p[i];
                    // Add Reversed Middle
                    for (int i = r; i >= l; i--) curr[idx++] = p[i];
                    // Add Prefix
                    for (int i = 0; i < l; i++) curr[idx++] = p[i];
                    
                    // Compare lexicographically
                    if (bestAns == null || compare(curr, bestAns) > 0) {
                        bestAns = curr;
                    }
                }
            }
            
            for (int i = 0; i < n; i++) {
                out.print(bestAns[i] + (i == n - 1 ? "" : " "));
            }
            out.println();
        }
        out.flush();
    }
    
    // Helper method for lexicographical comparison
    private static int compare(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return Integer.compare(a[i], b[i]);
            }
        }
        return 0;
    }

    // Standard Fast I/O
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