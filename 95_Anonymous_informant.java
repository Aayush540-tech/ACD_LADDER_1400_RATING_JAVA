import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 1. Fast I/O Setup
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        
        while (t-- > 0) {
            // 2. Read n (size) and k (shifts)
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            
            // 3. Read the final array state
            int[] a = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }
            
            // 4. Start at the current last index
            int currLastIdx = n - 1; 
            boolean possible = true;
            
            // 5. Simulation loop: we only need to check up to n times
            // because there are only n possible positions. If we pass n, we are in a cycle.
            int steps = Math.min(n, k);
            for (int i = 0; i < steps; i++) {
                int val = a[currLastIdx]; // This is the 'x' used for the shift
                
                // 6. The Impossible Check: If the shift value is larger than the array size
                if (val > n) {
                    possible = false;
                    break;
                }
                
                // 7. The Backward Jump:
                // Move the pointer to where the PREVIOUS last element was.
                // We subtract 'val' because we are going backward in time.
                // We add 'n' before the modulo to handle negative results.
                currLastIdx = (currLastIdx - val + n) % n;
            }
            
            // 8. Output Result
            if (possible) System.out.println("Yes");
            else System.out.println("No");
        }
    }
}