import java.io.*;
import java.util.*;

public class Main {
    
    // Helper method to get the compressed canonical form
    static List<long[]> getCanonical(long[] arr, long m) {
        List<long[]> stack = new ArrayList<>();
        
        for (long x : arr) {
            long val = x;
            long cnt = 1;
            
            // Break down
            while (val % m == 0) {
                val /= m;
                cnt *= m;
            }
            
            // RLE merge logic
            if (!stack.isEmpty() && stack.get(stack.size() - 1)[0] == val) {
                stack.get(stack.size() - 1)[1] += cnt;
            } else {
                stack.add(new long[]{val, cnt});
            }
        }
        return stack;
    }

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);
        
        if (!sc.hasNext()) return;
        int t = sc.nextInt();
        
        while (t-- > 0) {
            int n = sc.nextInt();
            long m = sc.nextLong();
            
            long[] a = new long[n];
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextLong();
            }
            
            int k = sc.nextInt();
            long[] b = new long[k];
            for (int i = 0; i < k; i++) {
                b[i] = sc.nextLong();
            }
            
            List<long[]> canonicalA = getCanonical(a, m);
            List<long[]> canonicalB = getCanonical(b, m);
            
            // Compare size first
            if (canonicalA.size() != canonicalB.size()) {
                out.println("No");
                continue;
            }
            
            boolean match = true;
            for (int i = 0; i < canonicalA.size(); i++) {
                if (canonicalA.get(i)[0] != canonicalB.get(i)[0] || 
                    canonicalA.get(i)[1] != canonicalB.get(i)[1]) {
                    match = false;
                    break;
                }
            }
            
            out.println(match ? "Yes" : "No");
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
        long nextLong() { return Long.parseLong(next()); }
    }
}