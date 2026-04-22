import java.io.*;
import java.util.*;

public class Main {
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;
        public FastReader() { br = new BufferedReader(new InputStreamReader(System.in)); }
        String next() {
            while (st == null || !st.hasMoreElements()) {
                try { st = new StringTokenizer(br.readLine()); }
                catch (IOException e) { e.printStackTrace(); }
            }
            return st.nextToken();
        }
        int nextInt() { return Integer.parseInt(next()); }
    }

    public static void main(String[] args) {
        FastReader in = new FastReader();
        PrintWriter out = new PrintWriter(System.out);
        
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = in.nextInt();
            
            // 1. Mark first and last occurrences
            boolean[] isFirst = new boolean[n];
            boolean[] isLast = new boolean[n];
            Set<Integer> seen = new HashSet<>();
            
            for (int i = 0; i < n; i++) {
                if (!seen.contains(a[i])) {
                    isFirst[i] = true;
                    seen.add(a[i]);
                }
            }
            
            seen.clear();
            for (int i = n - 1; i >= 0; i--) {
                if (!seen.contains(a[i])) {
                    isLast[i] = true;
                    seen.add(a[i]);
                }
            }
            
            // 2. Build suffix sum of Lasts
            long[] suffixLasts = new long[n + 1];
            for (int i = n - 1; i >= 0; i--) {
                suffixLasts[i] = suffixLasts[i + 1] + (isLast[i] ? 1 : 0);
            }
            
            // 3. Count pairs (isFirst[i], isLast[j]) where i <= j
            long total = 0;
            for (int i = 0; i < n; i++) {
                if (isFirst[i]) {
                    total += suffixLasts[i];
                }
            }
            out.println(total);
        }
        out.flush();
    }
}