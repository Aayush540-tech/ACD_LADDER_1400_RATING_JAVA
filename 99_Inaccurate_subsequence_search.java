import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        FastReader in = new FastReader();
        PrintWriter out = new PrintWriter(System.out);
        
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int m = in.nextInt();
            int k = in.nextInt();
            
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = in.nextInt();
            
            // 1. Build the 'shopping list' from array B
            Map<Integer, Integer> countB = new HashMap<>();
            for (int i = 0; i < m; i++) {
                int bVal = in.nextInt();
                countB.put(bVal, countB.getOrDefault(bVal, 0) + 1);
            }
            
            Map<Integer, Integer> windowCounts = new HashMap<>();
            int currentMatches = 0;
            int validSubarrays = 0;
            
            // 2. Initialize the first window (0 to m-1)
            for (int i = 0; i < m; i++) {
                int val = a[i];
                windowCounts.put(val, windowCounts.getOrDefault(val, 0) + 1);
                // If this element is still 'needed' according to countB
                if (windowCounts.get(val) <= countB.getOrDefault(val, 0)) {
                    currentMatches++;
                }
            }
            
            if (currentMatches >= k) validSubarrays++;
            
            // 3. Slide the window
            for (int i = m; i < n; i++) {
                int entering = a[i];
                int leaving = a[i - m];
                
                // Process element leaving
                if (windowCounts.get(leaving) <= countB.getOrDefault(leaving, 0)) {
                    currentMatches--;
                }
                windowCounts.put(leaving, windowCounts.get(leaving) - 1);
                
                // Process element entering
                windowCounts.put(entering, windowCounts.getOrDefault(entering, 0) + 1);
                if (windowCounts.get(entering) <= countB.getOrDefault(entering, 0)) {
                    currentMatches++;
                }
                
                if (currentMatches >= k) validSubarrays++;
            }
            
            out.println(validSubarrays);
        }
        out.flush();
    }
    
    // Fast I/O class
    static class FastReader {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String next() {
            while (st == null || !st.hasMoreElements()) {
                try { st = new StringTokenizer(br.readLine()); } catch (IOException e) {}
            }
            return st.nextToken();
        }
        int nextInt() { return Integer.parseInt(next()); }
    }
}