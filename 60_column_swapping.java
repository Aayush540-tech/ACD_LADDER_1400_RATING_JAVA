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
            
            int[][] grid = new int[n][m];
            
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    grid[i][j] = sc.nextInt();
                }
            }
            
            // Step 1: Find the Snitch Row
            int badRow = -1;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m - 1; j++) {
                    if (grid[i][j] > grid[i][j+1]) {
                        badRow = i;
                        break;
                    }
                }
                if (badRow != -1) break;
            }
            
            // If already sorted
            if (badRow == -1) {
                out.println("1 1");
                continue;
            }
            
            // Step 2: Compare with Perfection
            int[] sortedRow = grid[badRow].clone();
            Arrays.sort(sortedRow);
            
            List<Integer> mismatches = new ArrayList<>();
            for (int j = 0; j < m; j++) {
                if (grid[badRow][j] != sortedRow[j]) {
                    mismatches.add(j);
                }
            }
            
            // Step 3: The Mismatch Rule
            if (mismatches.size() > 2) {
                out.println("-1");
                continue;
            }
            
            // Step 4: The All-or-Nothing Test
            int c1 = mismatches.get(0);
            int c2 = mismatches.get(1);
            
            // Swap columns
            for (int i = 0; i < n; i++) {
                int temp = grid[i][c1];
                grid[i][c1] = grid[i][c2];
                grid[i][c2] = temp;
            }
            
            // Verify grid
            boolean possible = true;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m - 1; j++) {
                    if (grid[i][j] > grid[i][j+1]) {
                        possible = false;
                        break;
                    }
                }
                if (!possible) break;
            }
            
            if (possible) {
                out.println((c1 + 1) + " " + (c2 + 1));
            } else {
                out.println("-1");
            }
        }
        out.flush();
    }

    // FastScanner boilerplate
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