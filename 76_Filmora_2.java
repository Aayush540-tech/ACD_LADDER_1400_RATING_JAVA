import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;
        
        int n = sc.nextInt();
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = sc.nextInt();
        }
        
        // Initialize the N x N grid (defaults to 0)
        int[][] ans = new int[n][n];
        
        // Process each number on the diagonal
        for (int i = 0; i < n; i++) {
            int val = p[i];
            
            // Start at the diagonal position
            int r = i;
            int c = i;
            ans[r][c] = val;
            
            // We placed 1 block, we need to place val - 1 more
            int rem = val - 1;
            
            while (rem > 0) {
                // Try to go LEFT: Check boundary and if cell is empty
                if (c > 0 && ans[r][c - 1] == 0) {
                    c--; // Move Left
                } else {
                    r++; // Move Down
                }
                
                ans[r][c] = val;
                rem--;
            }
        }
        
        // Print the lower triangle using fast output
        PrintWriter out = new PrintWriter(System.out);
        for (int r = 0; r < n; r++) {
            for (int c = 0; c <= r; c++) {
                out.print(ans[r][c] + (c == r ? "" : " "));
            }
            out.println();
        }
        out.flush();
    }
}