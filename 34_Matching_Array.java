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
            int x = sc.nextInt();
            
            int[] a = new int[n];
            // Store A's values and original indices
            int[][] aSorted = new int[n][2];
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
                aSorted[i][0] = a[i];
                aSorted[i][1] = i;
            }
            
            int[] b = new int[n];
            for (int i = 0; i < n; i++) {
                b[i] = sc.nextInt();
            }
            
            // Sort A based on values
            Arrays.sort(aSorted, Comparator.comparingInt(arr -> arr[0]));
            // Sort B
            Arrays.sort(b);
            
            int[] ans = new int[n];
            
            // Assign Bob's elements
            for (int i = 0; i < n; i++) {
                if (i < n - x) {
                    ans[aSorted[i][1]] = b[i + x];
                } else {
                    ans[aSorted[i][1]] = b[i - (n - x)];
                }
            }
            
            // The Sanity Check
            int actualWins = 0;
            for (int i = 0; i < n; i++) {
                if (a[i] > ans[i]) {
                    actualWins++;
                }
            }
            
            if (actualWins == x) {
                out.println("YES");
                for (int i = 0; i < n; i++) {
                    out.print(ans[i] + " ");
                }
                out.println();
            } else {
                out.println("NO");
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