import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // Fast I/O
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] nk = br.readLine().trim().split("\\s+");
        if (nk.length < 2) return;
        int n = Integer.parseInt(nk[0]);
        long k = Long.parseLong(nk[1]);
        
        long[] a = new long[n];
        String[] elements = br.readLine().trim().split("\\s+");
        for (int i = 0; i < n; i++) {
            a[i] = Long.parseLong(elements[i]);
        }
        
        // Sort the array to find the median and process the right half
        Arrays.sort(a);
        
        int m = n / 2;
        
        // Binary search limits. Use LONG to prevent overflow!
        long low = a[m];
        long high = a[m] + k;
        long ans = low;
        
        while (low <= high) {
            long mid = low + (high - low) / 2;
            
            if (isPossible(a, m, n, mid, k)) {
                ans = mid;
                low = mid + 1; // Try to go higher
            } else {
                high = mid - 1; // Too high, go lower
            }
        }
        
        System.out.println(ans);
    }
    
    // Helper function to calculate cost
    private static boolean isPossible(long[] a, int m, int n, long target, long k) {
        long cost = 0;
        for (int i = m; i < n; i++) {
            if (target > a[i]) {
                cost += (target - a[i]);
                if (cost > k) {
                    return false; // Early exit optimization
                }
            }
        }
        return cost <= k;
    }
}