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
            
            long[] a = new long[n];
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextLong();
            }
            
            String s = sc.next();
            
            // Step 1: Scout the pointers
            int l = 0;
            int r = n - 1;
            for (int i = 0; i < n - 1; i++) {
                if (s.charAt(i) == 'L') {
                    l++;
                } else {
                    r--;
                }
            }
            
            long[] ans = new long[n];
            long currentProd = 1;
            
            // Step 2: Build the product backwards
            for (int i = n - 1; i >= 0; i--) {
                if (s.charAt(i) == 'L') {
                    currentProd = (currentProd * a[l]) % m;
                    l--; // Move outwards
                } else {
                    currentProd = (currentProd * a[r]) % m;
                    r++; // Move outwards
                }
                ans[i] = currentProd; // Storing backwards automatically reverses it!
            }
            
            // Step 3: Print the results
            for (int i = 0; i < n; i++) {
                out.print(ans[i] + (i == n - 1 ? "" : " "));
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
        long nextLong() { return Long.parseLong(next()); }
    }
}