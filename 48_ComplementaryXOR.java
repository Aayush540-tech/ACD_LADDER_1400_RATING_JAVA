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
            String a = sc.next();
            String b = sc.next();
            
            boolean same = true;
            boolean opp = true;
            
            for (int i = 0; i < n; i++) {
                if (a.charAt(i) != b.charAt(i)) {
                    same = false;
                } else {
                    opp = false;
                }
            }
            
            if (!same && !opp) {
                out.println("NO");
                continue;
            }
            
            out.println("YES");
            Set<String> ops = new HashSet<>();
            char[] aArr = a.toCharArray();
            
            // Step 1: Force identical
            if (opp) {
                toggle(ops, 1, n);
                for (int i = 0; i < n; i++) {
                    aArr[i] = aArr[i] == '1' ? '0' : '1';
                }
            }
            
            // Step 2: The [i, i] Scalpel
            int c = 0;
            for (int i = 0; i < n; i++) {
                if (aArr[i] == '1') {
                    toggle(ops, i + 1, i + 1);
                    c++;
                }
            }
            
            // Step 3 & 4: The Magic Combo
            if (c % 2 == 1) {
                toggle(ops, 1, 1);
                toggle(ops, 2, n);
                toggle(ops, 1, n);
            }
            
            out.println(ops.size());
            for (String op : ops) {
                out.println(op);
            }
        }
        out.flush();
    }
    
    // Helper for operation cancellation
    private static void toggle(Set<String> ops, int l, int r) {
        String op = l + " " + r;
        if (ops.contains(op)) {
            ops.remove(op);
        } else {
            ops.add(op);
        }
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