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
            
            // Step 1: Find the index of the element with the maximum absolute value
            int maxAbsIdx = 0;
            for (int i = 1; i < n; i++) {
                if (Math.abs(a[i]) > Math.abs(a[maxAbsIdx])) {
                    maxAbsIdx = i;
                }
            }
            
            if (a[maxAbsIdx] == 0) {
                out.println(0);
                continue;
            }
            
            List<int[]> ops = new ArrayList<>();
            
            // Step 2: Homogenize the array
            if (a[maxAbsIdx] > 0) {
                for (int i = 0; i < n; i++) {
                    if (a[i] < 0) {
                        ops.add(new int[]{i, maxAbsIdx});
                        a[i] += a[maxAbsIdx];
                    }
                }
                // Step 3: Prefix sweep (left to right)
                for (int i = 1; i < n; i++) {
                    ops.add(new int[]{i, i - 1});
                    a[i] += a[i - 1];
                }
            } else {
                for (int i = 0; i < n; i++) {
                    if (a[i] > 0) {
                        ops.add(new int[]{i, maxAbsIdx});
                        a[i] += a[maxAbsIdx];
                    }
                }
                // Step 3: Suffix sweep (right to left)
                for (int i = n - 2; i >= 0; i--) {
                    ops.add(new int[]{i, i + 1});
                    a[i] += a[i + 1];
                }
            }
            
            out.println(ops.size());
            for (int[] op : ops) {
                out.println((op[0] + 1) + " " + (op[1] + 1));
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