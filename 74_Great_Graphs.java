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
            long[] d = new long[n];
            
            for (int i = 0; i < n; i++) {
                d[i] = sc.nextLong();
            }
            
            // Step 1: Sort the distances
            Arrays.sort(d);
            
            // Step 2: Forward line sum
            long ans = d[n - 1];
            long prefixSum = 0;
            
            // Step 3: Negative back-edges sum
            for (int i = 0; i < n; i++) {
                // Cast 'i' to long to prevent integer overflow during multiplication
                ans += prefixSum - ((long) i * d[i]);
                prefixSum += d[i];
            }
            
            out.println(ans);
        }
        out.flush();
    }

    // Standard FastScanner boilerplate
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
        long nextLong() { return Long.parseLong(next()); }
    }
}