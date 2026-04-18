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
            int k = sc.nextInt();
            
            int[] a = new int[n];
            // pos will store the indices of each value
            List<Integer>[] pos = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                pos[i] = new ArrayList<>();
            }
            
            // Step 1: Collect indices, capped at k
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
                if (pos[a[i]].size() < k) {
                    pos[a[i]].add(i);
                }
            }
            
            // Step 2: Flatten into a pool
            List<Integer> paintableIndices = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                paintableIndices.addAll(pos[i]);
            }
            
            // Step 3: Trim to a multiple of k
            int validCount = paintableIndices.size() - (paintableIndices.size() % k);
            
            // Step 4: Deal the colors
            int[] ans = new int[n];
            int color = 1;
            
            for (int i = 0; i < validCount; i++) {
                int originalIndex = paintableIndices.get(i);
                ans[originalIndex] = color;
                
                color++;
                if (color > k) {
                    color = 1;
                }
            }
            
            // Print the answer for this test case
            for (int i = 0; i < n; i++) {
                out.print(ans[i] + (i == n - 1 ? "" : " "));
            }
            out.println();
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
    }
}