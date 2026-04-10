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
            int q = fr.nextInt();
            
            long[] prefSum = new long[n + 1];
            int[] prefOnes = new int[n + 1];
            
            for (int i = 1; i <= n; i++) {
                long val = fr.nextLong();
                prefSum[i] = prefSum[i - 1] + val;
                prefOnes[i] = prefOnes[i - 1] + (val == 1 ? 1 : 0);
            }
            
            for (int i = 0; i < q; i++) {
                int l = fr.nextInt();
                int r = fr.nextInt();
                
                int length = r - l + 1;
                
                if (length == 1) {
                    out.println("NO");
                    continue;
                }
                
                long rangeSum = prefSum[r] - prefSum[l - 1];
                int onesCount = prefOnes[r] - prefOnes[l - 1];
                
                // Minimum required sum
                long minRequired = (long) onesCount + length;
                
                if (rangeSum >= minRequired) {
                    out.println("YES");
                } else {
                    out.println("NO");
                }
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