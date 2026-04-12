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
            int m = sc.nextInt();
            
            char[][] grid = new char[n][m];
            for (int i = 0; i < n; i++) {
                grid[i] = sc.next().toCharArray();
            }
            
            char[][] ans = new char[n][m];
            for (int i = 0; i < n; i++) Arrays.fill(ans[i], '.');
            
            boolean possible = true;
            
            // Step 1: Process Rows (Only care about Vertical Dominoes: 'U')
            for (int i = 0; i < n; i++) {
                int uCount = 0;
                boolean colorToggle = true; 
                
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] == 'U') {
                        uCount++;
                        if (colorToggle) {
                            ans[i][j] = 'W';
                            ans[i+1][j] = 'B';
                        } else {
                            ans[i][j] = 'B';
                            ans[i+1][j] = 'W';
                        }
                        colorToggle = !colorToggle;
                    }
                }
                if (uCount % 2 != 0) {
                    possible = false;
                    break;
                }
            }
            
            // Step 2: Process Columns (Only care about Horizontal Dominoes: 'L')
            if (possible) {
                for (int j = 0; j < m; j++) {
                    int lCount = 0;
                    boolean colorToggle = true; 
                    
                    for (int i = 0; i < n; i++) {
                        if (grid[i][j] == 'L') {
                            lCount++;
                            if (colorToggle) {
                                ans[i][j] = 'W';
                                ans[i][j+1] = 'B';
                            } else {
                                ans[i][j] = 'B';
                                ans[i][j+1] = 'W';
                            }
                            colorToggle = !colorToggle;
                        }
                    }
                    if (lCount % 2 != 0) {
                        possible = false;
                        break;
                    }
                }
            }
            
            // Step 3: Print Results
            if (!possible) {
                out.println("-1");
            } else {
                for (int i = 0; i < n; i++) {
                    out.println(new String(ans[i]));
                }
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