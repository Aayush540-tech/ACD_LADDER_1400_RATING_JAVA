import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);
        
        if (!sc.hasNext()) return;
        int t = sc.nextInt();
        
        while (t-- > 0) {
            int n = sc.nextInt();
            String s = sc.next();
            char[] arr = s.toCharArray();
            
            // Step 1: Find LLS scanning Right to Left
            List<Integer> llsIndices = new ArrayList<>();
            char maxChar = (char) 0;
            
            for (int i = n - 1; i >= 0; i--) {
                if (arr[i] >= maxChar) {
                    llsIndices.add(i);
                    maxChar = arr[i];
                }
            }
            
            // Reverse list to make it Left to Right
            Collections.reverse(llsIndices);
            int m = llsIndices.size();
            
            // Step 2: Extract LLS chars
            char[] llsChars = new char[m];
            for (int i = 0; i < m; i++) {
                llsChars[i] = arr[llsIndices.get(i)];
            }
            
            // Find count of the absolute largest character
            char largest = llsChars[0];
            int duplicateCount = 0;
            for (int i = 0; i < m; i++) {
                if (llsChars[i] == largest) {
                    duplicateCount++;
                } else {
                    break; // Since LLS is descending, once it changes, it won't match again
                }
            }
            
            // Step 3: Reconstruct string by reversing LLS in place
            char[] finalStr = arr.clone();
            for (int i = 0; i < m; i++) {
                finalStr[llsIndices.get(i)] = llsChars[m - 1 - i];
            }
            
            // The Sanity Check
            boolean isSorted = true;
            for (int i = 1; i < n; i++) {
                if (finalStr[i] < finalStr[i - 1]) {
                    isSorted = false;
                    break;
                }
            }
            
            if (isSorted) {
                out.println(m - duplicateCount);
            } else {
                out.println("-1");
            }
        }
        out.flush();
    }

    // Standard Fast I/O
    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        boolean hasNext() {
            while (st == null || !st.hasMoreElements()) {
                try { 
                    String line = br.readLine();
                    if (line == null) return false;
                    st = new StringTokenizer(line); 
                }
                catch (IOException e) { return false; }
            }
            return true;
        }
        String next() {
            if (!hasNext()) return null;
            return st.nextToken();
        }
        int nextInt() { return Integer.parseInt(next()); }
    }
}