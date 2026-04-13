import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);
        
        if (!sc.hasNext()) return;
        int t = sc.nextInt();
        
        while (t-- > 0) {
            long a = sc.nextLong();
            long b = sc.nextLong();
            long r = sc.nextLong();
            
            if (a < b) {
                long temp = a;
                a = b;
                b = temp;
            }
            
            long x = 0;
            boolean firstBitFound = false;
            
            // Loop from MSB to LSB
            for (int i = 60; i >= 0; i--) {
                long bitA = (a >> i) & 1;
                long bitB = (b >> i) & 1;
                
                if (bitA != bitB) {
                    if (!firstBitFound) {
                        // Kingmaker bit
                        firstBitFound = true;
                    } else {
                        // We want to flip bits that favor 'a' to favor 'b' instead
                        if (bitA == 1 && bitB == 0) {
                            // Check if flipping fits within budget 'r'
                            // Note: use 1L to prevent 32-bit overflow
                            if (x + (1L << i) <= r) {
                                x += (1L << i);
                            }
                        }
                    }
                }
            }
            
            long ans = (a ^ x) - (b ^ x);
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
        long nextLong() { return Long.parseLong(next()); }
    }
}