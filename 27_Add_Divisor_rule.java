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
            
            int m = Integer.MAX_VALUE;
            int M = Integer.MIN_VALUE;
            
            // Find the minimum and maximum elements
            for (int i = 0; i < n; i++) {
                int val = fr.nextInt();
                if (val < m) m = val;
                if (val > M) M = val;
            }
            
            List<Integer> ops = new ArrayList<>();
            
            // Bring the max and min together
            while (M > m) {
                int x = m % 2;
                ops.add(x);
                m = (m + x) / 2;
                M = (M + x) / 2;
            }
            
            out.println(ops.size());
            
            // Output the operations if count is <= n
            if (ops.size() <= n && !ops.isEmpty()) {
                for (int i = 0; i < ops.size(); i++) {
                    out.print(ops.get(i) + (i == ops.size() - 1 ? "" : " "));
                }
                out.println();
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
    }
}