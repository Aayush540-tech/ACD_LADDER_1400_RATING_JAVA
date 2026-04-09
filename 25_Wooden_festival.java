import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        FastReader fr = new FastReader();
        PrintWriter out = new PrintWriter(System.out);
        
        int t = fr.nextInt();
        while (t-- > 0) {
            int n = fr.nextInt();
            
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = fr.nextInt();
            }
            
            if (n <= 3) {
                out.println(0);
                continue;
            }
            
            Arrays.sort(a);
            
            int left = 0;
            int right = a[n - 1] - a[0];
            int ans = right;
            
            while (left <= right) {
                int mid = left + (right - left) / 2;
                
                if (canCover(a, n, mid)) {
                    ans = mid;
                    right = mid - 1; // Try for a smaller max wait time
                } else {
                    left = mid + 1;  // Too strict, increase wait time
                }
            }
            
            out.println(ans);
        }
        out.flush();
    }
    
    // Greedy check function
    private static boolean canCover(int[] a, int n, int maxWait) {
        int workers = 1;
        int currentStart = a[0];
        
        for (int i = 1; i < n; i++) {
            if (a[i] - currentStart > 2L * maxWait) {
                workers++;
                currentStart = a[i];
            }
        }
        
        return workers <= 3;
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