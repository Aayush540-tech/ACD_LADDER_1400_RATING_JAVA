import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        FastReader fr = new FastReader();
        PrintWriter out = new PrintWriter(System.out);
        
        if (!fr.hasNext()) return;
        int t = fr.nextInt();
        
        while (t-- > 0) {
            long k = fr.nextLong();
            int x = fr.nextInt();
            long a = fr.nextLong();
            
            long S = 0;
            boolean possible = true;
            
            // We must survive x losses, and win on the (x+1)-th game
            for (int i = 0; i <= x; i++) {
                // Minimum bet needed to recover S and turn a profit
                long y = S / (k - 1) + 1;
                S += y;
                
                // If our total spent exceeds our initial balance, we go bankrupt
                if (S > a) {
                    possible = false;
                    break;
                }
            }
            
            if (possible) {
                out.println("YES");
            } else {
                out.println("NO");
            }
        }
        out.flush();
    }

    // Standard Fast I/O
    static class FastReader {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        boolean hasNext() {
            while (st == null || !st.hasMoreElements()) {
                try { 
                    String line = br.readLine();
                    if (line == null) return false;
                    st = new StringTokenizer(line); 
                }
                catch (IOException e) { e.printStackTrace(); return false; }
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