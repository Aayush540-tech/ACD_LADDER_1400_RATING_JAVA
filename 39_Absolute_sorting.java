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
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }
            
            int minX = 0;
            int maxX = (int) 1e9;
            
            for (int i = 0; i < n - 1; i++) {
                int left = a[i];
                int right = a[i+1];
                
                if (left < right) {
                    // Ascending: Update ceiling (Floor division)
                    int upperBound = (left + right) / 2;
                    maxX = Math.min(maxX, upperBound);
                } else if (left > right) {
                    // Descending: Update floor (Ceiling division)
                    // (left + right + 1) / 2 is the integer math trick for ceil(a/2)
                    int lowerBound = (left + right + 1) / 2;
                    minX = Math.max(minX, lowerBound);
                }
            }
            
            if (minX > maxX) {
                out.println("-1");
            } else {
                out.println(minX);
            }
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