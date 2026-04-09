import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        FastReader fr = new FastReader();
        PrintWriter out = new PrintWriter(System.out);
        
        int t = fr.nextInt();
        while (t-- > 0) {
            int n = fr.nextInt();
            String s = fr.next();
            
            int p = 0;
            boolean found = false;
            
            // Scan from right to left
            for (int i = n - 1; i >= 0; i--) {
                if (s.charAt(i) == '(') {
                    p++;
                } else if (s.charAt(i) == ')') {
                    // If we see a ')' and there are at least two '(' to its right
                    if (p > 1) {
                        found = true;
                        break;
                    }
                }
            }
            
            if (found) {
                out.println(n - 2);
            } else {
                out.println("-1");
            }
        }
        out.flush();
    }

    // Standard Fast I/O
    static class FastReader {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try { st = new StringTokenizer(br.readLine()); }
                catch (IOException e) { e.printStackTrace(); }
            }
            return st.nextToken();
        }
        int nextInt() { return Integer.parseInt(next()); }
    }
}