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
            
            int[][] items = new int[n][2];
            
            for (int i = 0; i < n; i++) {
                items[i][0] = sc.nextInt(); // Alice's marbles
            }
            for (int i = 0; i < n; i++) {
                items[i][1] = sc.nextInt(); // Bob's marbles
            }
            
            // Sort descending by a[i] + b[i]
            Arrays.sort(items, (x, y) -> {
                // Using Long.compare to absolutely prevent overflow, 
                // though a+b fits in int since max is 2*10^9
                long sumX = (long)x[0] + x[1];
                long sumY = (long)y[0] + y[1];
                return Long.compare(sumY, sumX); 
            });
            
            long aliceScore = 0;
            long bobScore = 0;
            
            for (int i = 0; i < n; i++) {
                if (i % 2 == 0) {
                    aliceScore += items[i][0] - 1;
                } else {
                    bobScore += items[i][1] - 1;
                }
            }
            
            out.println(aliceScore - bobScore);
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