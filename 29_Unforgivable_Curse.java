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
            int k = fr.nextInt();
            String s = fr.next();
            String tStr = fr.next();
            
            // Check Rule 1: Character frequencies using arrays
            char[] sArr = s.toCharArray();
            char[] tArr = tStr.toCharArray();
            Arrays.sort(sArr);
            Arrays.sort(tArr);
            
            if (!Arrays.equals(sArr, tArr)) {
                out.println("NO");
                continue;
            }
            
            // Check Rule 2: Locked elements must match
            boolean possible = true;
            for (int i = 0; i < n; i++) {
                if (i < k && i + k >= n) {
                    if (s.charAt(i) != tStr.charAt(i)) {
                        possible = false;
                        break;
                    }
                }
            }
            
            if (possible) {
                out.println("YES");
            } else {
                out.println("NO");
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