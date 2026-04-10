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
            
            // events array will store both l and r
            long[] events = new long[2 * n];
            
            // Read l (Type 0)
            for (int i = 0; i < n; i++) {
                long coord = sc.nextInt();
                events[i] = (coord << 32); // Shift coord to top 32 bits, bottom remains 0
            }
            
            // Read r (Type 1)
            for (int i = 0; i < n; i++) {
                long coord = sc.nextInt();
                events[n + i] = (coord << 32) | 1L; // Bottom bit becomes 1
            }
            
            // Read costs
            Integer[] c = new Integer[n]; // Integer object array for reverse sorting
            for (int i = 0; i < n; i++) {
                c[i] = sc.nextInt();
            }
            
            // Sort events by coordinate
            Arrays.sort(events);
            
            // Stack to match intervals
            int[] stack = new int[n];
            int top = 0;
            
            long[] lengths = new long[n];
            int lenIdx = 0;
            
            // Process events
            for (long ev : events) {
                int coord = (int) (ev >>> 32); // Extract top 32 bits
                int type = (int) (ev & 1);     // Extract bottom bit
                
                if (type == 0) {
                    stack[top++] = coord;
                } else {
                    lengths[lenIdx++] = coord - stack[--top];
                }
            }
            
            // Sort lengths ascending, costs descending
            Arrays.sort(lengths);
            Arrays.sort(c, Collections.reverseOrder());
            
            // Calculate minimum penalty
            long ans = 0;
            for (int i = 0; i < n; i++) {
                ans += lengths[i] * c[i];
            }
            out.println(ans);
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