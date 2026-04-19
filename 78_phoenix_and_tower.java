import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        String line = br.readLine();
        if (line == null) return;
        int t = Integer.parseInt(line.trim());
        
        while (t-- > 0) {
            String[] nmx = br.readLine().trim().split("\\s+");
            int n = Integer.parseInt(nmx[0]);
            int m = Integer.parseInt(nmx[1]);
            int x = Integer.parseInt(nmx[2]); // x is implicitly handled by the logic
            
            String[] hStrings = br.readLine().trim().split("\\s+");
            
            // Priority Queue to store {height, tower_id}
            // Sorted by height (ascending). If heights are equal, order doesn't matter.
            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
            
            // Initialize m empty towers
            for (int i = 1; i <= m; i++) {
                pq.offer(new int[]{0, i});
            }
            
            out.println("YES");
            
            for (int i = 0; i < n; i++) {
                int blockHeight = Integer.parseInt(hStrings[i]);
                
                // Get the shortest tower
                int[] shortest = pq.poll();
                
                // Print the assigned tower ID
                out.print(shortest[1] + (i == n - 1 ? "" : " "));
                
                // Add the block to the tower and put it back in the queue
                shortest[0] += blockHeight;
                pq.offer(shortest);
            }
            out.println();
        }
        out.flush();
    }
}