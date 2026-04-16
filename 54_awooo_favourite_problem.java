import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);
        
        if (!sc.hasNext()) return;
        int q = sc.nextInt();
        
        while (q-- > 0) {
            int n = sc.nextInt();
            String s = sc.next();
            String t = sc.next();
            
            int i = 0, j = 0;
            boolean possible = true;
            
            while (i < n || j < n) {
                // Skip 'b's in both strings
                while (i < n && s.charAt(i) == 'b') i++;
                while (j < n && t.charAt(j) == 'b') j++;
                
                // If one string runs out of non-'b' characters before the other
                if (i == n || j == n) {
                    if (i != n || j != n) {
                        possible = false;
                    }
                    break;
                }
                
                // The characters must match
                if (s.charAt(i) != t.charAt(j)) {
                    possible = false;
                    break;
                }
                
                // 'a' can only move right (so starting i must be <= target j)
                if (s.charAt(i) == 'a' && i > j) {
                    possible = false;
                    break;
                }
                
                // 'c' can only move left (so starting i must be >= target j)
                if (s.charAt(i) == 'c' && i < j) {
                    possible = false;
                    break;
                }
                
                i++;
                j++;
            }
            
            out.println(possible ? "YES" : "NO");
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