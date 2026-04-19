import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // Fast I/O setup
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        String line = br.readLine();
        if (line == null) return;
        int t = Integer.parseInt(line.trim());
        
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine().trim());
            String[] elements = br.readLine().trim().split("\\s+");
            
            int[] a = new int[n];
            long sumEvenIndices = 0; // 0, 2, 4... (1st, 3rd, 5th elements)
            long sumOddIndices = 0;  // 1, 3, 5... (2nd, 4th, 6th elements)
            
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(elements[i]);
                if (i % 2 == 0) {
                    sumEvenIndices += a[i];
                } else {
                    sumOddIndices += a[i];
                }
            }
            
            // Rebuild the array based on which half has the smaller sum
            for (int i = 0; i < n; i++) {
                if (sumEvenIndices < sumOddIndices) {
                    // Replace even indices (0, 2, 4) with 1
                    if (i % 2 == 0) out.print(1 + (i == n - 1 ? "" : " "));
                    else out.print(a[i] + (i == n - 1 ? "" : " "));
                } else {
                    // Replace odd indices (1, 3, 5) with 1
                    if (i % 2 != 0) out.print(1 + (i == n - 1 ? "" : " "));
                    else out.print(a[i] + (i == n - 1 ? "" : " "));
                }
            }
            out.println();
        }
        out.flush();
    }
}