import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        int t = Integer.parseInt(br.readLine().trim());
        
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine().trim());
            String[] line = br.readLine().trim().split("\\s+");
            
            // Just compare the first and last elements
            int first = Integer.parseInt(line[0]);
            int last = Integer.parseInt(line[n - 1]);
            
            if (first < last) {
                out.println("YES");
            } else {
                out.println("NO");
            }
        }
        out.flush();
    }
}