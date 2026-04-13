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
            int m = sc.nextInt();
            
            int[] kArr = new int[n];
            for (int i = 0; i < n; i++) {
                kArr[i] = sc.nextInt();
            }
            // Sort to enable Binary Search
            Arrays.sort(kArr);
            
            for (int i = 0; i < m; i++) {
                long a = sc.nextLong();
                long b = sc.nextLong();
                long c = sc.nextLong();
                
                // Find insertion point of 'b'
                int pos = Arrays.binarySearch(kArr, (int)b);
                if (pos < 0) {
                    pos = -(pos + 1); // Convert to insertion index
                }
                
                boolean found = false;
                
                // Check candidate >= b
                if (pos < n) {
                    long k1 = kArr[pos];
                    if ((b - k1) * (b - k1) < 4L * a * c) {
                        out.println("YES");
                        out.println(k1);
                        found = true;
                    }
                }
                
                // Check candidate < b
                if (!found && pos > 0) {
                    long k2 = kArr[pos - 1];
                    if ((b - k2) * (b - k2) < 4L * a * c) {
                        out.println("YES");
                        out.println(k2);
                        found = true;
                    }
                }
                
                if (!found) {
                    out.println("NO");
                }
            }
            out.println(); // Blank line between testcases
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
                    if (line == null || line.trim().isEmpty()) {
                        line = br.readLine(); // handle empty lines
                        if (line == null) return false;
                    }
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