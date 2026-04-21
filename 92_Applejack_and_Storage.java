import java.io.*;
import java.util.*;

public class Main {
    // Fast I/O
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
        
        int n = in.nextInt();
        int[] counts = new int[100005];
        
        int cnt2 = 0;
        int cnt4 = 0;
        
        // 1. Process initial inventory
        for (int i = 0; i < n; i++) {
            int length = in.nextInt();
            counts[length]++;
            
            if (counts[length] % 2 == 0) cnt2++;
            if (counts[length] % 4 == 0) cnt4++;
        }
        
        int q = in.nextInt();
        
        // 2. Process queries
        while (q-- > 0) {
            String op = in.next();
            int length = in.nextInt();
            
            if (op.equals("+")) {
                counts[length]++;
                if (counts[length] % 2 == 0) cnt2++;
                if (counts[length] % 4 == 0) cnt4++;
            } else {
                if (counts[length] % 4 == 0) cnt4--;
                if (counts[length] % 2 == 0) cnt2--;
                counts[length]--;
            }
            
            // 3. Evaluate the Golden Condition
            if (cnt4 >= 1 && cnt2 >= 4) {
                out.println("YES");
            } else {
                out.println("NO");
            }
        }
        out.flush();
    }
}