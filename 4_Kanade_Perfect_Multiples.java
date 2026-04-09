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
            long k = fr.nextLong();
            
            long[] a = new long[n];
            HashSet<Long> aSet = new HashSet<>();
            
            for (int i = 0; i < n; i++) {
                a[i] = fr.nextLong();
                aSet.add(a[i]);
            }
            
            Arrays.sort(a);
            
            List<Long> b = new ArrayList<>();
            HashSet<Long> covered = new HashSet<>();
            boolean possible = true;
            
            for (int i = 0; i < n; i++) {
                long x = a[i];
                
                // If this element was already marked by a smaller factor, skip it
                if (covered.contains(x)) continue;
                
                b.add(x);
                
                // Validate that all multiples up to K exist in the original array
                for (long u = x; u <= k; u += x) {
                    if (!aSet.contains(u)) {
                        possible = false;
                        break;
                    }
                    covered.add(u);
                }
                
                if (!possible) break;
            }
            
            if (possible) {
                out.println(b.size());
                for (int i = 0; i < b.size(); i++) {
                    out.print(b.get(i) + (i == b.size() - 1 ? "" : " "));
                }
                out.println();
            } else {
                out.println("-1");
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