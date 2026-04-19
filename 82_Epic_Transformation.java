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
            
            // Map to store frequencies
            Map<String, Integer> counts = new HashMap<>();
            int maxF = 0;
            
            for (int i = 0; i < n; i++) {
                String val = elements[i];
                int currentCount = counts.getOrDefault(val, 0) + 1;
                counts.put(val, currentCount);
                
                // Track the maximum frequency on the fly
                if (currentCount > maxF) {
                    maxF = currentCount;
                }
            }
            
            // Apply the mathematical logic
            if (maxF * 2 > n) {
                out.println((maxF * 2) - n);
            } else {
                out.println(n % 2);
            }
        }
        out.flush();
    }
}