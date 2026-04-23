import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            Map<Integer, Integer> counts = new HashMap<>();
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            int maxFreq = 0;
            for (int i = 0; i < n; i++) {
                int val = Integer.parseInt(st.nextToken());
                int newFreq = counts.getOrDefault(val, 0) + 1;
                counts.put(val, newFreq);
                // Track the frequency of the most common element
                maxFreq = Math.max(maxFreq, newFreq);
            }
            
            // Logic implementation
            if (maxFreq <= n / 2) {
                // If no element dominates, we can pair almost everything
                System.out.println(n % 2);
            } else {
                // The majority element dominates
                System.out.println(2 * maxFreq - n);
            }
        }
    }
}