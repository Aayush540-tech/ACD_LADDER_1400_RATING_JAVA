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
            String s = sc.next();
            
            int first = -1;
            int last = -1;
            int onesCount = 0;
            
            // Find first and last occurrences of '1', and total count
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == '1') {
                    if (first == -1) first = i;
                    last = i;
                    onesCount++;
                }
            }
            
            if (onesCount == 0) {
                out.println(0);
                continue;
            }
            
            // Base score: assuming all '1's are in the middle (cost 11 each)
            long ans = 11L * onesCount;
            
            // Priority 1: Move last '1' to the end
            if (n - 1 - last <= k) {
                ans -= 10;
                k -= (n - 1 - last);
                
                // If there's only one '1', we used it. Stop here.
                if (first == last) {
                    out.println(ans);
                    continue;
                }
            }
            
            // Priority 2: Move first '1' to the front
            if (first <= k) {
                ans -= 1;
            }
            
            out.println(ans);
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