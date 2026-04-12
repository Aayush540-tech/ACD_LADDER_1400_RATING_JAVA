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
            int[] a = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                a[i] = sc.nextInt();
            }
            
            int q = sc.nextInt();
            
            // Step 1: Precompute Prefix Sums of bits
            int[][] pref = new int[n + 1][30];
            for (int i = 1; i <= n; i++) {
                for (int j = 0; j < 30; j++) {
                    pref[i][j] = pref[i - 1][j];
                    if ((a[i] & (1 << j)) != 0) {
                        pref[i][j]++;
                    }
                }
            }
            
            // Step 2: Process Queries
            for (int i = 0; i < q; i++) {
                int l = sc.nextInt();
                int k = sc.nextInt();
                
                if (a[l] < k) {
                    out.print("-1 ");
                    continue;
                }
                
                int low = l, high = n, bestR = l;
                
                while (low <= high) {
                    int mid = low + (high - low) / 2;
                    int currentAnd = 0;
                    int length = mid - l + 1;
                    
                    // Reconstruct the AND value
                    for (int j = 0; j < 30; j++) {
                        if (pref[mid][j] - pref[l - 1][j] == length) {
                            currentAnd |= (1 << j);
                        }
                    }
                    
                    if (currentAnd >= k) {
                        bestR = mid;
                        low = mid + 1; // Try to extend further right
                    } else {
                        high = mid - 1; // AND is too small, shrink range
                    }
                }
                out.print(bestR + " ");
            }
            out.println();
        }
        out.flush();
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