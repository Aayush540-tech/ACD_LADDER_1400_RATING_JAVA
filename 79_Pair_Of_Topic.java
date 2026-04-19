import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // Fast I/O
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String line = br.readLine();
        if (line == null) return;
        int n = Integer.parseInt(line.trim());
        
        int[] a = new int[n];
        int[] b = new int[n];
        
        String[] aStrs = br.readLine().trim().split("\\s+");
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(aStrs[i]);
        }
        
        String[] bStrs = br.readLine().trim().split("\\s+");
        for (int i = 0; i < n; i++) {
            b[i] = Integer.parseInt(bStrs[i]);
        }
        
        // Create the difference array 'c'
        // Using Integer objects allows us to sort without O(N^2) quicksort worst-case traps in Java, 
        // though standard Arrays.sort(int[]) usually passes here.
        int[] c = new int[n];
        for (int i = 0; i < n; i++) {
            c[i] = a[i] - b[i];
        }
        
        // Sort the array ascending
        Arrays.sort(c);
        
        // Use LONG to prevent integer overflow! Max pairs ~ 2*10^10
        long ans = 0; 
        
        int left = 0;
        int right = n - 1;
        
        // Two-pointer sweep
        while (left < right) {
            if (c[left] + c[right] > 0) {
                // All elements between left and right form a valid pair with c[right]
                ans += (right - left);
                right--; // Decrement right pointer
            } else {
                // Sum is too small, increment left pointer
                left++;
            }
        }
        
        System.out.println(ans);
    }
}