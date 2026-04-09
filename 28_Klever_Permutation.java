import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        FastReader fr = new FastReader();
        PrintWriter out = new PrintWriter(System.out);
        
        if (!fr.hasNext()) return;
        int t = fr.nextInt();
        
        while (t-- > 0) {
            int n = fr.nextInt();
            int k = fr.nextInt();
            
            int[] ans = new int[n];
            int left = 1;
            int right = n;
            
            // Iterate through each modulo k "strand"
            for (int i = 0; i < k; i++) {
                if (i % 2 == 0) {
                    // Even strands get increasing small numbers
                    for (int j = i; j < n; j += k) {
                        ans[j] = left++;
                    }
                } else {
                    // Odd strands get decreasing large numbers
                    for (int j = i; j < n; j += k) {
                        ans[j] = right--;
                    }
                }
            }
            
            // Print the constructed array
            for (int i = 0; i < n; i++) {
                out.print(ans[i] + (i == n - 1 ? "" : " "));
            }
            out.println();
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
    }
}