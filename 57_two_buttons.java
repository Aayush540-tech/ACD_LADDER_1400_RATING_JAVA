import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);
        
        if (!sc.hasNext()) return;
        
        int n = sc.nextInt();
        int m = sc.nextInt();
        
        int clicks = 0;
        
        // Work backwards from m to n
        while (m > n) {
            if (m % 2 == 0) {
                m /= 2;
            } else {
                m++;
            }
            clicks++;
        }
        
        // Add the remaining straight-line distance if m < n
        clicks += (n - m);
        
        out.println(clicks);
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