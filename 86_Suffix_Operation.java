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
            int n = Integer.parseInt(br.readLine().trim());
            String[] elements = br.readLine().trim().split("\\s+");
            
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(elements[i]);
            }
            
            // 1. Calculate baseline total cost. 
            // MUST be a long because 2*10^5 * 10^9 exceeds 32-bit integer limits!
            long totalDiff = 0;
            for (int i = 1; i < n; i++) {
                totalDiff += Math.abs(a[i] - a[i-1]);
            }
            
            // 2. Calculate the maximum possible savings
            // Check the boundaries first
            long maxSavings = Math.max(Math.abs(a[1] - a[0]), Math.abs(a[n-1] - a[n-2]));
            
            // Check internal elements
            for (int i = 1; i < n - 1; i++) {
                long currentLocalCost = Math.abs(a[i] - a[i-1]) + Math.abs(a[i+1] - a[i]);
                long newLocalCost = Math.abs(a[i+1] - a[i-1]);
                long savings = currentLocalCost - newLocalCost;
                
                if (savings > maxSavings) {
                    maxSavings = savings;
                }
            }
            
            // 3. Print the minimized total cost
            out.println(totalDiff - maxSavings);
        }
        out.flush();
    }
}