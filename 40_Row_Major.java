import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        
        int t = Integer.parseInt(line.trim());
        StringBuilder out = new StringBuilder();
        
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine().trim());
            
            // Step 1: Find the smallest non-divisor
            int k = 1;
            while (n % k == 0) {
                k++;
            }
            
            // Step 2: Construct the periodic string
            for (int i = 0; i < n; i++) {
                // Modulo math keeps the character within the bounds of k
                out.append((char) ('a' + (i % k)));
            }
            out.append("\n");
        }
        
        System.out.print(out.toString());
    }
}