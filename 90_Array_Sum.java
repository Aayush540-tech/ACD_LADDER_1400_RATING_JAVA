import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // Fast I/O
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        String line = br.readLine();
        if (line == null) return;
        int t = Integer.parseInt(line.trim());
        
        while (t-- > 0) {
            String[] nk = br.readLine().trim().split("\\s+");
            int n = Integer.parseInt(nk[0]);
            int k = Integer.parseInt(nk[1]);
            
            String[] elements = br.readLine().trim().split("\\s+");
            
            // 1. Count the number of distinct elements
            int c = 1; // At least one element exists
            for (int i = 1; i < n; i++) {
                if (!elements[i].equals(elements[i-1])) {
                    c++;
                }
            }
            
            // 2. Handle the k=1 trap
            if (k == 1) {
                if (c > 1) {
                    out.println(-1);
                } else {
                    out.println(1);
                }
                continue;
            }
            
            // 3. Integer math for ceiling division: ceil(A / B) = (A + B - 1) / B
            // Here: ceil((c - 1) / (k - 1))
            int jumps = c - 1;
            int capacity = k - 1;
            int ans = (jumps + capacity - 1) / capacity;
            
            // We always need at least 1 array
            out.println(Math.max(1, ans));
        }
        out.flush();
    }
}