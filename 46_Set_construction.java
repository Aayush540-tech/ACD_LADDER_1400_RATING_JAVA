import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        
        int t = Integer.parseInt(line.trim());
        StringBuilder out = new StringBuilder();
        
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine().trim());
            String[] b = new String[n];
            
            for (int i = 0; i < n; i++) {
                b[i] = br.readLine().trim();
            }
            
            for (int i = 0; i < n; i++) {
                List<Integer> currentSet = new ArrayList<>();
                // Step 1: Self-Inclusion
                currentSet.add(i + 1);
                
                // Step 2: Inheritance (Read column i)
                for (int j = 0; j < n; j++) {
                    if (b[j].charAt(i) == '1') {
                        currentSet.add(j + 1);
                    }
                }
                
                // Format output
                out.append(currentSet.size()).append(" ");
                for (int k = 0; k < currentSet.size(); k++) {
                    out.append(currentSet.get(k)).append(k == currentSet.size() - 1 ? "" : " ");
                }
                out.append("\n");
            }
        }
        
        System.out.print(out.toString());
    }
}